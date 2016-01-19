package com.bxtel.security5.filter;

import java.io.IOException;



import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.bxtel.security5.auth.*;
import com.bxtel.security5.auth.exceiption.AccessDeniedException;
import com.bxtel.security5.auth.exceiption.AccountIsNotAuthenticatedException;
import com.bxtel.security5.auth.exceiption.UserNotLogin;


//public boolean supports(Class<?> clazz) {
//return FilterInvocation.class.isAssignableFrom(clazz);
//}


public class FilterSecurityInterceptorFilter extends GenericFilterBean  {
		
		private RequestCache requestCache = new HttpSessionRequestCache();
		
		private static final Log logger = LogFactory.getLog(FilterSecurityInterceptorFilter.class);
		
		//需要保护的资源
		@Resource
		private ISecurityMetadataSource securityMetadataSource;
		
		@Resource
		MySecurityContextHolder mySecurityContextHolder;
		
		private boolean checkRole=true;
		
		public boolean isCheckRole() {
			return checkRole;
		}
		public void setCheckRole(boolean checkRole) {
			this.checkRole = checkRole;
		}
		private String pathtype="ant";
		
		public String getPathtype() {
			return pathtype;
		}
		public void setPathtype(String pathtype) {
			this.pathtype = pathtype;
		}
		public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			logger.debug("request url :"+servletRequest.getRequestURI());
			//只检查需要角色的资源
			Collection<IConfigAttribute> roles = securityMetadataSource.getAttributes(servletRequest);
			if(roles!=null)
			{
				    IAuthenticationResponse auth =mySecurityContextHolder.getSecurityContext(servletRequest);
					if(auth==null)
					{
						throw new AccountIsNotAuthenticatedException("user is not Authenticated");
					}
					if(isCheckRole())
					{
						Collection<? extends IGrantedAuthority> authorities = auth.getAuthorities();
						if(authorities==null)
						{
						  	throw new AccessDeniedException("Access is denied : user have no privilege in path "+servletRequest.getRequestURI());
						}
						if(!decide(roles,authorities))
						{
							throw new AccessDeniedException("Access is denied");
						}
					}
			}
			else{
				logger.debug("url need not sercurity :"+servletRequest.getRequestURI());
			}
			filterChain.doFilter(request, response);
		}
		
		
		public boolean decide(Collection<IConfigAttribute> configroles,Collection<? extends IGrantedAuthority> authorities)
		{
			for(IConfigAttribute config:configroles)
			{
				Iterator<? extends IGrantedAuthority> it = authorities.iterator();
				while(it.hasNext())
				{
					IGrantedAuthority ga = it.next();
					if(config.getAttribute().equals(ga.getAuthority()))
					{
						return true;
					}
				}
			}
			return false;
		}
}


/*
Spring security防用户重复登录 
使用Spring security如何防止用户的重复登录呢？如果用户账号已登录，这时再进行第二次或多次登录，需要阻止这样的多次登录。

一.在web.xml中配置listener

<listener>
      <listener-class>org.springframework.security.web.session.HttpSessionEventPublisher</listener-class>
</listener>
二.在security.xml中配置Hibernate ORM提供了三种继承映射策略
<session-management>
          <concurrency-control max-sessions="1" error-if-maximum-exceeded="true"/>
</session-management>
max-sessions表示最多允许多少次重复登录。如果没有配置error-if-maximum-exceeded，那么用户账号的第二次登录会使第一次登录失效，而配置了的话，那么第二次登录会被阻止。通常的做法是阻止第二次登录。
*/