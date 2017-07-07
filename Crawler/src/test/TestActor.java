package test;

import impls.downloader.HtmlDownLoaderImpl;
import impls.parser.HtmlParserImpl;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by run-jiao on 2017/6/11.
 */
public class TestActor {
    @Test
    public void testGetpage(){
        HtmlDownLoaderImpl impl = new HtmlDownLoaderImpl();
        try {
            String url = "https://movie.douban.com/subject/6311303/?from=playing_poster";
            HtmlParserImpl impl2 = new HtmlParserImpl();
            String page = impl.getHtmlPage(url);
            impl2.parseHtml(page,url);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
