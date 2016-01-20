package com.bxtel.sms.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//com/bxtel/sms/showadd.do
//com/bxtel/sms/showdetail.do
//com/bxtel/sms/showedit.do
//com/bxtel/sms/showsearch.do
//com/bxtel/sms/showpagelist.do
//com/bxtel/sms/showlist.do
//com/bxtel/sms/doadd.do
//com/bxtel/sms/doupdate.do
//com/bxtel/sms/dodelete.do
//http://localhost:8080/sms/search
//@Controller("/")

@Controller
@RequestMapping(value = "/sms")
public class SmsController extends MultiActionController {
    //org.springframework.web.servlet.view.InternalResourceViewResolver
	//org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping
	//WebMvcAutoConfiguration
	//AnnotationMethodHandlerAdapter
	//SimpleControllerHandlerAdapter
	//AnnotationMethodHandlerAdapter
	//org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
	//WebMvcConfigurationSupport
	//SimpleUrlHandlerMapping
	private static final Log logger = LogFactory.getLog(SmsController.class);
    
//	@RequestMapping("/")
//	public String index(Map<String, Object> model) {
//		return "index";
//	}
//	
	@RequestMapping("/")
	public ModelAndView index(Map<String, Object> model) {
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
    @RequestMapping(value = "search")
    @ResponseBody
    public String search()  throws Exception {
//    	DispatcherServlet  aa;
    	return "hello";
	}
    
}