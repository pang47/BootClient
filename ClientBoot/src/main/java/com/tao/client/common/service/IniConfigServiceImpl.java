package com.tao.client.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tao.client.common.dao.IniConfigMapper;
import com.tao.client.common.model.IniConfigDTO;
import com.tao.dubbo.core.entry.InputObject;
import com.tao.dubbo.core.entry.OutputObject;
import com.tao.dubbo.core.exception.DubboException;
import com.tao.dubbo.core.util.SystemConstant;

@Service
public class IniConfigServiceImpl {
	
	@Autowired
	private IniConfigMapper iniConfigMapper;
	
	@SuppressWarnings(value={"unchecked","rawtypes"})
	public void getIniConfig(InputObject input, OutputObject out) {
		try {
			Map<String, String> queryParams = input.getParams();
			if(StringUtils.isEmpty(queryParams.get("ini_type")) || StringUtils.isEmpty(queryParams.get("ini_class")))
				throw new DubboException("请求参数不全");
			List<IniConfigDTO> queryList = iniConfigMapper.getIniConfigByIniTypeAndIniClass(queryParams.get("ini_type"), queryParams.get("ini_class"));
			out.setRetList(queryList);
		}catch (Exception e) {
			out.setRetCode(SystemConstant.ERRORCODE);
			out.setRetMsg(e.getMessage());
			out.setRetParams(null);
		}
	}
}
