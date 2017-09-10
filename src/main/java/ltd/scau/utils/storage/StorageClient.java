package ltd.scau.utils.storage;

import java.io.FileInputStream;

public interface StorageClient {

    boolean put(FileInputStream inputStream, String path, String...params);
}
