package ltd.scau.struts2.converter;

import ltd.scau.entity.type.GenderType;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

public class GenderConverter extends StrutsTypeConverter {

    @Override
    public String convertToString(Map map, Object o) {
        return o.toString();
    }

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        switch (strings[0]) {
            case "UNKNOWN":
                return GenderType.UNKNOWN;
            case "MALE":
                return GenderType.MALE;
            case "FEMALE":
                return GenderType.FEMALE;
        }
        return null;
    }
}
