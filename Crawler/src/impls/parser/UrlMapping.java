package impls.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douchengfeng on 2017/6/1.
 * url映射器
 */
public class UrlMapping {
    private List<MyMap> myMapList = new ArrayList<>();

    UrlMapping() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/templates/url-obj-mapping.xml"));
        Element root = document.getRootElement();
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element element = (Element) it.next();
            String xmlPath = element.elementText("template");
            String url = element.elementText("url");
            MyMap myMap = new MyMap();
            myMap.htmlMapping = new ParserCore(xmlPath);
            myMap.urlPattern = Pattern.compile(url);
            myMapList.add(myMap);
        }
    }

    public List<String> parse(String url, String html){
        for(MyMap myMap: myMapList){
            Matcher matcher = myMap.urlPattern.matcher(url);
            if(matcher.find()){
                return myMap.htmlMapping.parse(html);
            }
        }
        return null;
    }

    private class MyMap{
        ParserCore htmlMapping;
        Pattern urlPattern;
    }
}
