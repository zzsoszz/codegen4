package com.bxtel.security5.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.filter.GenericFilterBean;
import dinamica.util.matcher.RequestMatcher;

public class VFilterChainProxy extends GenericFilterBean {
	//WebMvcAutoConfiguration aaa;
	public Map<RequestMatcher, VFilterChain> filterChainMap;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		VFilterChain vchain=getFilter(request);
		vchain.doFilter(request, response);
		//返回调用父链org.apache.tomcat.websocket.server.WsFilter@3cfe978c
		chain.doFilter(request, response);
	}
	public void setFilterChainMap(Map<RequestMatcher, VFilterChain> filterChainMap) {
		this.filterChainMap=filterChainMap;
	}
	public VFilterChain getFilter(ServletRequest request)
	{
		for(Entry<RequestMatcher, VFilterChain> one:this.filterChainMap.entrySet())
		{
			if(one.getKey().matches((HttpServletRequest)request))
			{
				return one.getValue();
			}
		}
		return null;
	}
}

