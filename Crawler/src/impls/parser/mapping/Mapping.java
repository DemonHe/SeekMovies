package impls.parser.mapping;

/**
 * Created by douchengfeng on 2017/6/1.
 * 解析器中的一个简单单元
 */
public class Mapping {
    private String attrName;
    private String tag;
    private String need;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        char firstChar = attrName.charAt(0);
        if(firstChar >= 'a' && firstChar <= 'z'){
            firstChar += 'A'- 'a';
            StringBuilder strBuilder = new StringBuilder(attrName);
            strBuilder.setCharAt(0, firstChar);
            attrName = "set" + strBuilder.toString();

        }

        this.attrName = attrName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "attrName='" + attrName + '\'' +
                ", tag='" + tag + '\'' +
                ", need='" + need + '\'' +
                '}';
    }
}
