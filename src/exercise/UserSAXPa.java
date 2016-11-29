package exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserSAXPa {
	public static void main(String[] args) {
		new SAXp().saxParser("src/exercise/xml/user.xml");
	}
}

class SAXp {
	public void saxParser(String path) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
			MyHandler mh = new MyHandler();
			parser.parse(path, mh);
			Map<String, String> userMap = mh.getUserMap();
			for (Entry e : userMap.entrySet()) {
				System.out.println(e.getKey() + ":" + e.getValue());
			}
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
}

class MyHandler extends DefaultHandler {
	private User user;
	private Map<String, String> userMap;
	private boolean userF;

	public MyHandler() {
		this.userMap = new HashMap<String, String>();
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("user".equals(qName)) {
			userF = true;
			String name = attributes.getValue("name");
			user = new User();
			user.setName(name);
		}
		System.out.println("localName:"+localName);
		System.out.println("qName:"+qName);
		System.out.println("uri:"+uri);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("user".equals(qName)) {
			userF = false;
			addMap(user.getName(), user.getFee() + "");
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		if (userF && str.trim().length() != 0) {
			user.setFee(Float.parseFloat(str));
		}
	}

	public void addMap(String key, String value) {
		if (userMap.containsKey(key)) {
			userMap.put(key, (Float.parseFloat(value) + Float.parseFloat(userMap.get(key))) + "");
		} else {
			userMap.put(key, value);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, String> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, String> userMap) {
		this.userMap = userMap;
	}

}
// class Domp{}