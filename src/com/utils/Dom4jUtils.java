package com.utils;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtils {
	//封装路径常量
	public static final String PATH = "src/p1.xml";

	//返回Document
	public static Document getDocument(String path)
	{
		try {
			//创建解析器
			SAXReader saxReader = new SAXReader();
			//得到document
			Document document = saxReader.read(path);
			return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//回写xml的方法
	public static void xmlWriters(Document document, String path)
	{
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
