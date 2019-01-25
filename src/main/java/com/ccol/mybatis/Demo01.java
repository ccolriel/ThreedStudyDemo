package com.ccol.mybatis;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.ccol.mybatis.bean.Person;
import com.ccol.mybatis.mapper.PersonMapper;

/**
 * 
 * @author Administrator
 * 创建SqlSessionFactory对象
 */
public class Demo01 {
	public static void main(String[] args) {
		method3();
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
			Person person = session.selectOne("com.ccol.mybatis.mapper.PersonMapper.selectPerson", 101);  //命名空间+sqlId，入参映射
			System.out.println(person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 不通过xml来创建SqlSessionFactory
	 */
	private static void method2() {
		/*DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(BlogMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		*/
	}
	
	/**
	 * 
	 */
	private static void method3() {
		String resource = "com/ccol/mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			PersonMapper personMapper = session.getMapper(PersonMapper.class);
			Person person = personMapper.selectPerson(101);
			System.out.println(person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
