package com.utils;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtils {
	//��װ·������
	public static final String PATH = "src/p1.xml";

	//����Document
	public static Document getDocument(String path)
	{
		try {
			//����������
			SAXReader saxReader = new SAXReader();
			//�õ�document
			Document document = saxReader.read(path);
			return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//��дxml�ķ���
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
