package com.bxtel.security5.auth.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bxtel.commons.Response;
import com.bxtel.security5.auth.IAuthenticationFailureHandler;
import com.bxtel.security5.auth.exceiption.AuthenticationException;
import com.bxtel.security5.filter.SecurityConfig;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dinamica.http.UrlEncodedQueryString;
import dinamica.util.JsonHelper;

@Component
public class AuthenticationFailureHandlerImpl implements IAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));  
		if(isAjax)
		{
			Response<String> kv=new Response<String>();  
 			kv.setReturncode("00000002");
			kv.setReturnmsg("帐号或密码有误");
			outputJson(response, kv);
		}
		else
		{
			String redirecturl=request.getParameter("redirecturl");
	    	if(redirecturl!=null)
			{
				//优先返回到登陆页面指定的重定向地址
				UrlEncodedQueryString queryString = UrlEncodedQueryString.parse(request.getContextPath()+SecurityConfig.entrypoint);
				queryString.set("redirecturl",redirecturl);
				String url=queryString.toString();
				response.sendRedirect(url);
			}else{
				response.sendRedirect(request.getContextPath()+SecurityConfig.entrypoint);
			}
		}
	}
	
	private void outputJson(HttpServletResponse response, Response<String> kv)
				throws IOException, JsonGenerationException, JsonMappingException {
			response.setContentType("application/json;charset=UTF-8");  
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			ObjectMapper om=JsonHelper.getObjectMapperInstance();
			out.print(om.writeValueAsString(kv)); 
			out.flush();
			out.close();
	}
}
