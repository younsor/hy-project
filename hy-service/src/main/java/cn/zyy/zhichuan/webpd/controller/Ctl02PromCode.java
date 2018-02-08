package cn.zyy.zhichuan.webpd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zyy.oss.share.OssLog;
import cn.zyy.zhichuan.webpd.dto.DtoPromCodeDetail;
import cn.zyy.zhichuan.webpd.service.SrvImplAuth;
import cn.zyy.zhichuan.webpd.service.SrvImplPromCode;
import cn.zyy.zhichuan.webpd.share.CommonPage;
import cn.zyy.zhichuan.webpd.share.JsonResult;

@RestController
@RequestMapping(value = "/mngr/adcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Ctl02PromCode extends BaseController
{
    private static final OssLog log = new OssLog();

    @Autowired
    private SrvImplAuth         srvAuth;

    @Autowired
    private SrvImplPromCode     srvPromCode;

    @GetMapping(value = { "/pulldown" })
    public JsonResult getMaterialPulldown()
    {
        return renderSuccess(srvPromCode.getPromCodePullDown(getUserId()));
    }

    @GetMapping(value = { "", "/" })
    public CommonPage getPromCodeList(Optional<String> enabled, Optional<String> key, @PageableDefault(size = 20, page = 0) Pageable pageable)
    {
        int pageNum = pageable.getPageNumber() < 1 ? 0 : pageable.getPageNumber() - 1;
        int pageSize = pageable.getPageSize() > 0 ? pageable.getPageSize() : 20;
        PageRequest page = new PageRequest(pageNum, pageSize, pageable.getSort());

        String isActive = enabled.isPresent() ? enabled.get() : null;
        String matchUserName = key.isPresent() ? key.get() : null;
        return srvPromCode.getPromCodeList(getUserId(), isActive, matchUserName, page);
    }

    /* 生成广告码 */
    @PostMapping(value = { "/generate" })
    public JsonResult getPromCodeGenerate(@RequestParam(value = "uid") int pdUid, int num)
    {
        /* 先检查用户是否存在 */
        if (!srvAuth.isPdUserExist(pdUid))
        {
            return renderError(HttpStatus.UNAUTHORIZED, "pd-user[" + pdUid + "] not exist");
        }

        if (srvPromCode.generatePromCode(pdUid, num))
        {
            return renderSuccess();
        }
        else
        {
            return renderError(HttpStatus.UNAUTHORIZED, "generate prom-code fail");
        }
    }

    /* 推广码详情 */
    @GetMapping(value = { "/{id}" })
    public JsonResult getPromCodeDetail(@PathVariable(value = "id") int promCodeId)
    {
        DtoPromCodeDetail promCodeDetail = srvPromCode.getPromCodeDetail(promCodeId);
        if (null == promCodeDetail)
        {
            return renderError(HttpStatus.UNAUTHORIZED, "no detail info for id=" + promCodeId);
        }
        else
        {
            return renderSuccess(promCodeDetail);
        }
    }

    /* 激活或者更新推广码 */
    @PostMapping(value = { "/{id}" })
    public JsonResult activeOrupdatePromCode(@PathVariable(value = "id") int promCodeId, int mid, String name, String tel)
    {
        boolean isSuccess = srvPromCode.activateOrUpdatePromCode(promCodeId, mid, name, tel);

        if (isSuccess)
        {
            return renderSuccess();
        }
        else
        {
            return renderError(HttpStatus.UNAUTHORIZED, "handle fail");
        }
    }
}
