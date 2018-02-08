package com.wisemk.web.spg.share;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

public class AliYunOss
{
    private static final Logger LOG             = LoggerFactory.getLogger(AliYunOss.class);

    @Value("${aliyun.oss.accesskey.id}")
    private static final String accessKeyId     = "LTAISEzznWhRyyIB";

    @Value("${aliyun.oss.accesskey.secret}")
    private static final String accessKeySecret = "phiXu1K3v8kiibWGcfcJuk6m8tH3XL";

    @Value("${aliyun.oss.endpoint}")
    private static final String endpoint        = "http://oss-cn-beijing.aliyuncs.com";

    @Value("${aliyun.oss.bucket}")
    private static final String bucketName      = "smartad-asset";

    private static final String cndPath         = "http://cdn.apilnk.com";

    /**
     * 上传文件到OSS中
     */
    public static String uploadFile(MultipartFile multipartFile, String subDir, String uploadName) throws IOException
    {
        return uploadFile(multipartFile, subDir, uploadName, null, null);
    }

    /**
     * 上传文件到OSS中
     */
    public static String uploadFile(MultipartFile multipartFile, String subDir, String uploadName, String contentType, String contentDisposition) throws IOException
    {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 获取Bucket的存在信息
        boolean exists = client.doesBucketExist(bucketName);
        if (!exists)
        {
            // 新建一个Bucket
            client.createBucket(bucketName);
            // CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
        }

        // 获取指定文件的输入流
        InputStream content = multipartFile.getInputStream();
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(multipartFile.getSize());
        if (contentType != null)
        {
            meta.setContentType(contentType);
        }
        if (contentDisposition != null)
        {
            meta.setContentDisposition(contentDisposition);
        }

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, subDir + "/" + uploadName, content, meta);

        return cndPath + "/" + subDir + "/" + uploadName;
    }

    public static String uploadFile(byte[] bytes, String subDir, String uploadName)
    {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 获取Bucket的存在信息
        boolean exists = client.doesBucketExist(bucketName);
        if (!exists)
        {
            // 新建一个Bucket
            client.createBucket(bucketName);
            // CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
        }

        /* 获取指定文件的输入流 */
        InputStream content = new ByteArrayInputStream(bytes);
        /* 创建上传Object的Metadata */
        ObjectMetadata meta = new ObjectMetadata();
        /* 必须设置ContentLength */
        meta.setContentLength(bytes.length);

        /* 上传Object */
        PutObjectResult result = client.putObject(bucketName, subDir + "/" + uploadName, content, meta);

        return cndPath + "/" + subDir + "/" + uploadName;
    }
}
