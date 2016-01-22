package com.bxtel.knowledge.controller;
import com.bxtel.knowledge.bo.*;
import com.bxtel.knowledge.model.*;
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
//com/bxtel/knowledge/showadd.do
//com/bxtel/knowledge/showdetail.do
//com/bxtel/knowledge/showedit.do
//com/bxtel/knowledge/showsearch.do
//com/bxtel/knowledge/showpagelist.do
//com/bxtel/knowledge/showlist.do
//com/bxtel/knowledge/doadd.do
//com/bxtel/knowledge/doupdate.do
//com/bxtel/knowledge/dodelete.do


@Controller
@RequestMapping(value = "/knowledge")
public class KnowledgeController extends MultiActionController {
	    
    @Resource
	public KnowledgeBO  bo;
    
    private static final Log logger = LogFactory.getLog(KnowledgeController.class);
    
    
     /*
     * search_LIKE_title
     * sort_DESC_title
     */
    @RequestMapping(value = "dosearch")
    @ResponseBody
    public Page<Knowledge> dosearch(Integer page,Integer pagesize,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	Map<String, Object> search = Servlets.getParametersStartingWith(request, "search_");
    	Map<String, Object> sort = Servlets.getParametersStartingWith(request, "sort_");
    	return bo.search(search,sort,page,pagesize);
	}
    
    
    @RequestMapping(value = "dosearchforjson")
    @ResponseBody
    public Response<Page<Knowledge>> dosearchforjson(@RequestBody Request<SearchData> req)  throws Exception, BusinessException {
    	Response<Page<Knowledge>> resp=new Response<Page<Knowledge>>();
    	try
    	{
    		Page<Knowledge> page = bo.search(req.getData().getSearch(),req.getData().getSort(),req.getData().getPage(),req.getData().getPagesize());
        	resp.setData(page);
    	}catch(Exception ex)
		{
			resp.setReturncode("00000001"); 
			resp.setReturnmsg("系统异常!");
		}
    	return resp;
	}
	
}