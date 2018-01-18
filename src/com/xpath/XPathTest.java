package com.xpath;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.junit.Test;

import com.utils.Dom4jUtils;

public class XPathTest {
	
	@Test
	public void testXPath() throws Exception
	{
//		getAllName();
		getSingleName();
	}
	
	//ʹ��xpathʵ�֣���ȡ��һ��p1�����name��ֵ
	public static void getSingleName() throws Exception
	{
		/*
		 * 1.�õ�document
		 * 2.ֱ��ʹ��selectSingleNode�����õ����е�nameԪ��
		 * 		-xpath://p1[@id1="test"]/name
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Node name1 = document.selectSingleNode("//p1[@id1='test']/name");
		String s = name1.getText();
		System.out.println(s);
	}

	//��ѯxml������nameԪ�ص�ֵ
	public static void getAllName() throws Exception
	{
		/*
		 * 1.�õ�document
		 * 2.ֱ��ʹ��selectNodes("//name")�����õ����е�nameԪ��
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		//�õ����е�nameԪ��
		List<Node> list = document.selectNodes("//name");
		//����list����
		for(Node node:list)
		{
			//node��ÿһ��nameԪ��
			String s = node.getText();
			System.out.println(s);
		}
	}
}
