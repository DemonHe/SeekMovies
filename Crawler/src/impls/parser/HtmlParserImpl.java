package impls.parser;

import interfaces.HtmlParser;
import org.dom4j.DocumentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by douchengfeng on 2017/5/31.
 * html的解析器
 */
public class HtmlParserImpl implements HtmlParser{
    private static BlockingQueue<Object> saveQueue = new ArrayBlockingQueue<>(1000);
    private UrlMapping urlMapping;
    private volatile boolean stop = false;
    private Thread thread = new Thread(new ParserMission());
    private SessionFactory sessionFactory;

    public HtmlParserImpl() throws DocumentException {
        urlMapping = new UrlMapping();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        thread.start();
    }

    @Override
    public List<String> parseHtml(String html, String url) {
        return urlMapping.parse(url, html);
    }

    @Override
    public void stopHtmlParser() {
        stop = true;
        thread.interrupt();
    }

    public static void saveObject(Object obj) throws InterruptedException {
        saveQueue.put(obj);
    }



    private class ParserMission implements Runnable{

        @Override
        public void run() {
            try {
                while(!stop) {
                    Object obj = saveQueue.take();
                    System.out.println("save object!");
                    Session session = sessionFactory.openSession();
                    Transaction transaction = session.beginTransaction();
                 try{
                     session.saveOrUpdate(obj);
                     transaction.commit();
                     session.close();
                 } catch (RuntimeException e) {
                     e.printStackTrace();
                 } finally {
                     session.close();
                 }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
