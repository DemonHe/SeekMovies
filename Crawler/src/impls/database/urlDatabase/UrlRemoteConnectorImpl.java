package impls.database.urlDatabase;

import interfaces.UrlDatabase;
import interfaces.UrlRemoteConnector;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/5/30.
 * 连接url仓库的连接器
 */
public class UrlRemoteConnectorImpl implements UrlRemoteConnector{
    private UrlDatabase database;


    @Override
    public boolean hasNotVisited(String url) throws RemoteException, NotBoundException, MalformedURLException {
        if(database == null){
            database = (UrlDatabase) Naming.lookup("rmi://localhost:13000/UrlDatabase");
        }
        return database.hasNotVisited(url);
    }

    @Override
    public void updateUrl(String url, Date updateTime) throws RemoteException, NotBoundException, MalformedURLException {
        if(database == null){
            database = (UrlDatabase) Naming.lookup("rmi://localhost:13000/UrlDatabase");
        }
        database.updateUrl(url, updateTime);
    }


}
