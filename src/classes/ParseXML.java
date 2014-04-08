package classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParseXML {
	
	public static HashMap<String,String> creator = new HashMap<String,String>();
	
	public static String parse(String inputLine) throws ParserConfigurationException, SAXException, IOException{
		    String type=null;
		    String returnUrl=null;

		    //Dom parser for xml
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new InputSource(new StringReader(inputLine)));
	        doc.getDocumentElement().normalize();
	        System.out.println("root of xml file:" + doc.getDocumentElement().getNodeName());
	        System.out.println("root of xml file:" + doc.getDocumentElement().getTextContent());
            
	        //Get all child nodes
	        NodeList nodes = doc.getDocumentElement().getChildNodes();

	        
	    for (int i = 0; i < nodes.getLength(); i++) {
	        Node node = nodes.item(i);
	        Element element = (Element) node;
	        if(element.getTagName().equals("type")){
	        	 type = element.getTextContent();
	        	System.out.println("type is : " + type);

	        }
	        if(element.getTagName().equals("returnUrl")){
	        	returnUrl = element.getTextContent();
	        }
	        
	        if(element.getTagName().equals("creator")){
	        	setCreator(element);
	        }
	        

	     }
	    if(creator.containsKey("firstName")){
	    	System.out.println(creator.get("firstName"));
	    }
	     return type;
	        
	}
	
	public static void setCreator(Element e){
		NodeList n = e.getChildNodes();
		for (int i = 0; i < n.getLength(); i++) {
		        Node node = n.item(i);
		        Element elem = (Element) node;
		    	System.out.println("name:value" + elem.getNodeName() + ":" + elem.getTextContent());
		    	creator.put(elem.getNodeName(), elem.getTextContent());
		}
		
	}
	

}
