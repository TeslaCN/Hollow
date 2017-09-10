package ltd.scau.utils.storage.impl;

import ltd.scau.utils.storage.StorageClient;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class LocalStorageClient implements StorageClient {

    public String getPathPrefix() {
        return ServletActionContext.getServletContext().getRealPath("") + "/";
    }

    @Override
    public boolean put(FileInputStream inputStream, String path, String... params) {
        try {
            try (FileOutputStream out = new FileOutputStream(getPathPrefix() + path);) {
                System.out.println(inputStream.available());
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                out.write(b);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
