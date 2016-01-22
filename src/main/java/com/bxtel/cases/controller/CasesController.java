package com.bxtel.cases.controller;
import com.bxtel.cases.bo.*;
import com.bxtel.cases.model.*;
import com.bxtel.exception.*;
import com.bxtel.commons.Request;
import com.bxtel.commons.Response;
import com.bxtel.commons.SearchData;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;
import dinamica.util.DateHelper;
//com/bxtel/cases/showadd.do
//com/bxtel/cases/showdetail.do
//com/bxtel/cases/showedit.do
//com/bxtel/cases/showsearch.do
//com/bxtel/cases/showpagelist.do
//com/bxtel/cases/showlist.do
//com/bxtel/cases/doadd.do
//com/bxtel/cases/doupdate.do
//com/bxtel/cases/dodelete.do


@Controller
@RequestMapping(value = "/cases")
public class CasesController extends MultiActionController {
	    
    @Resource
	public CasesBO  bo;
    
    private static final Log logger = LogFactory.getLog(CasesController.class);
    
    
     /*
     * search_LIKE_title
     * sort_DESC_title
     */
    @RequestMapping(value = "dosearch")
    @ResponseBody
    public Page<Cases> dosearch(Integer page,Integer pagesize,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	Map<String, Object> search = Servlets.getParametersStartingWith(request, "search_");
    	Map<String, Object> sort = Servlets.getParametersStartingWith(request, "sort_");
    	return bo.search(search,sort,page,pagesize);
	}
    
    
    @RequestMapping(value = "dosearchforjson")
    @ResponseBody
    public Response<Page<Cases>> dosearchforjson(@RequestBody Request<SearchData> req)  throws Exception, BusinessException {
    	Response<Page<Cases>> resp=new Response<Page<Cases>>();
    	try
    	{
    		Page<Cases> page = bo.search(req.getData().getSearch(),req.getData().getSort(),req.getData().getPage(),req.getData().getPagesize());
        	resp.setData(page);
    	}catch(Exception ex)
		{
			resp.setReturncode("00000001"); 
			resp.setReturnmsg("系统异常!");
		}
    	return resp;
	}
	
}