package ltd.scau.utils.crawler;

import java.util.Map;

public interface Browser {

    void setReferer(String referer);

    void setHost(String host);

    String get(String url, String charset);

    String post(String url, Map<String, String> datas, String charset);

}
