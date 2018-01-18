package com.dom4j;

import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.utils.Dom4jUtils;

public class TestDom4j {
	
	@Test
	public void testDom4j() throws Exception
	{
//		selectSec();
//		addSex();
//		addAgeBefore();
//		modifyAge();
//		delSchool();
		getValues();
	}
	
	//��ȡ��һ��p1���������id1��ֵ
	public static void getValues() throws Exception
	{
		/*
		 * 1.�õ�document
		 * 2.�õ����ڵ�
		 * 3.�õ���һ��p1Ԫ��
		 * 4.�õ�p1���������ֵ
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		//�õ�p1���������ֵ
		String value = p1.attributeValue("id1");
		System.out.println(value);
	}
	
	//ɾ����һ��p1�����<school>edu.cn</school>Ԫ��
	public static void delSchool() throws Exception
	{
		/*
		 * 1.�õ�document
		 * 2.�õ���һ��p1Ԫ��
		 * 3.�õ���һ��p1�����school
		 * 4.ʹ��p1ɾ��school
		 * 5.��дxml
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		Element school = p1.element("school");
		//ͨ�����ڵ�ɾ��schoolԪ��
		p1.remove(school);
		Dom4jUtils.xmlWriters(document, Dom4jUtils.PATH);
	}
	
	//�޸ĵ�һ��p1�����ageԪ�ص�ֵ<age>30</age>
	public static void modifyAge() throws Exception
	{
		/*
		 * 1.�õ�document
		 * 2.�õ���һ��p1Ԫ��
		 * 3.�õ���һ��p1�����age
		 * 4.�޸�ֵΪ30
		 * 5.��дxml
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		Element age1 = p1.element("age");
		age1.setText("30");
		Dom4jUtils.xmlWriters(document, Dom4jUtils.PATH);
	}
	
	//�ڵ�һ��p1�����age��ǩ֮ǰ���<school>edu.cn</school>
	public static void addAgeBefore() throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.��ȡ��һ��p1
		 * 5.��ȡp1��������е�Ԫ��
		 * 	elements()����������list����
		 * 	ʹ��list����ķ��������ض�λ�����Ԫ��
		 * 		����Ԫ�أ���Ԫ�����洴���ı�
		 * 		add(int index, E element)
		 * 		- ��һ��������λ���±꣬��0��ʼ
		 * 		- �ڶ���������Ҫ��ӵ�Ԫ��
		 * 6.��дxml
		 */
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		List<Element> list = p1.elements();
		//����Ԫ��
		Element school = DocumentHelper.createElement("school");
		//��school���洴���ı�
		school.setText("edu.cn");
		//���ض���λ�����
		list.add(1, school);
		//��дxml
		Dom4jUtils.xmlWriters(document, "src/p1.xml");
	}
	
	//�ڵ�һ��p1��ǩĩβ���һ��Ԫ��<sex>nv</sex>
	public static void addSex() throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.��ȡ��һ��p1
		 * 5.��p1�������Ԫ��
		 * 6.��������֮���Ԫ����������ı�
		 * 7.��дxml
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/p1.xml");
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		//��p1����ֱ�����Ԫ��
		Element sex1 = p1.addElement("sex");
		//��sex��������ı�
		sex1.setText("nv");
		//��дxml
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/p1.xml"), format);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	
	//��ȡ�ڶ���nameԪ�ص�ֵ
	public static void selectSec() throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.�õ����е�p1
		 * 5.�����õ��ڶ���p1
		 * 6.�õ��ڶ���p1�����name
		 * 7.�õ�name�����ֵ
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/p1.xml");
		Element root = document.getRootElement();
		//�õ����е�p1
		List<Element> list = root.elements("p1");
		//�õ��ڶ���p1
		Element p2 = list.get(1);
		//�õ�name
		Element name2 = p2.element("name");
		String s = name2.getText();
		System.out.println(s);
	}
	
	//��ȡ��һ��nameԪ�ص�ֵ
	public static void selectSin() throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.�õ�p1
		 * 5.�õ�p1�����name
		 * 6.�õ�name�����ֵ
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/p1.xml");
		Element root = document.getRootElement();
		//�õ���һ��p1
		Element p1 = root.element("p1");
		//�õ���һ��name
		Element name1 = p1.element("name");
		String s = name1.getText();
		System.out.println(s);
	}

	//��ѯxml������nameԪ�ص�ֵ
	public static void selectName() throws Exception
	{
		/*
		 * 1.����������
		 * 2.�õ�document
		 * 3.�õ����ڵ�
		 * 4.�õ�p1
		 * 5.�õ�p1�����name
		 * 6.�õ�name�����ֵ
		 */
		//����������
		SAXReader saxReader = new SAXReader();
		//�õ�document
		Document document = saxReader.read("src/p1.xml");
		//�õ����ڵ�
		Element root = document.getRootElement();
		//�õ�p1
		List<Element> list = root.elements("p1");
		//����list
		for(Element e:list)
		{
			//�õ�ÿ��p1�����nameԪ��
			Element name1 = e.element("name");
			//�õ�name�����ֵ
			String s = name1.getText();
			System.out.println(s);
		}
	}
}
