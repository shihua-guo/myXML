package dom;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import dom4j.Student;

public class StudentServiceImpl implements StudentSerive {
	@Override
	public List<Student> getStudent(String fileName) {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// if use the namesapce default is false
		factory.setNamespaceAware(true);
		// create the builder
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			// when we parse the xml,may contains dtd
			// sometimes we need to import the dtd
			// sometimes we don't need the dtd
			// so we can ignore the dtd
			// this method can do this.
			builder.setEntityResolver(new EntityResolver() {
				@Override
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					byte[] b = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>".getBytes();
					return new InputSource(new ByteArrayInputStream(b));
				}
			});
			// parser to the tree doc
			Document doc = builder.parse(fileName);
			// through the tag's name then filter the empty tags
			NodeList nodeList = doc.getElementsByTagName("stu");
			System.out.println(nodeList.getLength());
			// get the child
			for (int i = 0; i < nodeList.getLength(); i++) {
				// new Student and put the value
				Student stu = new Student();
				Node node = nodeList.item(i);
				stu.setId(Long.parseLong(node.getAttributes().getNamedItem("id").getNodeValue()));
				// go on the next child
				NodeList nameAgeList = node.getChildNodes();
				for (int j = 0; j < nameAgeList.getLength(); j++) {
					Node nameAgeNode = nameAgeList.item(j);
					String name = nameAgeNode.getNodeName();
					if ("name".equals(name)) {
						stu.setName(nameAgeNode.getTextContent());
					} else if ("age".equals(name)) {
						stu.setAge(Integer.parseInt(nameAgeNode.getTextContent()));
					}
				}
				list.add(stu);
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
		return list;
	}

	@Override
	public void addStudent(String fileName, Student stu) {
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
			Document doc = builder.parse(fileName);
			System.out.println(stu.toString());
			// create the element and set the attribute
			Element eStu = doc.createElement("stu");
			eStu.setAttribute("id", stu.getId() + "");
			Element eName = doc.createElement("name");
			eName.setTextContent(stu.getName());
			Element eAge = doc.createElement("age");
			eAge.setTextContent(stu.getAge() + "");
			// set the relation stu and name,age
			eStu.appendChild(eName);
			eStu.appendChild(eAge);
			// set the relation doc and stu
			doc.getDocumentElement().appendChild(eStu);
			// transformer:trans Tree to XML
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer tf = transFactory.newTransformer();
			// set the encoding
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// set if format 换行
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			// trans the XML and write into the file
			// write the doc node into original file(cover)
			tf.transform(new DOMSource(doc), new StreamResult(fileName));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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

	@Override
	public void delStudent(String fileName, Student stu) {
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
			Document doc = builder.parse(fileName);
			// get the "stu" nodeList
			NodeList nodeList = doc.getElementsByTagName("stu");
			// travel the nodeList
			for (int i = 0; i < nodeList.getLength(); i++) {
				// get the stu node
				Node node = nodeList.item(i);
				// get the
				String id = node.getAttributes().getNamedItem("id").getNodeValue();
				if (id.equals(stu.getId() + "")) {
					node.getParentNode().removeChild(node);
				}
			}
			// transformer:trans Tree to XML
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer tf = transFactory.newTransformer();
			// set the encoding
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// set if format
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			// trans the XML and write into the file
			// write the doc node into original file(cover)
			tf.transform(new DOMSource(doc), new StreamResult(fileName));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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

	@Override
	public void updateStudent(String fileName, Student stu) {
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
			Document doc = builder.parse(fileName);
			// get the "stu" nodeList
			NodeList nodeList = doc.getElementsByTagName("stu");
			// travel the nodeList
			for (int i = 0; i < nodeList.getLength(); i++) {
				// get the stu node
				Node node = nodeList.item(i);
				System.out.println(node.getNodeName());
				// get the attribute id's value
				String id = node.getAttributes().getNamedItem("id").getNodeValue();
				if (id.equals(stu.getId() + "")) {//if is the id 
					//get the childList 
					NodeList childList = node.getChildNodes();
					//change the child's value
					for (int j = 0; j < childList.getLength(); j++) {
						Node nameAge = childList.item(j);
						System.out.println(nameAge.getNodeName());
						if ("name".equals(nameAge.getNodeName())) {
							nameAge.setTextContent(stu.getName());
						}else if("age".equals(nameAge.getNodeName())){
							nameAge.setTextContent(stu.getAge()+"");
						}
					}
				}
			}
			// transformer:trans Tree to XML
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer tf = transFactory.newTransformer();
			// set the encoding
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// set if format
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			// trans the XML and write into the file
			// write the doc node into original file(cover)
			tf.transform(new DOMSource(doc), new StreamResult(fileName));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		StudentServiceImpl stuService = new StudentServiceImpl();
		String fileName = "src/XML/Student.xml";
		// Student stuTemp = new Student(4,"cat",24);
		// System.out.println("==========add");
		// stuSerive.addStudent(fileName, stuTemp);
		List<Student> stuList = stuService.getStudent(fileName);
		for (Student stu : stuList) {
			System.out.println(stu.toString());
		}
//		Student stuDel = new Student(4, "alan", 23);
//		System.out.println("===========del");
//		stuSerive.delStudent(fileName, stuDel);
		
		Student stuUpdate = new Student(124,"cat",28);
		stuService.updateStudent(fileName, stuUpdate);
		System.out.println("===========update");
		stuList = stuService.getStudent(fileName);
		for (Student stu : stuList) {
			System.out.println(stu.toString());
		}

	}

}
