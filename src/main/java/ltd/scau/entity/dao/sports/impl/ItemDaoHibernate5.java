package ltd.scau.entity.dao.sports.impl;

import ltd.scau.base.dao.impl.BaseDaoHibernate5;
import ltd.scau.entity.dao.sports.ItemDao;
import ltd.scau.entity.sports.Item;
import ltd.scau.entity.sports.Item_;
import ltd.scau.entity.type.OrderType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoHibernate5 extends BaseDaoHibernate5<Item> implements ItemDao {

    private DataSource dataSource;

    @Override
    public int rank(int examId, int itemId, double value, OrderType order) {
        int result = -1;
        try (Connection connection = getDataSource().getConnection();) {
            String sql = "select count(*) from items where exam_id=? and item_id=? and value%s?";
            switch (order) {
                case ASCEND:
                    sql = String.format(sql, "<");
                    break;
                case DESCEND:
                    sql = String.format(sql, ">");
                    break;
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setInt(1, examId);
                preparedStatement.setInt(2, itemId);
                preparedStatement.setDouble(3, value);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    result = -1;
                    if (resultSet.next()) result = resultSet.getInt(1);
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
