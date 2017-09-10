package ltd.scau.utils.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class HtmlParser {

    public static Map<String, String> parseForm(String html) {
        Document doc = Jsoup.parse(html);
        Elements input = doc.getElementsByTag("input");
        Map<String, String> form = new HashMap<>();
        for (Element e : input) {
            form.put(e.attr("name"), e.attr("value"));
        }
        return form;
    }
}
