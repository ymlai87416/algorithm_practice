package ctci.moderate;
import java.util.*;

public class XMLEncoding {

    HashMap<String, Integer> attributeMap;
    public String xmlEncoding(Element root, HashMap<String, Integer> attributeMap){

        StringBuilder sb = new StringBuilder();
        this.attributeMap = attributeMap;
        encode(root, sb);

        return sb.toString();

    }

    public String encode(Element root, StringBuilder sb){

        sb.append(attributeMap.get(root.tag));
        sb.append(" ");

        if(root.attributeList != null){
            for(int i=0; i<root.attributeList.size(); ++i){
                Attribute attr = root.attributeList.get(i);
                sb.append(attributeMap.get(attr.tag));
                sb.append(" ");
                sb.append(attr.value);
                sb.append(" ");
            }
        }
        sb.append(0);
        sb.append(" ");

        if(root.value != null){
            sb.append(root.value);
            sb.append(" ");
        }
        else{
            if(root.children != null)
                for(int i=0; i<root.children.size(); ++i){
                    Element child = root.children.get(i);
                    encode(child, sb);
                    sb.append(" ");
                }
        }

        sb.append(0);

        return sb.toString();

    }


    public static void main(String[] args) {
        String input ="<family lastName=\"McDowell\" state=\"CA\">\n" +
                "  <person firstName=\"Gayle\">Some Message</person>\n" +
                "</family>";

        Element root = new Element("family");
        root.attributeList = List.of(new Attribute("lastName", "McDowell"), new Attribute("state", "CA"));
        Element person = new Element("person");
        person.attributeList= List.of(new Attribute("firstName", "Gayle"));
        person.value = "Some Message";
        root.children = new ArrayList<>();
        root.children.add(person);

        HashMap<String, Integer> attributeMap = new HashMap<>();
        attributeMap.put("family", 1);
        attributeMap.put("person", 2);
        attributeMap.put("firstName", 3);
        attributeMap.put("lastName", 4);
        attributeMap.put("state", 5);

        XMLEncoding test = new XMLEncoding();
        System.out.println(test.xmlEncoding(root, attributeMap));
    }
}

class Element{
    String tag;
    List<Attribute> attributeList;
    List<Element> children;
    String value;

    public Element(String tag){
        this.tag = tag;
        attributeList = new ArrayList<>();
        children = null;
        value = null;
    }
}

class Attribute{
    String tag;
    String value;
    public Attribute(String tag, String value){
        this.tag = tag;
        this.value =value;
    }
}