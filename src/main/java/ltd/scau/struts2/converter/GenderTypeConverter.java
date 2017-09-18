package ltd.scau.struts2.converter;

import ltd.scau.entity.type.GenderType;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

/**
 * Struts2 自定义类型转换器
 * Enum 类型的 GenderType 与String 相互转换
 */
public class GenderTypeConverter extends StrutsTypeConverter {

    @Override
    public String convertToString(Map map, Object o) {
        return o.toString();
    }

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        return GenderType.valueOf(strings[0]);
    }
}
