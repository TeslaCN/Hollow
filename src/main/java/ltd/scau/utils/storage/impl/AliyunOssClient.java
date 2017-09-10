package ltd.scau.utils.storage.impl;

import com.aliyun.oss.OSSClient;
import ltd.scau.utils.storage.StorageClient;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;

public class AliyunOssClient implements StorageClient {

    private OSSClient ossClient;

    public String getPathPrefix() {
        String prefix = ServletActionContext.getServletContext().getContextPath();
        switch (prefix) {
            case "/":
                return "";
            default:
                return prefix.concat("/");
        }
    }

    @Override
    public boolean put(FileInputStream inputStream, String path, String... params) {
        path = getPathPrefix() + path;
        if (path.startsWith("/")) path = path.substring(1);
        getOssClient().putObject(params[0], path, inputStream);
        return true;
    }

    public OSSClient getOssClient() {
        return ossClient;
    }

    public void setOssClient(OSSClient ossClient) {
        this.ossClient = ossClient;
    }
}
