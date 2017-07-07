package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by douchengfeng on 2017/5/30.
 * 远程对象的接口
 */
public interface Monitor extends Remote{

    /**
     * 重置远程对象的timer
     */
    void resetTimer(String name) throws RemoteException;
}
