package ltd.scau.utils.storage;

import java.io.FileInputStream;

/**
 * 图像存储基本方法
 */
public interface StorageClient {

    /**
     *
     * @param inputStream 被存储图像
     * @param path 存储路径
     * @param params 根据不同的实现类，传递不同的参数
     * @return 存储成功则返回 true
     */
    boolean put(FileInputStream inputStream, String path, String...params);
}
