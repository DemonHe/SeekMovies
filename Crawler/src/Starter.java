import impls.controller.ControllerImpl;
import interfaces.Controller;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by douchengfeng on 2017/6/2.
 * 启动器
 */
public class Starter {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        LocateRegistry.createRegistry(12000);
        Controller controller = new ControllerImpl();
        Naming.bind("rmi://localhost:12000/Controller", controller);
    }
}
