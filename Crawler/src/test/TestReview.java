package test;

import impls.downloader.HtmlDownLoaderImpl;
import impls.parser.HtmlParserImpl;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by run-jiao on 2017/6/11.
 */
public class TestReview {
    @Test
        public void testReview(){
            HtmlDownLoaderImpl impl = new HtmlDownLoaderImpl();
            try {
                String url = "https://movie.douban.com/review/best/";
                HtmlParserImpl impl2 = new HtmlParserImpl();
                String page = impl.getHtmlPage(url);
                impl2.parseHtml(page,url);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
