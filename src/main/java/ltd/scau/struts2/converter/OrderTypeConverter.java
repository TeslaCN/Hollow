package ltd.scau.struts2.converter;

import ltd.scau.entity.type.OrderType;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

public class OrderTypeConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        return OrderType.valueOf(values[0]);
    }

    @Override
    public String convertToString(Map context, Object o) {
        return o.toString();
    }
}
