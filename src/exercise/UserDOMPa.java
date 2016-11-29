package exercise;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UserDOMPa {
	private Map<String, String> userMap;

	public UserDOMPa() {
		this.setUserMap(new HashMap<String, String>());
	}

	public void parser(String path) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		User u = new User();
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(path);
			Element eRoot = doc.getDocumentElement();
			NodeList nodeList = eRoot.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					/**
					 * getNamedItem("name")??????
					 */
					String name = node.getAttributes().getNamedItem("name").getNodeValue();
					String fee = node.getTextContent();
					u.setName(name);
					u.setFee(Float.parseFloat(fee));
					addMap(u.getName(), u.getFee() + "");
				}
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addMap(String key, String value) {
		if (getUserMap().containsKey(key)) {
			getUserMap().put(key, (Float.parseFloat(value) + Float.parseFloat(getUserMap().get(key))) + "");
		} else {
			getUserMap().put(key, value);
		}
	}

	public void printMap() {
		for (Entry e : getUserMap().entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}
	
	public static void main(String[] args) {
		UserDOMPa UDP = new UserDOMPa();
		UDP.parser("src/exercise/xml/user.xml");
		UDP.printMap();
	}

	public Map<String, String> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, String> userMap) {
		this.userMap = userMap;
	}
}
