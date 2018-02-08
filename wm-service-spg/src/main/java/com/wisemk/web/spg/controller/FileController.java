package com.wisemk.web.spg.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisemk.web.spg.dto.FileDto;
import com.wisemk.web.spg.share.AliYunOss;
import com.wisemk.web.spg.share.JsonResult;

@RestController
@RequestMapping(value = "/mngr/files", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileController extends BaseController
{

    @Value("${aliyun.oss.dir.adassert:weiying/adassert}")
    private String adAssertBaseDir;

    @PostMapping(value = "/upload")
    public JsonResult createFile(@RequestParam("file") MultipartFile multipartFile)
    {
        if (multipartFile.isEmpty())
            renderExc("不允许上传空文件");

        FileDto fileDto = new FileDto();
        try
        {
            String ext = "";
            if (multipartFile.getOriginalFilename().lastIndexOf(".") > 0)
                ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

            fileDto.setSuffixName(ext);
            fileDto.setFileName(multipartFile.getOriginalFilename());
            fileDto.setMime(multipartFile.getContentType());
            fileDto.setFileSize(multipartFile.getSize());

            try
            {
                BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

                fileDto.setResolution(bufferedImage.getWidth() + "*" + bufferedImage.getHeight());
                fileDto.setSize_w(bufferedImage.getWidth());
                fileDto.setSize_h(bufferedImage.getHeight());
            }
            catch (Exception ex)
            {
            }

            String fileName = DigestUtils.md5Hex(multipartFile.getBytes()) + ext;

            String uploadUrl = AliYunOss.uploadFile(multipartFile, adAssertBaseDir, fileName);

            if (null != uploadUrl)
            {
                fileDto.setUrl(uploadUrl);
                return renderSuccess(fileDto);
            }
            else
            {
                return renderExc("文件保存失败");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
