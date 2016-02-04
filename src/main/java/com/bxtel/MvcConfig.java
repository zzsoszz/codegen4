package com.bxtel;

import java.util.List;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import web.core.CP_InitializingInterceptor;
import web.core.CP_PropertyEditorRegistrar;
import web.core.CP_SimpleMappingExceptionResolver;


/*
 * Spring Boot 静态资源处理
 * http://blog.csdn.net/isea533/article/details/50412212
 * WebMvcConfigurer
 * DelegatingWebMvcConfiguration
 * WebMvcConfigurerAdapter
 */

//@EnableWebMvc
//DelegatingWebMvcConfiguration
//https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
	
	private final MyWebMvcConfigurerComposite configurers = new MyWebMvcConfigurerComposite();
	
	
	@Autowired(required = false)
	public void setConfigurers(List<WebMvcConfigurer> configurers) {
		if (configurers == null || configurers.isEmpty()) {
			return;
		}
		this.configurers.addWebMvcConfigurers(configurers);
	}


	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		this.configurers.addInterceptors(registry);
	}

	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		this.configurers.configureContentNegotiation(configurer);
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		this.configurers.configureAsyncSupport(configurer);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		this.configurers.configurePathMatch(configurer);
	}

//	@Override
//	protected void addViewControllers(ViewControllerRegistry registry) {
//		this.configurers.addViewControllers(registry);
//	}

	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry) {
		this.configurers.configureViewResolvers(registry);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		this.configurers.addResourceHandlers(registry);
	}

	@Override
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		this.configurers.configureDefaultServletHandling(configurer);
	}

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		this.configurers.addArgumentResolvers(argumentResolvers);
	}

	@Override
	protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		this.configurers.addReturnValueHandlers(returnValueHandlers);
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		this.configurers.configureMessageConverters(converters);
	}

	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		this.configurers.extendMessageConverters(converters);
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		this.configurers.addFormatters(registry);
	}

	@Override
	protected Validator getValidator() {
		return this.configurers.getValidator();
	}

	@Override
	protected MessageCodesResolver getMessageCodesResolver() {
		return this.configurers.getMessageCodesResolver();
	}

	@Override
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		this.configurers.configureHandlerExceptionResolvers(exceptionResolvers);
	}

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		this.configurers.addCorsMappings(registry);
	}

	private static final Logger logger = Logger.getLogger(MvcConfig.class);
	
