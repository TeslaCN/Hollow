package ltd.scau.struts2.json.jwc.sport;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.sports.ItemDao;
import ltd.scau.entity.type.OrderType;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 根据参数计算出对应数据的排名
 */
@ParentPackage("hollow")
@Result(type = "json", params = {
        "includeProperties", "total, rank, same"
})
@Conversion(conversions = {@TypeConversion(key = "orderType", converter = "ltd.scau.struts2.converter.OrderTypeConverter")})
public class RankAction extends ActionSupport {

    private ItemDao itemDao;

    /**
     * Ascending
     */
    private int rank;

    private int total;

    private int same;

    private int examId;

    private int itemId;

    private double value;

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String stuId = (String) ctx.getSession().get("stuId");
        if (stuId == null || stuId.trim().equals("")) {
            User user = (User) ctx.getSession().get("user");
            stuId = user.getStuId();
        }
        //取学号前四位即年级
        String grade = stuId.substring(0, 4);
        int[] result = getItemDao().rank(getExamId(), getItemId(), grade, getValue());

        setTotal(result[0]);
        setRank(result[1]);
        setSame(result[2]);
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

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSame() {
        return same;
    }

    public void setSame(int same) {
        this.same = same;
    }
}
