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

	//���
	public static void addStu(Student stu) throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.��ȡ�����ڵ�
		 * 4.�ڸ��ڵ����洴��stu��ǩ
		 * 5.��stu��ǩ�����������id name age
		 * 6.��id name age�����������ֵ
		 * 7.��дxml
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
	//ɾ��
	public static void delStu(String id) throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.��ȡ�����е�id
		 * 		ʹ��xpath //id ����list����
		 * 4.����list����
		 * 5.�жϼ��������id�ʹ��ݵ�id�Ƿ���ͬ
		 * 6.�����ͬ����id���ڵ�stuɾ��
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
	//��ѯ
	public static Student selectStu(String id) throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.��ȡ�����е�id
		 * 		ʹ��xpath //id ����list����
		 * 4.����list����
		 * 5.�жϼ��������id�ʹ��ݵ�id�Ƿ���ͬ
		 * 6.�����ͬ���Ȼ�ȡ��id�ĸ��ڵ�stu
		 * 7.ͨ��stu��ȡ��name ageֵ
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
