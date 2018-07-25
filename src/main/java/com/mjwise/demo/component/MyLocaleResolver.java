package com.mjwise.demo.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 *可以在链接上携带区域信息
 */

public class MyLocaleResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(javax.servlet.http.HttpServletRequest request) {
		String l = request.getParameter("local");
		Locale local =Locale.getDefault();
		if(!StringUtils.isEmpty(l)){
			String[] split = l.split("_");
			local = new Locale(split[0],split[1]);

		}
		return local;
	}

	@Override
	public void setLocale(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, Locale locale) {

	}
}
