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
	
	//获取第一个p1里面的属性id1的值
	public static void getValues() throws Exception
	{
		/*
		 * 1.得到document
		 * 2.得到根节点
		 * 3.得到第一个p1元素
		 * 4.得到p1里面的属性值
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		//得到p1里面的属性值
		String value = p1.attributeValue("id1");
		System.out.println(value);
	}
	
	//删除第一个p1下面的<school>edu.cn</school>元素
	public static void delSchool() throws Exception
	{
		/*
		 * 1.得到document
		 * 2.得到第一个p1元素
		 * 3.得到第一个p1下面的school
		 * 4.使用p1删除school
		 * 5.回写xml
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		Element school = p1.element("school");
		//通过父节点删除school元素
		p1.remove(school);
		Dom4jUtils.xmlWriters(document, Dom4jUtils.PATH);
	}
	
	//修改第一个p1下面的age元素的值<age>30</age>
	public static void modifyAge() throws Exception
	{
		/*
		 * 1.得到document
		 * 2.得到第一个p1元素
		 * 3.得到第一个p1下面的age
		 * 4.修改值为30
		 * 5.回写xml
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		Element age1 = p1.element("age");
		age1.setText("30");
		Dom4jUtils.xmlWriters(document, Dom4jUtils.PATH);
	}
	
	//在第一个p1下面的age标签之前添加<school>edu.cn</school>
	public static void addAgeBefore() throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.获取第一个p1
		 * 5.获取p1下面的所有的元素
		 * 	elements()方法，返回list集合
		 * 	使用list里面的方法，在特定位置添加元素
		 * 		创建元素，在元素下面创建文本
		 * 		add(int index, E element)
		 * 		- 第一个参数是位置下标，从0开始
		 * 		- 第二个参数是要添加的元素
		 * 6.回写xml
		 */
		Document document = Dom4jUtils.getDocument("src/p1.xml");
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		List<Element> list = p1.elements();
		//创建元素
		Element school = DocumentHelper.createElement("school");
		//在school下面创建文本
		school.setText("edu.cn");
		//在特定的位置添加
		list.add(1, school);
		//回写xml
		Dom4jUtils.xmlWriters(document, "src/p1.xml");
	}
	
	//在第一个p1标签末尾添加一个元素<sex>nv</sex>
	public static void addSex() throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.获取第一个p1
		 * 5.在p1下面添加元素
		 * 6.在添加完成之后的元素下面添加文本
		 * 7.回写xml
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/p1.xml");
		Element root = document.getRootElement();
		Element p1 = root.element("p1");
		//在p1下面直接添加元素
		Element sex1 = p1.addElement("sex");
		//在sex下面添加文本
		sex1.setText("nv");
		//回写xml
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/p1.xml"), format);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	
	//获取第二个name元素的值
	public static void selectSec() throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.得到所有的p1
		 * 5.遍历得到第二个p1
		 * 6.得到第二个p1下面的name
		 * 7.得到name里面的值
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/p1.xml");
		Element root = document.getRootElement();
		//得到所有的p1
		List<Element> list = root.elements("p1");
		//得到第二个p1
		Element p2 = list.get(1);
		//得到name
		Element name2 = p2.element("name");
		String s = name2.getText();
		System.out.println(s);
	}
	
	//获取第一个name元素的值
	public static void selectSin() throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.得到p1
		 * 5.得到p1下面的name
		 * 6.得到name里面的值
		 */
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("src/p1.xml");
		Element root = document.getRootElement();
		//得到第一个p1
		Element p1 = root.element("p1");
		//得到第一个name
		Element name1 = p1.element("name");
		String s = name1.getText();
		System.out.println(s);
	}

	//查询xml中所有name元素的值
	public static void selectName() throws Exception
	{
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.得到根节点
		 * 4.得到p1
		 * 5.得到p1下面的name
		 * 6.得到name里面的值
		 */
		//创建解析器
		SAXReader saxReader = new SAXReader();
		//得到document
		Document document = saxReader.read("src/p1.xml");
		//得到根节点
		Element root = document.getRootElement();
		//得到p1
		List<Element> list = root.elements("p1");
		//遍历list
		for(Element e:list)
		{
			//得到每个p1下面的name元素
			Element name1 = e.element("name");
			//得到name里面的值
			String s = name1.getText();
			System.out.println(s);
		}
	}
}
