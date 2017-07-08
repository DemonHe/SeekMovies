package impls.parser.mapping;

import impls.parser.HtmlParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by douchengfeng on 2017/6/11.
 * 超级复杂的对象的转化器
 */
public class ComplexObjectParser extends AbstractObjectParser {
    private String[] root;
    private Mapping[] mappings;
    private String className;


    public void setRoot(List<String> root) {
        this.root = root.toArray(new String[root.size()]);
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings.toArray(new Mapping[mappings.size()]);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void parse(String html) {
        Document doc = Jsoup.parseBodyFragment(html);
        List<Element> elements = getAllChildes(getElements(root[0], doc));

        for(Element element: elements){
            parse(doc, element, 0, 0, new HashMap<String,String>());

        }
    }

    private void parse(Document doc, Element rootElement, int rootPos, int mappingPos, Map<String, String> values){
        for (;mappingPos < mappings.length && mappings[mappingPos] != null; mappingPos ++){
            Element ptr = rootElement;
            String tag = mappings[mappingPos].getTag();
            String need = mappings[mappingPos].getNeed();
            String attrName = mappings[mappingPos].getAttrName();
            String data;
            if (!tag.equals("/")) {
                if(tag.startsWith("$")){
                    StringBuilder builder = new StringBuilder(tag);
                    builder.setCharAt(0, ' ');
                    tag = builder.toString();
                    ptr = getElement(tag, doc);
                }else{
                    ptr = getElement(tag, rootElement);
                }
            }
            //System.out.println(rootElement);
            //System.out.println(ptr);
            if(ptr == null){
                return;
            }
            if(!need.equals("text")){
                data = ptr.attr(need);
            }else{
                data = ptr.text();
            }
            //System.out.println(data);
            values.put(attrName, data);
        }
        rootPos ++;
        if(rootPos >= root.length){
            buildObject(values);
            return;
        }

        List<Element> elements = getAllChildes(getElements(root[rootPos], rootElement));
        for(Element element: elements){
            HashMap<String, String> childValues = new HashMap<>();
            childValues.putAll(values);
            parse(doc, element, rootPos, mappingPos + 1, childValues);
        }
    }

    private List<Element> getAllChildes(List<Element> elements){
        List<Element> childes = new LinkedList<>();
        for(Element element: elements){
            childes.addAll(element.children());
        }
        return childes;
    }


    private void buildObject(Map<String, String> values){
        try {
            Class<?> object = Class.forName(className);
            Object instance = object.newInstance();
            for(String methodName: values.keySet()){
                Method method = object.getDeclaredMethod(methodName, String.class);
                method.invoke(instance, values.get(methodName));
            }
            System.out.println(instance.toString());
            HtmlParserImpl.saveObject(instance);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InterruptedException | InstantiationException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();

        }

    }

    @Override
    public String toString() {
        return "ComplexObjectParser{" +
                "root=" + Arrays.toString(root) +
                ", mappings=" + Arrays.toString(mappings) +
                ", className='" + className + '\'' +
                '}';
    }
}
