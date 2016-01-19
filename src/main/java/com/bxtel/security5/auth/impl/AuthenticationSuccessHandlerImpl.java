package com.bxtel.security5.auth.impl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import com.bxtel.commons.Response;
import com.bxtel.security5.auth.IAuthenticationResponse;
import com.bxtel.security5.auth.IAuthenticationSuccessHandler;
import com.bxtel.security5.filter.RememberMeFiilter;
import com.bxtel.security5.filter.SecurityConfig;
import com.bxtel.user.model.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dinamica.coder.ThreeDesHelper2;
import dinamica.guid.Guid;
import dinamica.util.HttpHelper;
import dinamica.util.JsonHelper;
@Component
public class AuthenticationSuccessHandlerImpl implements IAuthenticationSuccessHandler{
	private static final Log logger = LogFactory.getLog(AuthenticationSuccessHandlerImpl.class);
	private RequestCache requestCache = new HttpSessionRequestCache();
	//返回到登录之前页面
	boolean backToPageBeforeLogin=true;
	public boolean isBackToPageBeforeLogin() {
		return backToPageBeforeLogin;
	}
	public String getFixedSucessUrl(HttpServletRequest request)
	{
		if(HttpHelper.judgeIsWeiXin(request))
		{
			 return SecurityConfig.weixinentrypoint;
		}else if(HttpHelper.judgeIsMoblie(request))
		{
			 return SecurityConfig.mobileentrypoint;
		}else{
			 return SecurityConfig.entrypoint;
		}
	}
	private String getRedirectUrl(HttpServletRequest request,HttpServletResponse response) {
		//1.优先返回系统固定的url
		logger.debug("isBackToPageBeforeLogin():"+isBackToPageBeforeLogin());
		if(!isBackToPageBeforeLogin())
		{
			String fixedurl=request.getContextPath()+getFixedSucessUrl(request);;
			logger.debug("fixedurl:"+fixedurl);
			return fixedurl;
		}
		//2.返回页面指定的url
		String redirecturl=request.getParameter("redirecturl");
		if(!dinamica.util.StringUtil.isEmptyOrWhitespace(redirecturl))
		{
			logger.debug("redirecturl:"+redirecturl);
			return redirecturl;
		}
		//3.返回被拦截时保存的url
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		//登陆前的页面
		if(savedRequest!=null)
		{
			//被拦截地址里面包含redirecturl，就返回它
			String[] redirecturlarr=savedRequest.getParameterMap().get("redirecturl");
			if(redirecturlarr!=null && redirecturlarr.length>0)
			{
				//如果指定了页面，按照指定页面优先
				return request.getContextPath()+redirecturlarr[0];
			}
			//返回到之前的页面
			String url=savedRequest.getRedirectUrl();
			logger.debug("saverequesturl:"+url);
			requestCache.removeRequest(request, response);
			return url;
		}
		return request.getContextPath()+getFixedSucessUrl(request);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,IAuthenticationResponse authentication) throws IOException, ServletException {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if(isAjax)
		{//返回json格式
			Response<String> kv=new Response<String>();  
 			kv.setReturncode("00000000");
			kv.setReturnmsg("登录成功");
			kv.setData(Guid.getUniqueId());
			outputJson(response, kv);
		}else
		{
			//重定向网页
//			
//			User user=(User)authentication.getUserData();
//			String autologin = request.getParameter("autologin");// true 自动登录
//			if ("true".equals(autologin)) {
//				int seconds = 7 * 24 * 60 * 60;// 保存7天
//				long expiretime = System.currentTimeMillis() + seconds;
//				String cookievalue = null;
//				String cookievaluebase64 = null;
//				try {
//					cookievalue = new Long(expiretime).toString() + RememberMeFiilter.DELIMITER+ new String(Base64.encode(user.getMobile().getBytes())) + RememberMeFiilter.DELIMITER+ ThreeDesHelper2.encode(user.getPassword());
//					cookievaluebase64 = new String(Base64.encode(cookievalue.getBytes()));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				Cookie cookie = new Cookie(RememberMeFiilter.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, cookievaluebase64);
//				cookie.setMaxAge(Integer.MAX_VALUE);
//				cookie.setPath(RememberMeFiilter.getCookiePath(request));
//				response.addCookie(cookie);
//			} else {
//				Cookie cookie = new Cookie(RememberMeFiilter.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
//				cookie.setMaxAge(0);
//				cookie.setPath(getCookiePath(request));
//				response.addCookie(cookie);
//			}
//			
			String sucessurl = getRedirectUrl(request, response);
			logger.debug("sucessurl:"+sucessurl);
			response.sendRedirect(sucessurl);
		}
	}
	private String getCookiePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		return contextPath.length() > 0 ? contextPath : "/";
	}
	private void outputJson(HttpServletResponse response, Response<String> kv)throws IOException, JsonGenerationException, JsonMappingException {
		response.setContentType("application/json;charset=UTF-8");  
		//response.setContentType("text/plain;charset=UTF-8");  
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "no-cache");  
		response.setDateHeader("Expires", 0);  
		PrintWriter out = response.getWriter();
		ObjectMapper om=JsonHelper.getObjectMapperInstance();
		out.print(om.writeValueAsString(kv)); 
		out.flush();
		out.close();
	}
}
