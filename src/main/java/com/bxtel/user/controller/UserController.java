package com.bxtel.user.controller;
import com.bxtel.user.bo.UserBO;

import com.bxtel.user.model.User;
import com.bxtel.user.vo.RegistInfo;
import com.bxtel.user.vo.YzmInfo;
import com.bxtel.commons.Request;
import com.bxtel.commons.Response;
import com.bxtel.commons.SearchData;
import com.bxtel.exception.BusinessException;

import dinamica.coder.MD5Helper;
import dinamica.coder.RSACoderTest;
import dinamica.guid.Guid;
import dinamica.util.JsonHelper;

import com.bxtel.security5.auth.IAuthenticationManager;
import com.bxtel.security5.auth.IAuthenticationSuccessHandler;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springside.modules.web.Servlets;
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

@Controller
@RequestMapping(value = "/user")
public class UserController extends MultiActionController {
	
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	public UserBO  bo;
    @Autowired
	private IAuthenticationManager  authenticationManager;
	@Autowired
	private IAuthenticationSuccessHandler successHandler = null;
	@Autowired
	CacheManager cacheManager;

    @RequestMapping(value = "docreate")
    @ResponseBody
    public Response docreate(@RequestBody Request<RegistInfo> req,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	Response resp=new Response();
    	try
    	{
    		System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(req));;
    		//RSACoderTest.decode()
    		String pwdmd5=MD5Helper.md5(req.getData().getUser().getPassword());
    		req.getData().getUser().setPassword(pwdmd5);
    		ValueWrapper yzm=cacheManager.getCache("yzm").get(req.getData().getUser().getMobile());
    		if(!req.getData().getYzm().equals(yzm.get()))
    		{
    			resp.setReturncode("00000002");
        		resp.setReturnmsg("验证码错误!");
    		}
    		bo.add(req.getData().getUser());
    		resp.setReturncode("00000000");
    		resp.setReturnmsg("处理成功!");
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		resp.setReturncode("00000001");
    		resp.setReturnmsg("系统异常");
    	}
    	return resp;
	}
    
    //
    @RequestMapping(value = "dosendyzm")
    @ResponseBody
    public Response dosendyzm(@RequestBody Request<String> req,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	Response resp=new Response();
    	try
    	{
    		String yzm=Guid.genRandom(6);
    		System.out.println("yzm:"+yzm);
    		cacheManager.getCache("yzm").put(req.getData(),yzm);
    		bo.sendyzm(req.getData(),yzm);
    		resp.setReturncode("00000000");
    		resp.setReturnmsg("处理成功!");
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		resp.setReturncode("00000001");
    		resp.setReturncode("系统异常");
    	}
    	return resp;
	}
    
    
    @RequestMapping(value = "docheckyzm")
    @ResponseBody
    public Response docheckyzm(@RequestBody Request<YzmInfo> req,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	Response resp=new Response();
    	try
    	{
    		ValueWrapper val = cacheManager.getCache("yzm").get(req.getData().getMobile());
    		if(val==null)
    		{
        		throw new BusinessException("验证码不正确!");
    		}
    		String oldyzm=(String) val.get();
    		if(oldyzm.equals(req.getData().getYzm()))
    		{
    			resp.setReturncode("00000000");
        		resp.setReturnmsg("验证码成功!");
    			return resp;
    		}
    	}catch(BusinessException ex)
    	{
    		resp.setReturncode("00000002");
    		resp.setReturnmsg(ex.getMessage());
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		resp.setReturncode("00000001");
    		resp.setReturnmsg("系统异常");
    	}
    	return resp;
	}
    
    
    /*
     * search_LIKE_title
     * sort_DESC_title
     */
    @RequestMapping(value = "dosearch")
    @ResponseBody
    public Page<User> dosearch(Integer page,Integer pagesize,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	Map<String, Object> search = Servlets.getParametersStartingWith(request, "search_");
    	Map<String, Object> sort = Servlets.getParametersStartingWith(request, "sort_");
    	return bo.search(search,sort,page,pagesize);
	}
    
    
    @RequestMapping(value = "dosearchforjson")
    @ResponseBody
    public Response<Page<User>> dosearchforjson(@RequestBody Request<SearchData> req)  throws Exception, BusinessException {
    	Response<Page<User>> resp=new Response<Page<User>>();
    	try
    	{
    		Page<User> page = bo.search(req.getData().getSearch(),req.getData().getSort(),req.getData().getPage(),req.getData().getPagesize());
        	resp.setData(page);
    	}catch(Exception ex)
		{
			resp.setReturncode("00000001"); 
			resp.setReturnmsg("系统异常!");
		}
    	return resp;
	}
    
    
