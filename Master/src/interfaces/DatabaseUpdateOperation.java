package interfaces;

import java.sql.SQLException;

/**
 * Created by douchengfeng on 2017/5/31.
 * 数据库操作的接口，负责插入操作和更新操作的实现
 */
public interface DatabaseUpdateOperation {

    void execute() throws SQLException;


}
