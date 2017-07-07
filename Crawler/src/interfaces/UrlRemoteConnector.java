package interfaces;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/5/30.
 * 同url仓库通信，实现url去重
 */
public interface UrlRemoteConnector {

    /**
     * 显示这个url是否被访问过
     * @param url 要检查的url
     * @return 是否被访问过
     */
    boolean hasNotVisited(String url) throws RemoteException, NotBoundException, MalformedURLException;

    /**
     * 更新url的更新日期
     * @param url 要更新的url
     * @param updateTime 更新的时间
     */
    void updateUrl(String url, Date updateTime) throws RemoteException, NotBoundException, MalformedURLException;
}
