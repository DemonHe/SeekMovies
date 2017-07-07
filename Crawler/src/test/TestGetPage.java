package test;

import impls.downloader.HtmlDownLoaderImpl;
import impls.parser.HtmlParserImpl;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by run-jiao on 2017/6/8.
 */
public class TestGetPage {
    @Test
    public void testGetpage(){
        HtmlDownLoaderImpl impl = new HtmlDownLoaderImpl();
        try {
            String url = "http://www.dytt8.net/html/gndy/dyzz/list_23_5.html";
            HtmlParserImpl impl2 = new HtmlParserImpl();
            String page = impl.getHtmlPage(url);
            impl2.parseHtml(page,url);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
