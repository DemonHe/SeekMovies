package impls.parser;

import impls.parser.extractor.UrlExtractor;
import impls.parser.mapping.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by douchengfeng on 2017/6/1.
 * html解析器
 */
public class ParserCore {
    private List<ObjectParser> parserList = new ArrayList<>();
    private List<UrlExtractor> extractors = new ArrayList<>();

    public ParserCore(String xmlPath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(xmlPath));

        Element root = document.getRootElement();
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element element = (Element) it.next();

            String name = element.getName();
            switch (name) {
                case "multi-objects":
                    buildMultiObjectParser(element);
                    break;
                case "single-object":
                    buildSingleObjectParser(element);
                    break;
                case "extract-url":
                    buildExtractUrl(element);
                    break;
                case "complex-objects":
                    buildComplexObject(element);
                    break;
            }
        }
    }

    private void buildExtractUrl(Element element){
        UrlExtractor extractor = new UrlExtractor();
        for(Iterator it = element.elementIterator(); it.hasNext();){
            Element ptr = (Element) it.next();
            if(ptr.getName().equals("location")){
                extractor.add(ptr.getText(), ptr.attributeValue("type"));
            }else if(ptr.getName().equals("prefix")){
                extractor.setPreFix(ptr.getText());
            }else if(ptr.getName().equals("paser_class")){
                extractor.setLinkParser(ptr.getText());
            }

        }
        extractors.add(extractor);
    }

    public List<String> parse(String html){
        System.out.println("=============================提取到的数据=======================");
        for(ObjectParser objectParser: parserList){
            objectParser.parse(html);
        }
        System.out.println("=============================提取到的url=======================");
        List<String> urls = new LinkedList<>();
        for(UrlExtractor extractor: extractors){
            urls.addAll(extractor.extract(html));
        }
        return urls;
    }

    private void buildComplexObject(Element element){
        ComplexObjectParser parser = new ComplexObjectParser();
        Element ptr = element;
        List<Mapping> mappings = new ArrayList<>();
        List<String> root = new ArrayList<>();
        parser.setClassName(ptr.elementText("class"));
        while(true){
            root.add(ptr.elementText("root-tag"));
            addMappings(ptr, mappings);
            if(ptr.element("loop") == null){
                break;
            }
            ptr = ptr.element("loop");
            mappings.add(null);
        }

        parser.setRoot(root);
        parser.setMappings(mappings);
       // System.out.println(parser.toString());
        parserList.add(parser);
    }

    private void addMappings(Element element, List<Mapping> mappings){
        Element tmp = element.element("attributes");
        if(tmp == null){
            return;
        }
        for(Iterator it = tmp.elementIterator(); it.hasNext();){
            Element ptr = (Element) it.next();
            Mapping mapping = new Mapping();
            Element tag = ptr.element("tag");
            Element attrName = ptr.element("attr-name");
            mapping.setAttrName(attrName.getText());
            mapping.setNeed(tag.attributeValue("need"));

            mapping.setTag(tag.getText());
            mappings.add(mapping);
        }
    }

    private void buildSingleObjectParser(Element element){
        parserList.add(getSingleObject(element));
    }

    private void buildMultiObjectParser(Element element){
        String contextPath = element.element("root-tag").getText();
        Element childElement = element.element("single-object");
        MultiObjectParser parser = getMultiObject(childElement, contextPath);
        parserList.add(parser);
    }


    private MultiObjectParser getMultiObject(Element element, String contextPath){
        List<Mapping> mappings = new ArrayList<>();
        String className = element.element("class").getText();

        addMappings(element, mappings);

        return new MultiObjectParser(mappings, className, contextPath);
    }

    private SingleObjectParser getSingleObject(Element element){
        List<Mapping> mappings = new ArrayList<>();
        String path = element.element("class").getText();

        addMappings(element, mappings);
        return new SingleObjectParser(path, mappings);
    }

    public void printParseList(){
        for(ObjectParser objectParser: parserList){
            System.out.println(objectParser.toString());
        }
    }
}
