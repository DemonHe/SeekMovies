package impls.databaseTools;

import interfaces.DatabaseUpdateOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/5/31.
 * 实现数据库更新操作
 */
public class UpdateOperation implements DatabaseUpdateOperation{
    private String url;
    private Date updateTime;

    public UpdateOperation(String url, Date updateTime) {
        this.url = url;
        this.updateTime = updateTime;
    }

    @Override
    public void execute() throws SQLException {
        Connection connection = DatabasePoolHolder.getConnection();
        String query = "update url_database d" +
                        "set d.updateTime = ?" +
                        "where d.url = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, updateTime.getTime());
        statement.setString(2, url);
        statement.executeUpdate();
        //connection.commit();
        connection.close();
    }
}
