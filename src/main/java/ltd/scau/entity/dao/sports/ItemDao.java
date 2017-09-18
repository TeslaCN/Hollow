package ltd.scau.entity.dao.sports;

import ltd.scau.base.dao.impl.base.dao.BaseDao;
import ltd.scau.entity.sports.Item;
import ltd.scau.entity.type.OrderType;

public interface ItemDao extends BaseDao<Item> {

    int rank(int examId, int itemId, double value, OrderType order);

}
