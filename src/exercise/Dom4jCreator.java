package exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4jCreator {
	private Map<String, String> userMap;

	public Dom4jCreator() {
		this.userMap = new HashMap<String, String>();
	}

	public void creator() {
		UserDOMPa udp = new UserDOMPa();
		udp.parser("src/exercise/xml/user.xml");
		userMap = udp.getUserMap();
		
		// create a empty document initial the memory
		Document doc = DocumentHelper.createDocument();
		// doc is the father of the root node
		Element root = doc.addElement("records");
		// add attributes to the root
//		root.addAttribute("id", "test");
		for(Entry e:userMap.entrySet()){
			// add the second tag (children)
			Element recordE = root.addElement("record");
			// add the third tag
			Element nameE = recordE.addElement("name");
			nameE.setText((String)e.getKey());
			
			Element priceE = recordE.addElement("price");
			priceE.setText((String)e.getValue());
		}
		// set the output format
		OutputFormat outFor = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream(new File("src/exercise/xml/records.xml")), outFor);
			xmlWriter.write(doc);
			xmlWriter.flush();
			xmlWriter.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Dom4jCreator().creator();
	}
}
