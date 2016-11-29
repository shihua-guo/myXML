package exercise;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DomCreator {
	private Map<String,String> userMap;
	public DomCreator(){
		userMap = new HashMap<String,String>();
	}
	public void creator(String fileName){
		UserDOMPa udp = new UserDOMPa();
		udp.parser("src/exercise/xml/user.xml");
		userMap = udp.getUserMap();
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// if use the namesapce default is false
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;

		try {
			builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new EntityResolver() {

				@Override
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					byte[] b = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes();
					return new InputSource(new ByteArrayInputStream(b));
				}
			});
			Document doc = builder.newDocument();
			// create the element and set the attribute
			Element root = doc.createElement("records");
			for(Entry e:userMap.entrySet()){
				Element recordE = doc.createElement("record");
				Element nameE = doc.createElement("name");
				nameE.setTextContent((String)e.getKey());
				Element priceE = doc.createElement("price");
				priceE.setTextContent((String)e.getValue());
				// set the relation stu and name,age
				recordE.appendChild(nameE);
				recordE.appendChild(priceE);
				root.appendChild(recordE);
			}
			// set the relation doc and stu
			doc.appendChild(root);
			// transformer:trans Tree to XML
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer tf = transFactory.newTransformer();
			// set the encoding
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// set if format
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			// trans the XML and write into the file
			// write the doc node into original file(cover)
			tf.transform(new DOMSource(doc), new StreamResult(fileName));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new DomCreator().creator("src/exercise/xml/recordsDom.xml");
	}
}
