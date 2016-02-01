package com.bxtel;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value="/servlet/init-param", loadOnStartup=1, initParams={@WebInitParam(name="param1", value="value1")})  
public class SystemInitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void init() {
		ServletContext   servletContext   =   this.getServletContext();
		Enumeration<String> attrs = servletContext.getAttributeNames();
		while(attrs.hasMoreElements())
		{
			String str = attrs.nextElement();
			System.out.println("servletcontextkey:"+str);
			//key:payController   value:com.bxtel.bxpay.controller.PayController@18c26d7
		}
	}
}