//	
//	
//	
//	private static final Logger logger = Logger
//			.getLogger(MvcConfig.class);
//	
//    /**                                                          
//    * 描述 : <注册试图处理器>. <br> 
//    *<p> 
//    	<使用方法说明>  
//     </p>                                                                                                                                                                                                                                                
//    * @return                                                                                                      
//    */  
	
    @Bean
    public ViewResolver viewResolver() {
    	logger.info("ViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");//WEB-INF/jsp/
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
//    
//    /**                                                          
//    * 描述 : <注册消息资源处理器>. <br> 
//    *<p> 
//    	<使用方法说明>  
//     </p>                                                                                                                                                                                                                                                
//    * @return                                                                                                      
//    */  
//    @Bean
//    public MessageSource messageSource() {
//    	logger.info("MessageSource");
//    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//    	messageSource.setBasename("config.messages.messages");
//    	return messageSource;
//    }
//    
//    /**                                                          
//    * 描述 : <注册servlet适配器>. <br> 
//    *<p> 
//    	<只需要在自定义的servlet上用@Controller("映射路径")标注即可>  
//     </p>                                                                                                                                                                                                                                                
//    * @return                                                                                                      
//    */  
//    @Bean
//    public HandlerAdapter servletHandlerAdapter(){
//    	logger.info("HandlerAdapter");
//    	return new SimpleServletHandlerAdapter();
//    }
//    
//    /**                                                          
//    * 描述 : <本地化拦截器>. <br> 
//    *<p> 
//    	<使用方法说明>  
//     </p>                                                                                                                                                                                                                                                
//    * @return                                                                                                      
//    */  
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor(){
//    	logger.info("LocaleChangeInterceptor");
//    	return new LocaleChangeInterceptor();
//    }
//    
//    /**                                                          
//    * 描述 : <基于cookie的本地化资源处理器>. <br> 
//    *<p> 
//    	<使用方法说明>  
//     </p>                                                                                                                                                                                                                                                
//    * @return                                                                                                      
//    */  
//    @Bean(name="localeResolver")
//    public CookieLocaleResolver cookieLocaleResolver(){
//    	logger.info("CookieLocaleResolver");
//    	return new CookieLocaleResolver();
//    }
//    
//    /**                                                          
//    * 描述 : <注册自定义拦截器>. <br> 
//    *<p> 
//    	<使用方法说明>  
//     </p>                                                                                                                                                                                                                                                
//    * @return                                                                                                      
//    */  
//    @Bean
//    public CP_InitializingInterceptor initializingInterceptor(){
//    	logger.info("CP_InitializingInterceptor");
//    	return new CP_InitializingInterceptor();
//    }
//    
//    
//    /**                                                          
//     * 描述 : <RequestMappingHandlerMapping需要显示声明，否则不能注册自定义的拦截器>. <br> 
//     *<p> 
//     	<这个比较奇怪,理论上应该是不需要的>  
//      </p>                                                                                                                                                                                                                                                
//     * @return                                                                                                      
//     */ 
    @Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    	logger.info("RequestMappingHandlerMapping");
		return super.requestMappingHandlerMapping();
	}
//    
//    
//    
//    
//    
//
//    /**                                                          
//    * 描述 : <添加拦截器>. <br> 
//    *<p> 
//    	<使用方法说明>  
//     </p>                                                                                                                                                                                                                                                
//    * @param registry                                                                                                      
//    */  
//    @Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//    	logger.info("addInterceptors start");
//		registry.addInterceptor(localeChangeInterceptor());
//		registry.addInterceptor(initializingInterceptor());
//		logger.info("addInterceptors end");
//	}
//
//    /**                                                          
//     * 描述 : <HandlerMapping需要显示声明，否则不能注册资源访问处理器>. <br> 
//     *<p> 
//     	<这个比较奇怪,理论上应该是不需要的>  
//      </p>                                                                                                                                                                                                                                                
//     * @return                                                                                                      
//     */ 
    @Bean
	public HandlerMapping resourceHandlerMapping() {
    	logger.info("HandlerMapping");
    	return super.resourceHandlerMapping();
    }
    
    /**                                                          
     * 描述 : <资源访问处理器>. <br> 
     *<p> 
     	<可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>  
      </p>                                                                                                                                                                                                                       
     * @param registry                                                                                                      
     */  
//	@Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		logger.info("addResourceHandlers");
//        registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/assets/");
//    }
    
	/**                                                          
	* 描述 : <文件上传处理器>. <br> 
	*<p> 
		<使用方法说明>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		logger.info("CommonsMultipartResolver");
		return new CommonsMultipartResolver();
	}
	
	/**                                                          
	* 描述 : <异常处理器>. <br> 
	*<p> 
		<系统运行时遇到指定的异常将会跳转到指定的页面>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean(name="exceptionResolver")
	public CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		logger.info("CP_SimpleMappingExceptionResolver");
		CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver= new CP_SimpleMappingExceptionResolver();
		simpleMappingExceptionResolver.setDefaultErrorView("common_error");
		simpleMappingExceptionResolver.setExceptionAttribute("exception");
		Properties properties = new Properties();
		properties.setProperty("java.lang.RuntimeException", "common_error");
		simpleMappingExceptionResolver.setExceptionMappings(properties);
		return simpleMappingExceptionResolver;
	}
	
	 /**                                                          
     * 描述 : <RequestMappingHandlerAdapter需要显示声明，否则不能注册通用属性编辑器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		logger.info("RequestMappingHandlerAdapter");
    	return super.requestMappingHandlerAdapter();
	}
	
	/**                                                          
	* 描述 : <注册通用属性编辑器>. <br> 
	*<p>
		<这里只增加了字符串转日期和字符串两边去空格的处理>  
	 </p>                                                                                                                                                                                                                          
	* @return                                                                                         
	*/
	@Override
	protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
		logger.info("ConfigurableWebBindingInitializer");
		ConfigurableWebBindingInitializer initializer = super.getConfigurableWebBindingInitializer();
		CP_PropertyEditorRegistrar register = new CP_PropertyEditorRegistrar();
		register.setFormat("yyyy-MM-dd");
		initializer.setPropertyEditorRegistrar(register);
		return initializer;
	}
	
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
    
}

