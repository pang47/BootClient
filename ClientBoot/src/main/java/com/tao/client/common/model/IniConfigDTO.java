package com.tao.client.common.model;

import java.io.Serializable;

public class IniConfigDTO implements Serializable {
	private String iniType;
	private String iniTypeName;
	private String iniClass;
	private String iniClassName;
	private String iniCode;
	private String iniCodeValue;

	public String getIniType() {
		return iniType;
	}

	public void setIniType(String iniType) {
		this.iniType = iniType;
	}

	public String getIniTypeName() {
		return iniTypeName;
	}

	public void setIniTypeName(String iniTypeName) {
		this.iniTypeName = iniTypeName;
	}

	public String getIniClass() {
		return iniClass;
	}

	public void setIniClass(String iniClass) {
		this.iniClass = iniClass;
	}

	public String getIniClassName() {
		return iniClassName;
	}

	public void setIniClassName(String iniClassName) {
		this.iniClassName = iniClassName;
	}

	public String getIniCode() {
		return iniCode;
	}

	public void setIniCode(String iniCode) {
		this.iniCode = iniCode;
	}

	public String getIniCodeValue() {
		return iniCodeValue;
	}

	public void setIniCodeValue(String iniCodeValue) {
		this.iniCodeValue = iniCodeValue;
	}

}
