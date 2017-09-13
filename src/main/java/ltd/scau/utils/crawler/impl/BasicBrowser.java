package ltd.scau.utils.crawler.impl;

import ltd.scau.utils.crawler.Browser;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BasicBrowser implements Browser {

    private CloseableHttpClient httpClient;

    private BasicCookieStore cookieStore;

    private Properties headersProperties;

    public BasicBrowser() {
        this.cookieStore = new BasicCookieStore();
        this.httpClient = HttpClients.custom()
                .setDefaultCookieStore(getCookieStore())
                .setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public Header[] getHeaders() {
        Properties props = getHeadersProperties();
        Header[] headers = new Header[props.size()];
        int i = 0;
        for (String key : props.stringPropertyNames()) {
            headers[i++] = new BasicHeader(key, props.getProperty(key));
        }
        return headers;
    }

    @Override
    public void setHost(String host) {
        getHeadersProperties().setProperty("Host", host);
    }

    @Override
    public void setReferer(String referer) {
        getHeadersProperties().setProperty("Referer", referer);
    }

    @Override
    public String get(String url, String charset) {
        HttpGet get = new HttpGet(url);
        get.setHeaders(getHeaders());
        CloseableHttpResponse response;
        try {
            response = getHttpClient().execute(get);

            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, charset);
            setReferer(url);
            return html;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String post(String url, Map<String, String> datas, String charset) {
        HttpPost post = new HttpPost(url);
        post.setHeaders(getHeaders());
        if (datas != null && !datas.isEmpty()) {
            List<NameValuePair> form = new ArrayList<>();
            for (String key : datas.keySet()) {
                form.add(new BasicNameValuePair(key, datas.get(key)));
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, "GBK");
                post.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            CloseableHttpResponse response = getHttpClient().execute(post);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, charset);
            setReferer(url);
            return html;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public BasicCookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(BasicCookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    @Override
    public Properties getHeadersProperties() {
        return headersProperties;
    }

    public void setHeadersProperties(Properties headersProperties) {
        this.headersProperties = headersProperties;
    }
}
