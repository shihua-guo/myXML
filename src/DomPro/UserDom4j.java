package DomPro;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import dom4j.DoT;
import dom4j.Sta;
import dom4j.Tea;
import dom4j.User;
import exercise.Student;

public class UserDom4j {
	private Map<String, User> map = new HashMap<String, User>();

	public UserDom4j() {
	}

	public UserDom4j(String path){
		//construct the parser
		SAXReader reader = new SAXReader();
		//read file
		File file = new File(path);
		try {
			if(!file.exists() && !file.canRead()){
				throw new Exception("file not exists or file can't read");
			}
			//then trans to the tree document
			Document doc= reader.read(file);
			//then get the root
			Element eRoot = doc.getRootElement();
			//then get the second element
			for(Object o1:eRoot.elements()){
				Element studentE = (Element)o1;
				//new Student
				Student stu = new Student();
				//get set the id 
				String id = studentE.attribute("id").getValue();
				stu.setId(Long.parseLong(id));
				//then get the third element (the attribute)
				for(Object o2:studentE.elements()){
					Element eAtt = (Element)o2;
					if("name".equals(eAtt.getName())){//name element
						//get and set name
						String name = eAtt.getText();
						stu.setName(name);
					}
					else if("age".equals(eAtt.getName())){
						String age = eAtt.getText();
						stu.setAge(Integer.parseInt(age));
					}
					else if("adress".equals(eAtt.getName())){
						Element cityE = eAtt.element("city");
						String city = cityE.getText();
						stu.setCity(city);
					}
				}
				//then set in the instance
				if(u instanceof User){//first need to judge is the Object
					u.init(pro);
					//then store in thr map
					map.put(name, u);//name is the class tag's name; u is the instance
				}
				//make sure the one properties
				pro.clear();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DoT getDoT() {
		return (DoT)map.get("DoT");
	}

	public Tea getTea() {
		return (Tea)map.get("Tea");
	}

	public Sta getSta() {
		return (Sta)map.get("Sta");
	}
	public static void main(String[] args) {
		UserDom4j udom = new UserDom4j("src/XML/User.xml");
		DoT d = udom.getDoT();
		System.out.println(d.getPort());
		Sta s = udom.getSta();
		System.out.println(s.getId());
		Tea t = udom.getTea();
		System.out.println(t.getPort());
	}
}
