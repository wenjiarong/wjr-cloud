package com.canal.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Mapper {
	@Insert("${sql}")
	void doOption(@Param("sql") String sql);
}