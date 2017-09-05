package ltd.scau.struts2.converter;

import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;
import java.util.UUID;

public class UUIDConverter extends StrutsTypeConverter {
    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        if (strings[0] == null || strings[0].equals("")) return null;
        return UUID.fromString(strings[0]);
    }

    @Override
    public String convertToString(Map map, Object o) {
        return o.toString();
    }
}
