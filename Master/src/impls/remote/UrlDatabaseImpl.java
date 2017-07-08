package impls.remote;

import interfaces.UrlDatabase;
import org.junit.Ignore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/5/30.
 * 访问数据库的入口
 */
public class UrlDatabaseImpl extends UnicastRemoteObject implements UrlDatabase{


    public UrlDatabaseImpl() throws RemoteException {
        super();
    }



    @Override
    public boolean hasNotVisited(String url) throws RemoteException {
        MemoryCache memoryCache = MemoryCache.getInstance();
        //DatabaseUpdateQueue updateQueue = DatabaseUpdateQueue.getInstance();
        boolean hasNotVisited = !memoryCache.contains(url);
        if(hasNotVisited){
            memoryCache.put(url);
//            try {
//                DatabaseUpdateOperation operation = new InsertOperation(url);
//                updateQueue.saveOperation(operation);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        return hasNotVisited;
    }

    @Override
    @Ignore
    public void updateUrl(String url, Date updateTime) throws RemoteException{
//        DatabaseUpdateQueue updateQueue = DatabaseUpdateQueue.getInstance();
//        DatabaseUpdateOperation operation = new UpdateOperation(url, updateTime);
//        try {
//            updateQueue.saveOperation(operation);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
