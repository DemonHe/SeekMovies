package impls.parser.mapping;

import impls.parser.HtmlParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by douchengfeng on 2017/6/1.
 * 单对象解析器
 */
public class SingleObjectParser extends AbstractObjectParser {
    private String className;
    private List<Mapping> mappings;

    public SingleObjectParser(String className, List<Mapping> mappings){
        this.mappings = mappings;
        this.className = className;
    }

    @Override
    public void parse(String html) {
        //System.out.println(className);
        Document doc = Jsoup.parseBodyFragment(html);
        try {
            Class<?> object = Class.forName(className);
            Object instance = object.newInstance();
            for(Mapping mapping: mappings){
                String tag = mapping.getTag();
                String need = mapping.getNeed();
                String attrName = mapping.getAttrName();
                String data;

                Element element = this.getElement(tag, doc);
                if(element == null){
                    continue;
                }

                if(!need.equals("text")){
                    data = element.attr(need);
                }else{
                    data = element.text();
                }

                Method method = object.getDeclaredMethod(attrName, String.class);
                //System.out.printf("attr-name:%s, data:%s \n",attrName,data);
                method.invoke(instance, data);


            }
            System.out.println(instance.toString());
            HtmlParserImpl.saveObject(instance);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InterruptedException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Mapping mapping: mappings){
            result.append(mapping.toString());
        }
        return "SingleObjectParser{" +
                "className='" + className + '\'' +
                ", mappings=" + result.toString() +
                '}';
    }
}
