package impls.monitor;

import impls.controller.MasterController;
import interfaces.Monitor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Created by douchengfeng on 2017/6/2.
 * 监控器的实现类
 */
public class MonitorImpl extends UnicastRemoteObject implements Monitor{


    public MonitorImpl() throws RemoteException {
    }

    @Override
    public void resetTimer(String name) throws RemoteException {
        MasterController.resetTimers(name);
    }
}
