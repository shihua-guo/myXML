package exercise;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Dom4jStu {
	List<Student> stuList;

	public Dom4jStu() {
	}

	public List<Student> parser(String path) {
		this.stuList = new ArrayList<Student>();
		// construct the parser
		SAXReader reader = new SAXReader();
		// read file
		File file = new File(path);
		try {
			if (!file.exists() && !file.canRead()) {
				throw new Exception("file not exists or file can't read");
			}
			// then trans to the tree document
			Document doc = reader.read(file);
			// then get the root
			Element eRoot = doc.getRootElement();
			// then get the second element
			for (Object o1 : eRoot.elements()) {
				Element studentE = (Element) o1;
				// new Student
				Student stu = new Student();
				// get set the id
				String id = studentE.attribute("id").getValue();
				stu.setId(Long.parseLong(id));
				// then get the third element (the attribute)
				for (Object o2 : studentE.elements()) {
					Element eAtt = (Element) o2;
					if ("name".equals(eAtt.getName())) {// name element
						// get and set name
						String name = eAtt.getText();
						stu.setName(name);
					} else if ("age".equals(eAtt.getName())) {
						String age = eAtt.getText();
						stu.setAge(Integer.parseInt(age));
					} else if ("address".equals(eAtt.getName())) {
						Element cityE = eAtt.element("city");
						String city = cityE.getText();
						stu.setCity(city);
					}
				}
				stuList.add(stu);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stuList;
	}
	public static void main(String[] args) {
		Dom4jStu domStu = new Dom4jStu();
		List<Student> stuList = domStu.parser("src/exercise/xml/student.xml");
		for(Student stu:stuList){
			System.out.println(stu.toString());
		}
	}
	
}
