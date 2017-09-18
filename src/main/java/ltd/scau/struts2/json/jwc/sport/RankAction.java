package ltd.scau.struts2.json.jwc.sport;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import ltd.scau.entity.dao.sports.ItemDao;
import ltd.scau.entity.type.OrderType;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("hollow-json")
@Result(type = "json", params = {
        "includeProperties", "rank"
})
@Conversion(conversions = {@TypeConversion(key = "orderType", converter = "ltd.scau.struts2.converter.OrderTypeConverter")})
public class RankAction extends ActionSupport {

    private ItemDao itemDao;

    private int rank;

    private int examId;

    private int itemId;

    private double value;

    private OrderType orderType;

    @Override
    public String execute() throws Exception {
        setRank(getItemDao().rank(getExamId(), getItemId(), getValue(), getOrderType()));
        return SUCCESS;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public int getRank() {
        return rank;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
