package com.ccol.mybatis.mapper;

import com.ccol.mybatis.bean.Person;

public interface PersonMapper {
	Person selectPerson(int pid);
}
