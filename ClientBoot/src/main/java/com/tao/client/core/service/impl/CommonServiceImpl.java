package com.tao.client.core.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tao.dubbo.core.entry.InputObject;
import com.tao.dubbo.core.entry.OutputObject;
import com.tao.dubbo.core.exception.DubboException;
import com.tao.dubbo.core.service.ICommonService;
import com.tao.dubbo.core.util.SystemConstant;

@Service
public class CommonServiceImpl implements ICommonService, BeanFactoryAware{
	
	private static final String DESC = "CLIENT通用处理业务接口";
	private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);
	private static BeanFactory factory;

	@Override
	@SuppressWarnings(value= {"rawtypes", "unchecked"})
	public OutputObject invoke(InputObject input) {
		LOG.info("DESC={},开始处理业务,请求接口:[{}.{}],请求参数:{}", DESC, input.getService(), input.getMethod(), JSON.toJSONString(input.getParams()));
		OutputObject out = new OutputObject();
		try {
			if(StringUtils.isEmpty(input.getService()) || StringUtils.isEmpty(input.getMethod())) {
				throw new DubboException("接口参数不完整");
			}
			Object object = factory.getBean(input.getService());
			Method mth = object.getClass().getMethod(input.getMethod(), InputObject.class, OutputObject.class);
			mth.invoke(object, input, out);
			List<JSONObject> retList = new ArrayList<JSONObject>();
			for(Object obj : out.getRetList()) {
				retList.add(JSON.parseObject(JSON.toJSONString(obj)));
			}
			out.setRetList(retList);
		}catch (NoSuchBeanDefinitionException e) {
			out.setRetCode(SystemConstant.ERRORCODE);
			out.setRetMsg("没有对应的实现");
		}catch(NoSuchMethodError e) {
			out.setRetCode(SystemConstant.ERRORCODE);
			out.setRetMsg("没有对应的方法");
		}catch (Exception e) {
			LOG.error("DESC={},通用CLIENT信息异常:{},错误堆栈信息:{}", DESC, e.getMessage(), e);
			out.setRetCode(SystemConstant.ERRORCODE);
			out.setRetMsg(e.getMessage());
		}
		LOG.info("DESC={},业务处理结束,请求接口:[{}.{}],返回编码:{},返回信息:{}", DESC, input.getService(), input.getMethod(), out.getRetCode(),out.getRetParams());
		return out;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = beanFactory;
	}
	
}
