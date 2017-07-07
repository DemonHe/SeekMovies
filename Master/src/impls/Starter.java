package impls;

import impls.console.Console;
import impls.monitor.MonitorImpl;
import impls.remote.UrlDatabaseImpl;
import interfaces.Monitor;
import interfaces.UrlDatabase;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by douchengfeng on 2017/5/30.
 * master的启动器
 */
public class Starter {

    private Console console = new Console();
    private Starter() throws RemoteException, AlreadyBoundException, MalformedURLException {
        this.bindRemoteObject();
    }

    private void startConsole(){
        console.start();
    }

    private void bindRemoteObject() throws RemoteException, AlreadyBoundException, MalformedURLException {
        LocateRegistry.createRegistry(13000);
        UrlDatabase urlDatabase = new UrlDatabaseImpl();
        Naming.bind("rmi://localhost:13000/UrlDatabase", urlDatabase);
        System.out.println(">>>>>INFO:远程UrlDatabase对象绑定成功！");
        Monitor monitor = new MonitorImpl();
        Naming.bind("rmi://localhost:13000/Monitor", monitor);
        System.out.println(">>>>>INFO:远程Monitor对象绑定成功！");
    }


    public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {
        Starter starter = new Starter();
        starter.startConsole();
    }
}
