package dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomTest {
	public void parser(String path){
		//create the parser class factory
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		//create the parser factory
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			//parser the xml file and create the tree doc
			Document doc = builder.parse(path);
			//get the root
			Element eRoot = doc.getDocumentElement();
			
			//get the tag's name/ tagName or nodeName both okay
//			System.out.println(eRoot.getNodeName());
			
			//get the child node list
			NodeList secondList = eRoot.getChildNodes();
			for(int i=0;i<secondList.getLength();i++){
				//get the every node
				Node nodeSecond = secondList.item(i);
				//filter the empty elements
				//get the tag(element node)
				if( nodeSecond.getNodeType() == Node.ELEMENT_NODE ){
					//get the attribute's name
					String name = nodeSecond.getNodeName();
					//get the attribute value
					NamedNodeMap map = nodeSecond.getAttributes();
					Node value = map.getNamedItem("id");
//					System.out.println(value.getNodeType());
//					System.out.println(nodeSecond.getNodeType());
					System.out.println(name+":"+value);
				}
				
			}
			//get the all "name"
			NodeList nameNode = doc.getElementsByTagName("name");
			System.out.println(nameNode.getLength());
			//get the second "name" node
			Node n1 = nameNode.item(0);
			System.out.println(n1.getTextContent());
			//get the parent
			Node parent = n1.getParentNode();
			System.out.println(
					parent.getAttributes()//get the parent's all attributes
						.getNamedItem("id")//get the "id" attribute
							.getNodeValue());//get the attribute's value
			//remove the node
			parent.removeChild(n1);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DomTest().parser("src/XML/Student.xml");
	}
}
