package com.student.service;

import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.student.vo.Student;

public class StuService {

	//添加
	public static void addStu(Student stu) throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.获取到根节点
		 * 4.在根节点上面创建stu标签
		 * 5.在stu标签上面依次添加id name age
		 * 6.在id name age上面依次添加值
		 * 7.回写xml
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/student.xml");
		Element root = document.getRootElement();
		Element stu1 = root.addElement("stu");
		Element id1 = stu1.addElement("id");
		Element name1 = stu1.addElement("name");
		Element age1 = stu1.addElement("age");
		id1.setText(stu.getId());
		name1.setText(stu.getName());
		age1.setText(stu.getAge());
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/student.xml"), format);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	//删除
	public static void delStu(String id) throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.获取到所有的id
		 * 		使用xpath //id 返回list集合
		 * 4.遍历list集合
		 * 5.判断集合里面的id和传递的id是否相同
		 * 6.如果相同，把id所在的stu删除
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/student.xml");
		Element root = document.getRootElement();
		List<Node> list = root.selectNodes("//id");
		for(Node node:list)
		{
			if(id.equals(node.getText()))
			{
				Element pnode = node.getParent().getParent();
				pnode.remove(node.getParent());
			}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/student.xml"), format);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	//查询
	public static Student selectStu(String id) throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.获取到所有的id
		 * 		使用xpath //id 返回list集合
		 * 4.遍历list集合
		 * 5.判断集合里面的id和传递的id是否相同
		 * 6.如果相同，先获取到id的父节点stu
		 * 7.通过stu获取到name age值
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/student.xml");
		Element root = document.getRootElement();
		List<Node> list = root.selectNodes("//id");
		Student student = new Student();
		for(Node node:list)
		{
			if(id.equals(node.getText()))
			{
				Element stu1 = node.getParent();
				Element age1 = stu1.element("age");
				Element name1 = stu1.element("name");
				student.setId(id);
				student.setAge(age1.getText());
				student.setName(name1.getText());
			}
		}
		return student;
	}
}
