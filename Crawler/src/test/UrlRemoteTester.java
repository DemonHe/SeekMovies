package test;

import impls.database.urlDatabase.UrlRemoteConnectorImpl;
import org.junit.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static junit.framework.TestCase.*;


/**
 * Created by douchengfeng on 2017/5/30.
 * UrlRemoteConnectorImpl的测试用例
 */
public class UrlRemoteTester{

    @Test
    @Ignore("已经测试过了")
    public void testConnection(){
        UrlRemoteConnectorImpl impl = new UrlRemoteConnectorImpl();
        try {
            boolean hasNotVisited = impl.hasNotVisited("这是一个测试的url");
            assertTrue(hasNotVisited);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            fail("连接失败了！");
        }
    }

    @Test
    public void testDuplicated(){
        UrlRemoteConnectorImpl impl = new UrlRemoteConnectorImpl();
        try {
            boolean hasNotVisited = impl.hasNotVisited("http://test.com");
            assertTrue(hasNotVisited);
            hasNotVisited = impl.hasNotVisited("http://test.com");
            assertTrue(!hasNotVisited);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            fail("连接失败了！");
        }
    }



}
