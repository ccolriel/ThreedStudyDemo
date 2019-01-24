package com.ccol.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ccol.mybatis.bean.Person;

/**
 * 
 * @author Administrator
 * 创建SqlSessionFactory对象
 */
public class Demo01 {
	public static void main(String[] args) {
		method1();
	}
	
	
	/**
	 * 通过XML创建SqlSessionFactory对象
	 */
	private static void method1() {
		String resource = "com/ccol/mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			SqlSession session = sqlSessionFactory.openSession();
			Person person = session.selectOne("com.ccol.mybatis.bean.Person.selectPerson", 101);
			System.out.println(person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
