package com.jaxp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class TestJaxp {

	@Test
	public void parseXml() throws Exception {
//		selectAll();
//		selectSin();
//		addSex();
//		modifySex();
		delSex();
	}
	
	//遍历结点，把xml中的所有元素名称打印出来
	public static void listElement() throws Exception
	{
		/*
		 * 1、创建解析器工厂
		 * 2.根据解析器工厂创建解析器
		 * 3.解析xml返回document
		 * ===使用递归实现===
		 * 4.得到根结点
		 * 5.得到根结点子节点
		 * 6.得到根结点子节点的子节点
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		//编写一个方法实现遍历操作
		list1(document);
	}
	//递归遍历的方法
	private static void list1(Node node) {
		//判断是元素类型时才对其打印
		if(node.getNodeType()==Node.ELEMENT_NODE)
		{
			System.out.println(node.getNodeName());			
		}
		//得到一层子节点
		NodeList list = node.getChildNodes();
		//遍历list
		for(int i=0; i<list.getLength(); i++)
		{
			list1(list.item(i));
		}
	}

	//删除<sex>nan</sex>节点
	public static void delSex() throws Exception
	{
		/*
		 * 1、创建解析器工厂
		 * 2.根据解析器工厂创建解析器
		 * 3.解析xml返回document
		 * 4.获取sex元素
		 * 5.获取sex的父结点getParentNode()
		 * 6.使用父结点删除removeChild()
		 * 7.回写xml
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		Node sex1 = document.getElementsByTagName("sex").item(0);
		//得到sex1的父结点
		Node p1 = sex1.getParentNode();
		p1.removeChild(sex1);
		//回写xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
	}
	
	//修改第一个p1下面的sex内容是nan
	public static void modifySex() throws Exception
	{
		/*
		 * 1、创建解析器工厂
		 * 2.根据解析器工厂创建解析器
		 * 3.解析xml返回document
		 * 4.得到sex item方法
		 * 5.修改sex里面的值  setTextContent方法
		 * 6.回写xml
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		NodeList list = document.getElementsByTagName("sex");
		Node sex1 = list.item(0);
		sex1.setTextContent("nan");
		//回写
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
	}
	
	// 在第一个p1下面（末尾）添加<sex>nv</sex>
	public static void addSex() throws Exception
	{
		/*
		 * 1、创建解析器工厂
		 * 2.根据解析器工厂创建解析器
		 * 3.解析xml返回document
		 * 4.得到第一个p1
		 * 	-得到所有p1，使用item方法下标得到
		 * 5.创建sex标签 createElement
		 * 6.创建文本createTextNode
		 * 7.把文本添加到sex下面appendChild
		 * 8.把sex添加到第一个p1下面
		 * 9.回写xml
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		//得到所有的p1
		NodeList list = document.getElementsByTagName("p1");
		Node p1 = list.item(0);
		//创建标签
		Element sex1 = document.createElement("sex");
		//创建文本
		Text text1 = document.createTextNode("nv");
		//把文本添加到sex1下面
		sex1.appendChild(text1);
		//把sex1添加到p1下面
		p1.appendChild(sex1);
		//回写xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
	}

	//查询xml中第一个name元素的值
	public static void selectSin() throws Exception
	{
		/*
		 * 1、创建解析器工厂
		 * 2.根据解析器工厂创建解析器
		 * 3.解析xml返回document
		 * 4.得到所有的name元素
		 * 5.使用返回集合里面方法item，下标获取具体的元素
		 * 6.得到具体的值，使用getTextContent方法
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		NodeList list = document.getElementsByTagName("name");
		Node name1 = list.item(0);
		String s1 = name1.getTextContent();
		System.out.println(s1);
	}
	
	// 查询所有name元素的值
	public static void selectAll() throws Exception {
		/*
		 * 1、创建解析器工厂
		 * 2.根据解析器工厂创建解析器
		 * 3.解析xml返回document
		 * 4.得到所有的name元素
		 * 5.返回集合，遍历集合，得到每一个name元素
		 */
		//创建解析器工厂
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		//创建解析器
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		//解析xml返回document
		Document document = builder.parse("src/person.xml");
		//得到所有的name元素
		NodeList list = document.getElementsByTagName("name");
		//遍历集合
		for(int i=0; i<list.getLength(); i++)
		{
			Node name1 = list.item(i);
			//得到name元素里面的值
			String s = name1.getTextContent();
			System.out.println(s);
		}
	}

}
