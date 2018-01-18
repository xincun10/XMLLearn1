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
	
	//使用xpath实现：获取第一个p1下面的name的值
	public static void getSingleName() throws Exception
	{
		/*
		 * 1.得到document
		 * 2.直接使用selectSingleNode方法得到所有的name元素
		 * 		-xpath://p1[@id1="test"]/name
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		Node name1 = document.selectSingleNode("//p1[@id1='test']/name");
		String s = name1.getText();
		System.out.println(s);
	}

	//查询xml中所有name元素的值
	public static void getAllName() throws Exception
	{
		/*
		 * 1.得到document
		 * 2.直接使用selectNodes("//name")方法得到所有的name元素
		 */
		Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
		//得到所有的name元素
		List<Node> list = document.selectNodes("//name");
		//遍历list集合
		for(Node node:list)
		{
			//node是每一个name元素
			String s = node.getText();
			System.out.println(s);
		}
	}
}
