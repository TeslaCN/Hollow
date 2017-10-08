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
    public int[] rank(int examId, int itemId, String grade, double value) {
        int[] result = new int[3];
        try (Connection connection = getDataSource().getConnection();) {
            String sql = "select count(*) from items where value>=0 and exam_id=? and item_id=? and stu_id like ? union " +
                    "select count(*) from items where value>=0 and exam_id=? and item_id=? and stu_id like ? and value > ? union " +
                    "select count(*) from items where exam_id=? and item_id=? and stu_id like ? and value = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setInt(1, examId);
                preparedStatement.setInt(2, itemId);
                preparedStatement.setString(3,  grade + "%");

                preparedStatement.setInt(4, examId);
                preparedStatement.setInt(5, itemId);
                preparedStatement.setString(6,  grade + "%");
                preparedStatement.setDouble(7, value);

                preparedStatement.setInt(8, examId);
                preparedStatement.setInt(9, itemId);
                preparedStatement.setString(10,  grade + "%");
                preparedStatement.setDouble(11, value);

                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    if (resultSet.next()) result[0] = resultSet.getInt(1);
                    if (resultSet.next()) result[1] = resultSet.getInt(1);
                    if (resultSet.next()) result[2] = resultSet.getInt(1);
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
