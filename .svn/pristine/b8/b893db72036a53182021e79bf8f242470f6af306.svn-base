package sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dom4j.Student;

public class SAXPa {
	public void parser(String path) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
			MyHandler mh = new MyHandler();
			//
			parser.parse(path, mh);
			for(Student stu:mh.getStuList()){
				System.out.println(stu.toString());
			}
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SAXPa().parser("src/XML/Student.xml");
	}
}

class MyHandler extends DefaultHandler {
	private Student stu;
	private List<Student> stuList = new ArrayList<Student>();
	private boolean stuF;
	private boolean nameF;
	private boolean ageF;
	@Override
	public void startDocument() throws SAXException {
		System.out.println("doc start");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("doc end");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println(qName + " element start");
		// get the stu tag
		if ("stu".equals(qName)) {
			stuF = true;
			stu = new Student();
			String id = attributes.getValue("id");
			stu.setId(Long.parseLong(id));
		}
		else if("name".equals(qName)){
			nameF = true;
		}
		else if("age".equals(qName)){
			ageF = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("stu".equals(qName)) {
			stuF = false;
			stuList.add(stu);
		}
		else if("name".equals(qName)){
			nameF = false;
		}
		else if("age".equals(qName)){
			ageF = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("handle the empty");
		String str = new String(ch, start, length);
		/*if (str.trim().length() != 0) {
			System.out.println("Document's value:" + str);
		} 
		else */
		if(nameF==true){
			stu.setName(str);
		}
		else if(ageF==true){
			stu.setAge(Integer.parseInt(str));
		}
	}

	public List<Student> getStuList() {
		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

}
