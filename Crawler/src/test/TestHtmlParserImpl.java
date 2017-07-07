package test;

import impls.parser.HtmlParserImpl;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.fail;

/**
 * Created by douchengfeng on 2017/6/1.
 * htmlParserImpl的测试用例
 */
public class TestHtmlParserImpl {

    @Test
    public void testParse(){
        try {
            HtmlParserImpl impl = new HtmlParserImpl();

            File input = new File("C:/Users/douchengfeng/Desktop/异形：契约 在线购票&影讯.htm");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            StringBuilder html = new StringBuilder();
            String temp;
            while((temp = reader.readLine()) != null){
                html.append(temp);
            }
            impl.parseHtml(html.toString(), "https://movie.douban.com/subject/11803087/cinema/nanjing/?from=playing_btn");
            Thread.sleep(20000);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            fail();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
