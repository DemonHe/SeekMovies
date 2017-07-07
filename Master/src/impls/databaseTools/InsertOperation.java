package impls.databaseTools;

import interfaces.DatabaseUpdateOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/5/31.
 * 数据库插入操作
 */
public class InsertOperation implements DatabaseUpdateOperation {
    private String url;

    public InsertOperation(String url){
        this.url = url;
    }

    /**
     * 用于保存新发现的url
     * @throws SQLException sql语句错误
     */
    @Override
    public void execute() throws SQLException {
        Connection connection = DatabasePoolHolder.getConnection();
        String query = "insert into url_database values (?, ?)";
        PreparedStatement preState = connection.prepareStatement(query);
        preState.setString(1, url);
        preState.setLong(2, new Date().getTime());
        preState.executeUpdate();
        //connection.commit();
        connection.close();
    }


}
