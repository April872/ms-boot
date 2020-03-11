package org.ant.config.oss;

import org.jeecg.common.util.oss.OssBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssBootConfiguration {

    @Value("${myconfig.oss.endpoint}")
    private String endpoint;
    @Value("${myconfig.oss.accessKey}")
    private String accessKeyId;
    @Value("${myconfig.oss.secretKey}")
    private String accessKeySecret;
    @Value("${myconfig.oss.bucketName}")
    private String bucketName;
    @Value("${myconfig.oss.staticDomain}")
    private String staticDomain;


    @Bean
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endpoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
    }
}