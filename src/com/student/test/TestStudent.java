package com.student.test;

import org.junit.Test;

import com.student.service.StuService;
import com.student.vo.Student;

public class TestStudent {

	@Test
	public void testSelect() throws Exception
	{
		Student stu = StuService.selectStu("330");
		System.out.println(stu);
	}
	
	public void testDel() throws Exception
	{
		StuService.delStu("101");
	}
	
	public void testStu() throws Exception
	{
		Student stu = new Student();
		stu.setAge("12");
		stu.setId("330");
		stu.setName("hebe");
		StuService.addStu(stu);
	}
}
