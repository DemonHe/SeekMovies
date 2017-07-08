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

            File input = new File("D:\\Homework\\Junior\\应用集成原理与工具\\神偷奶爸3 - 南京影讯、 影片简介、电影票网上预订、在线购票、兑换券、团购 - 淘票票.html");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            StringBuilder html = new StringBuilder();
            String temp;
            while((temp = reader.readLine()) != null){
                html.append(temp);
            }

            System.out.print(impl.parseHtml(html.toString(), "https://dianying.taobao.com/showDetail.htm?showId=178416").toString());
            Thread.sleep(20000);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            fail();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
