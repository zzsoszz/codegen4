package com.bxtel.security5.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import com.bxtel.security5.auth.IAuthenticationResponse;
import com.bxtel.security5.auth.exceiption.AccountIsNotAuthenticatedException;

import dinamica.guid.Guid;
//
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;


@Component
public class MySecurityContextHolder {
	
	@Autowired
	CacheManager cacheManager;
	
	public IAuthenticationResponse getSecurityContext(HttpServletRequest  request)
	{
		boolean longtime = "longtime".equals(request.getHeader("sessionstrategy"));
		if(longtime)
		{
			String token=request.getHeader("token");
			if(token==null || "".equals(token))
			{
				throw new AccountIsNotAuthenticatedException("token is not exits!");
			}
			ValueWrapper cache = cacheManager.getCache("securitycontext").get(token);
			if(token==null || "".equals(token))
			{
				throw new AccountIsNotAuthenticatedException("token is invalid!");
			}
			IAuthenticationResponse resp=(IAuthenticationResponse)cache.get();
			return resp;
		}else{
			HttpSession ses = request.getSession(false);
			if(ses!=null)
			{
				IAuthenticationResponse auth =(IAuthenticationResponse) ses.getAttribute("securitycontext");
				if(auth!=null)
				{
					return auth;
				}
			}
		}
		return null;
	}
	public void setSecurityContext(IAuthenticationResponse  auth,HttpServletRequest request, HttpServletResponse response)
	{
		boolean longtime = "longtime".equals(request.getHeader("sessionstrategy"));
		if(longtime)
		{
			String token=Guid.getUUID();
			response.addHeader("token", token);
			cacheManager.getCache("securitycontext").put(token,auth);
		}
		else
		{
			HttpSession ses = request.getSession(true);
			if(ses!=null)
			{
				ses.setAttribute("securitycontext",auth);
			}
		}
	}
}
