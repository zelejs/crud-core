package com.jfeat.crud.core.properties;

import java.io.File;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@ConfigurationProperties(
        prefix = "am"
)
public class AmProperties {
    public static final String PREFIX = "am";
    private String jsonSerializeDateFormat = "yyyy-MM-dd HH:mm:ss";
    //private Boolean kaptchaOpen = false;
    //private Boolean swaggerOpen = false;
    //private String fileUploadPath;
    //private String fileHost = "http://localhost:8080";
    //private Boolean haveCreatePath = false;
    @Deprecated
    private Integer workerId = 0;

    // cache
    private String redisHost = "127.0.0.1";
    private int redisPort = 6379;
    private int redisTimeout;
    private Boolean shiroCacheRedis = false;
    // lbs
    //private String lbsBaiduKey;
    //private String lbsAmapKey;
    //private String lbsTencentKey;

    @Deprecated
    private Boolean resetAdminPerms = true;  // 实始化admin权限, 框架自动默认有所有权限


    public AmProperties() {
    }


    /* lbs
    public String getLbsTencentKey() {
        return this.lbsTencentKey;
    }

    public void setLbsTencentKey(String lbsTencentKey) {
        this.lbsTencentKey = lbsTencentKey;
    }

    public String getLbsAmapKey() {
        return this.lbsAmapKey;
    }

    public void setLbsAmapKey(String lbsAmapKey) {
        this.lbsAmapKey = lbsAmapKey;
    }

    public String getLbsBaiduKey() {
        return this.lbsBaiduKey;
    }

    public void setLbsBaiduKey(String lbsBaiduKey) {
        this.lbsBaiduKey = lbsBaiduKey;
    }
     */

    @Deprecated
    public Integer getWorkerId() {
        return this.workerId;
    }

    @Deprecated
    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    /* file upload
    public String getFileHost() {
        return this.fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }
    */

    public String getRedisHost() {
        return this.redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public int getRedisPort() {
        return this.redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public int getRedisTimeout() {
        return this.redisTimeout;
    }

    public void setRedisTimeout(int redisTimeout) {
        this.redisTimeout = redisTimeout;
    }

    public Boolean getShiroCacheRedis() {
        return this.shiroCacheRedis;
    }

    public void setShiroCacheRedis(Boolean shiroCacheRedis) {
        this.shiroCacheRedis = shiroCacheRedis;
    }

    /* file upload
    public String getFileUploadPath() {
        if (StringUtils.isEmpty(this.fileUploadPath)) {
            return "java.io.tmpdir";
        } else {
            if (!this.fileUploadPath.endsWith(File.separator)) {
                this.fileUploadPath = this.fileUploadPath + File.separator;
            }

            if (!this.haveCreatePath) {
                File file = new File(this.fileUploadPath);
                file.mkdirs();
                this.haveCreatePath = true;
            }

            return this.fileUploadPath;
        }
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }
    */

    /*
    public Boolean getKaptchaOpen() {
        return this.kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }
    */

    /*
    public Boolean getSwaggerOpen() {
        return this.swaggerOpen;
    }

    public void setSwaggerOpen(Boolean swaggerOpen) {
        this.swaggerOpen = swaggerOpen;
    }
     */

    public String getJsonSerializeDateFormat() {
        return this.jsonSerializeDateFormat;
    }

    public void setJsonSerializeDateFormat(String jsonSerializeDateFormat) {
        this.jsonSerializeDateFormat = jsonSerializeDateFormat;
    }


    @Deprecated
    public Boolean getResetAdminPerms() {
        return this.resetAdminPerms;
    }

    @Deprecated
    public void setResetAdminPerms(Boolean resetAdminPerms) {
        this.resetAdminPerms = resetAdminPerms;
    }
}
