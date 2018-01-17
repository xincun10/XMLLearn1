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
	
	//������㣬��xml�е�����Ԫ�����ƴ�ӡ����
	public static void listElement() throws Exception
	{
		/*
		 * 1����������������
		 * 2.���ݽ�������������������
		 * 3.����xml����document
		 * ===ʹ�õݹ�ʵ��===
		 * 4.�õ������
		 * 5.�õ�������ӽڵ�
		 * 6.�õ�������ӽڵ���ӽڵ�
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		//��дһ������ʵ�ֱ�������
		list1(document);
	}
	//�ݹ�����ķ���
	private static void list1(Node node) {
		//�ж���Ԫ������ʱ�Ŷ����ӡ
		if(node.getNodeType()==Node.ELEMENT_NODE)
		{
			System.out.println(node.getNodeName());			
		}
		//�õ�һ���ӽڵ�
		NodeList list = node.getChildNodes();
		//����list
		for(int i=0; i<list.getLength(); i++)
		{
			list1(list.item(i));
		}
	}

	//ɾ��<sex>nan</sex>�ڵ�
	public static void delSex() throws Exception
	{
		/*
		 * 1����������������
		 * 2.���ݽ�������������������
		 * 3.����xml����document
		 * 4.��ȡsexԪ��
		 * 5.��ȡsex�ĸ����getParentNode()
		 * 6.ʹ�ø����ɾ��removeChild()
		 * 7.��дxml
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		Node sex1 = document.getElementsByTagName("sex").item(0);
		//�õ�sex1�ĸ����
		Node p1 = sex1.getParentNode();
		p1.removeChild(sex1);
		//��дxml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
	}
	
	//�޸ĵ�һ��p1�����sex������nan
	public static void modifySex() throws Exception
	{
		/*
		 * 1����������������
		 * 2.���ݽ�������������������
		 * 3.����xml����document
		 * 4.�õ�sex item����
		 * 5.�޸�sex�����ֵ  setTextContent����
		 * 6.��дxml
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		NodeList list = document.getElementsByTagName("sex");
		Node sex1 = list.item(0);
		sex1.setTextContent("nan");
		//��д
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
	}
	
	// �ڵ�һ��p1���棨ĩβ�����<sex>nv</sex>
	public static void addSex() throws Exception
	{
		/*
		 * 1����������������
		 * 2.���ݽ�������������������
		 * 3.����xml����document
		 * 4.�õ���һ��p1
		 * 	-�õ�����p1��ʹ��item�����±�õ�
		 * 5.����sex��ǩ createElement
		 * 6.�����ı�createTextNode
		 * 7.���ı���ӵ�sex����appendChild
		 * 8.��sex��ӵ���һ��p1����
		 * 9.��дxml
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		//�õ����е�p1
		NodeList list = document.getElementsByTagName("p1");
		Node p1 = list.item(0);
		//������ǩ
		Element sex1 = document.createElement("sex");
		//�����ı�
		Text text1 = document.createTextNode("nv");
		//���ı���ӵ�sex1����
		sex1.appendChild(text1);
		//��sex1��ӵ�p1����
		p1.appendChild(sex1);
		//��дxml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
	}

	//��ѯxml�е�һ��nameԪ�ص�ֵ
	public static void selectSin() throws Exception
	{
		/*
		 * 1����������������
		 * 2.���ݽ�������������������
		 * 3.����xml����document
		 * 4.�õ����е�nameԪ��
		 * 5.ʹ�÷��ؼ������淽��item���±��ȡ�����Ԫ��
		 * 6.�õ������ֵ��ʹ��getTextContent����
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse("src/person.xml");
		NodeList list = document.getElementsByTagName("name");
		Node name1 = list.item(0);
		String s1 = name1.getTextContent();
		System.out.println(s1);
	}
	
	// ��ѯ����nameԪ�ص�ֵ
	public static void selectAll() throws Exception {
		/*
		 * 1����������������
		 * 2.���ݽ�������������������
		 * 3.����xml����document
		 * 4.�õ����е�nameԪ��
		 * 5.���ؼ��ϣ��������ϣ��õ�ÿһ��nameԪ��
		 */
		//��������������
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		//����������
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		//����xml����document
		Document document = builder.parse("src/person.xml");
		//�õ����е�nameԪ��
		NodeList list = document.getElementsByTagName("name");
		//��������
		for(int i=0; i<list.getLength(); i++)
		{
			Node name1 = list.item(i);
			//�õ�nameԪ�������ֵ
			String s = name1.getTextContent();
			System.out.println(s);
		}
	}

}
