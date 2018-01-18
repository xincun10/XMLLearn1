package com.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestSax {

	@Test
	public void testSax() throws Exception
	{
//		printAll();
		printFirstName();
	}
	
	//��ȡ��һ��nameԪ��ֵ
	public static void printFirstName() throws Exception
	{
		/*
		 * 1.��������������
		 * 2.����������
		 * 3.ִ��parse����
		 * 4.�Լ�����һ����̳�DefaultHandler
		 * 5.��д���������������
		 */
		//��������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		//����������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//ִ��parse����
		saxParser.parse("src/p1.xml", new MyDefault3());
	}
	
	//��ȡ����nameԪ��ֵ
	public static void printName() throws Exception
	{
		/*
		 * 1.��������������
		 * 2.����������
		 * 3.ִ��parse����
		 * 4.�Լ�����һ����̳�DefaultHandler
		 * 5.��д���������������
		 */
		//��������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		//����������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//ִ��parse����
		saxParser.parse("src/p1.xml", new MyDefault2());
	}
	
	//��ӡ�����ĵ�
	public static void printAll() throws Exception
	{
		/*
		 * 1.��������������
		 * 2.����������
		 * 3.ִ��parse����
		 * 4.�Լ�����һ����̳�DefaultHandler
		 * 5.��д���������������
		 */
		//��������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		//����������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//ִ��parse����
		saxParser.parse("src/p1.xml", new MyDefault1());
	}
	
}

//ʵ�ֻ�ȡ��һ��nameԪ�ص�ֵ
class MyDefault3 extends DefaultHandler
{
	boolean flag = false;
	int idx = 1;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		//�ж�qName�Ƿ���nameԪ��
		if("name".equals(qName) && idx==1)
		{
			flag = true;
		}
	}	
	@Override
	public void characters(char[] ch, int start, int length) 
			throws SAXException {
		//��flagֵ��trueʱ����ʾ������nameԪ��
		if(flag==true)
		{
			System.out.print(new String(ch, start, length));
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//��flag���ó�false����ʾnameԪ�ؽ���
		if("name".equals(qName))
		{
			flag = false;		
			idx++;
		}
	}
}

//ʵ�ֻ�ȡ���е�nameԪ�ص�ֵ
class MyDefault2 extends DefaultHandler
{
	boolean flag = false;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		//�ж�qName�Ƿ���nameԪ��
		if("name".equals(qName))
		{
			flag = true;
		}
	}	
	@Override
	public void characters(char[] ch, int start, int length) 
			throws SAXException {
		//��flagֵ��trueʱ����ʾ������nameԪ��
		if(flag==true)
		{
			System.out.print(new String(ch, start, length));
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//��flag���ó�false����ʾnameԪ�ؽ���
		if("name".equals(qName))
		{
			flag = false;			
		}
	}
}

//�Լ�����һ��DefaultHandler�࣬��ӡ�����ĵ�
class MyDefault1 extends DefaultHandler
{

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		System.out.print("<"+qName+">");
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.print(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.print("</"+qName+">");
	}
	
}