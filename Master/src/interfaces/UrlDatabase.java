package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/5/30.
 * url仓库的接口，用于检查和保存url，这个是一个远程的接口
 */
public interface UrlDatabase extends Remote {

    /**
     * 检查url是否被访问过了
     * @param url 要被检查的url
     * @return 如果url未被收录或者url没有被访问过，返回true，并把数据库中的这条url标记为已经访问过，如果以及访问过，直接返回false
     */
    boolean hasNotVisited(String url) throws RemoteException;

    /**
     * 更新Url的更新时间
     * @param url url的名称
     * @param updateTime 更新的时间
     */
    void updateUrl(String url, Date updateTime) throws RemoteException;
}
