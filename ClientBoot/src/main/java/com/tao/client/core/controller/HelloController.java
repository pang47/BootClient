package com.tao.client.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试成功用例
 * @author chentao
 *
 */
@RestController
public class HelloController {
	@RequestMapping(value="/")
	public String hello() {
		return "success";
	}
}
