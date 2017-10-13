package ltd.scau.entity.dao.sports;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.sports.Item;
import ltd.scau.entity.type.OrderType;

import java.util.List;

public interface ItemDao extends BaseDao<Item> {

    /**
     * @param examId
     * @param itemId
     * @param value
     * @return 0=total, 1=ascendingRank, 2=same
     */
    int[] rank(int examId, int itemId, String stuId, double value);

}
