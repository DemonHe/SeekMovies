package impls.parser.mapping;

import impls.parser.HtmlParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by douchengfeng on 2017/6/1.
 * 多对象解析器
 */
public class MultiObjectParser extends AbstractObjectParser {
    private List<Mapping> mappings = new ArrayList<>();
    private String className;
    private String root;

    public MultiObjectParser(List<Mapping> mappings, String className, String root) {
        this.mappings = mappings;
        this.className = className;
        this.root = root;
    }

    @Override
    public void parse(String html) {
        Document doc = Jsoup.parseBodyFragment(html);
        Element parent = getElement(root, doc);
        for(Element child: parent.children()){

            Element ptr;
            try {
                Class<?> object = Class.forName(className);
                Object result = object.newInstance();
                for(Mapping mapping: mappings){
                    ptr = child;
                    String tag = mapping.getTag();
                    String need = mapping.getNeed();
                    String attrName = mapping.getAttrName();
                    String data;
                    if (!tag.equals("/")) {
                        if(tag.startsWith("$")){
                            StringBuilder builder = new StringBuilder(tag);
                            builder.setCharAt(0, ' ');
                            tag = builder.toString();
                            ptr = getElement(tag, doc);
                        }else{
                            ptr = getElement(tag, child);
                        }
                    }

                    if(!need.equals("text")){
                        data = ptr.attr(need);
                    }else{
                        //System.out.println(mapping);
                        data = ptr.text();
                    }
                    Method method = object.getDeclaredMethod(attrName, String.class);
                    method.invoke(result, data);

                }
                System.out.println(result.toString());
                HtmlParserImpl.saveObject(result);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InterruptedException | InstantiationException | NullPointerException | IndexOutOfBoundsException e) {
                //e.printStackTrace();
            }

        }
    }


    @Override
    public String toString() {
        StringBuilder mappingString = new StringBuilder();
        for(Mapping mapping: mappings){
            mappingString.append(mapping.toString());
        }
        return "MultiObjectParser{" +
                "mappings=" + mappingString.toString() +
                ", className='" + className + '\'' +
                ", root='" + root + '\'' +
                '}';
    }
}
