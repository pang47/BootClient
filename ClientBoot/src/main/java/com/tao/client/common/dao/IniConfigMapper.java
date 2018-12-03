package com.tao.client.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tao.client.common.model.IniConfigDTO;



@Mapper
public interface IniConfigMapper {
	List<IniConfigDTO> findAll();
	
	List<IniConfigDTO> getIniConfigByIniTypeAndIniClass(String iniType, String iniClass);
}
