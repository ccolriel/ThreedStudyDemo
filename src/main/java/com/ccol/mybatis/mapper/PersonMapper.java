package com.ccol.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.ccol.mybatis.bean.Person;

public interface PersonMapper {
	//@Select("select * from person where pid = #{id}")  使用注解方式 不用Mpper.xml执行sql
	Person selectPerson(int pid);
}