//    
//    @RequestMapping
//    public ModelAndView showadd(Sms model,HttpServletRequest request, 
//            HttpServletResponse response)  throws Exception, BusinessException {
//        ModelAndView mav = new ModelAndView();
//		mav.addObject("model",model);
//        return mav;
//    }
//    
//    
//    @RequestMapping
//    public ModelAndView showdetail(Sms model,HttpServletRequest request, 
//            HttpServletResponse response)  throws Exception, BusinessException {
//        ModelAndView mav = null;
//        Sms  model_result=bo.getSmsByCoud(model);
//        if(model_result!=null)
//    	{
//    		 mav = new ModelAndView();
//    		 mav.addObject("model",model);
//    	}
//    	else
//		{
//			 throw new BusinessException("");
//		}
//        return mav;
//    }
//    
//    @RequestMapping
//    public ModelAndView showedit(Sms model,HttpServletRequest request, 
//            HttpServletResponse response)  throws Exception, BusinessException {
//        ModelAndView mav = null;
//        Sms  model_result=bo.getSmsByCoud(model);
//        if(model_result!=null)
//    	{
//    		 mav = new ModelAndView();
//    		 mav.addObject("model",model);
//    	}
//    	else
//		{
//			 throw new BusinessException("");
//		}
//        return mav;
//    }
//    
//    
//    @RequestMapping
//    public ModelAndView showsearch(Sms model,HttpServletRequest request, 
//            HttpServletResponse response)  throws Exception, BusinessException {
//        ModelAndView mav = new ModelAndView();
//		mav.addObject("model",model);
//        return mav;
//    }
//   
//    
//    @RequestMapping
//    public ModelAndView showlist(Sms model,Integer pageindex,Integer rows,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
//    	ModelAndView mav = new ModelAndView();
//		mav.addObject("model",model);
//        return mav;
//    }
//    
//    @RequestMapping
//    @ResponseBody
//    public String listdata(Sms model,Integer pageindex,Integer rows,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
//    	if(pageindex==null)
//    	{
//    		pageindex=1;
//    	}
//    	if(rows==null)
//    	{
//    		rows=10;
//    	}
//    	ListAndTotalCount<Sms>  modellist=bo.getPageList(model,pageindex,rows);
//		Page page2=new Page();
//		page2.setPageNo(pageindex);
//		page2.setRowCount(Integer.parseInt(modellist.getTotal()));
//		page2.setData(modellist.getRows());
//		page2.setPageSize(rows);
//		String res= ObjectToCell.convertCell(page2).toXml();
//		return res;
//    }
//    
//    
//    @RequestMapping
//    public ModelAndView doadd(Sms model,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
//    	ModelAndView mav=null;
//    	if(bo.add(model)!=null)
//    	{
//    		 mav = new ModelAndView("showdetail");
//    		 mav.addObject("model",model);
//    	}
//    	else
//		{
//			 throw new BusinessException("");
//		}
//        return mav;
//    }
//    
//    @RequestMapping
//    public ModelAndView dodelete(Sms model,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
//       	ModelAndView mav=null;
//       	if(bo.delete(model)>0)
//    	{
//    		 mav = new ModelAndView("forward:showsearch");
//    		 mav.addObject("model",model);
//    	}
//    	else
//		{
//			 throw new BusinessException("");
//		}
//        return mav;
//    }
//    
//    
//    @RequestMapping
//    public ModelAndView doupdate(Sms model,HttpServletRequest request, HttpServletResponse response) throws Exception, BusinessException{
//	    ModelAndView mav=null;
//    	if(bo.update(model)>0)
//    	{
//    		 mav = new ModelAndView("forward:showedit");
//    		 mav.addObject("model",model);
//    	}
//    	else
//		{
//			 throw new BusinessException("");
//		}
//        return mav;
//    }
//    
//    
//    
//    @RequestMapping
//    public void exportcsv(Sms model,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
//		response.setContentType("text/csv;charset=GBK");
//		request.setCharacterEncoding("GBK");
//		response.setHeader("Content-disposition", "inline; filename="+DateHelper.getDateString()+".csv");
//		CSVWriter writer = new CSVWriter(response.getWriter());
//		bo.writeModelToCsv(model, writer);
//		writer.flush();
//		writer.close();
//    }
//    
//    
//    @RequestMapping
//    public ModelAndView exportexcel(Sms model,Integer pageindex,Integer rows,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
//    	ModelAndView mav=null;
//    	if(pageindex==null)
//    	{
//    		pageindex=1;
//    	}
//    	if(rows==null)
//    	{
//    		rows=10;
//    	}
//		ListAndTotalCount<Sms>  modellist=bo.getPageList(model,pageindex,rows);
//		if(Integer.parseInt(modellist.getTotal())>0)
//    	{
//    		mav = new ModelAndView(new JxlsExcelView("/WEB-INF/excel/report.xls","report.xls"));
//   		 	mav.addObject("modellist",modellist);
//    	}
//		else
//		{
//			 throw new Exception("无数据");
//		}
//        return mav;
//    }
    
    
}