package ${packageName}.controller;
import ${packageName}.bo.*;
import ${packageName}.model.*;

import com.bxtel.exception.BusinessException;
import com.bxtel.exception.DAOException;
import dinamica.util.ListAndTotalCount;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.jxls.JxlsExcelView;
import au.com.bytecode.opencsv.CSVWriter;
import dinamica.util.DateHelper;
import com.bxtel.common.ObjectToCell;
import com.bxtel.common.Page;
import org.springframework.web.bind.annotation.ResponseBody;


//${packageNameForHttp}/showadd.do
//${packageNameForHttp}/showdetail.do
//${packageNameForHttp}/showedit.do
//${packageNameForHttp}/showsearch.do
//${packageNameForHttp}/showpagelist.do
//${packageNameForHttp}/showlist.do
//${packageNameForHttp}/doadd.do
//${packageNameForHttp}/doupdate.do
//${packageNameForHttp}/dodelete.do


@Controller
public class ${model.simpleName}Controller extends MultiActionController {
	    
    @Resource
	public ${model.simpleName}BO  bo;
    
    private static final Log logger = LogFactory.getLog(${model.simpleName}Controller.class);
    
    
    @RequestMapping
    public ModelAndView showadd(${model.simpleName} model,HttpServletRequest request, 
            HttpServletResponse response)  throws Exception, BusinessException {
        ModelAndView mav = new ModelAndView();
		mav.addObject("model",model);
        return mav;
    }
    
    
    @RequestMapping
    public ModelAndView showdetail(${model.simpleName} model,HttpServletRequest request, 
            HttpServletResponse response)  throws Exception, BusinessException {
        ModelAndView mav = null;
        ${model.simpleName}  model_result=bo.get${model.simpleName}ByCoud(model);
        if(model_result!=null)
    	{
    		 mav = new ModelAndView();
    		 mav.addObject("model",model);
    	}
    	else
		{
			 throw new BusinessException("");
		}
        return mav;
    }
    
    
    @RequestMapping
    public ModelAndView showedit(${model.simpleName} model,HttpServletRequest request, 
            HttpServletResponse response)  throws Exception, BusinessException {
        ModelAndView mav = null;
        ${model.simpleName}  model_result=bo.get${model.simpleName}ByCoud(model);
        if(model_result!=null)
    	{
    		 mav = new ModelAndView();
    		 mav.addObject("model",model);
    	}
    	else
		{
			 throw new BusinessException("");
		}
        return mav;
    }
    
    
    @RequestMapping
    public ModelAndView showsearch(${model.simpleName} model,HttpServletRequest request, 
            HttpServletResponse response)  throws Exception, BusinessException {
        ModelAndView mav = new ModelAndView();
		mav.addObject("model",model);
        return mav;
    }
   
    
    @RequestMapping
    public ModelAndView showlist(${model.simpleName} model,Integer pageindex,Integer rows,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
    	ModelAndView mav = new ModelAndView();
		mav.addObject("model",model);
        return mav;
    }
    
    @RequestMapping
    @ResponseBody
    public String listdata(${model.simpleName} model,Integer pageindex,Integer rows,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
    	if(pageindex==null)
    	{
    		pageindex=1;
    	}
    	if(rows==null)
    	{
    		rows=10;
    	}
    	ListAndTotalCount<${model.simpleName}>  modellist=bo.getPageList(model,pageindex,rows);
		Page page2=new Page();
		page2.setPageNo(pageindex);
		page2.setRowCount(Integer.parseInt(modellist.getTotal()));
		page2.setData(modellist.getRows());
		page2.setPageSize(rows);
		String res= ObjectToCell.convertCell(page2).toXml();
		return res;
    }
    
    
    @RequestMapping
    public ModelAndView doadd(${model.simpleName} model,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
    	ModelAndView mav=null;
    	if(bo.add(model)!=null)
    	{
    		 mav = new ModelAndView("showdetail");
    		 mav.addObject("model",model);
    	}
    	else
		{
			 throw new BusinessException("");
		}
        return mav;
    }
    
    @RequestMapping
    public ModelAndView dodelete(${model.simpleName} model,HttpServletRequest request, HttpServletResponse response)  throws Exception, BusinessException {
       	ModelAndView mav=null;
       	if(bo.delete(model)>0)
    	{
    		 mav = new ModelAndView("forward:showsearch");
    		 mav.addObject("model",model);
    	}
    	else
		{
			 throw new BusinessException("");
		}
        return mav;
    }
    
    
    @RequestMapping
    public ModelAndView doupdate(${model.simpleName} model,HttpServletRequest request, HttpServletResponse response) throws Exception, BusinessException{
	    ModelAndView mav=null;
    	if(bo.update(model)>0)
    	{
    		 mav = new ModelAndView("forward:showedit");
    		 mav.addObject("model",model);
    	}
    	else
		{
			 throw new BusinessException("");
		}
        return mav;
    }
    
    
    
    @RequestMapping
    public void exportcsv(${model.simpleName} model,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
		response.setContentType("text/csv;charset=GBK");
		request.setCharacterEncoding("GBK");
		response.setHeader("Content-disposition", "inline; filename="+DateHelper.getDateString()+".csv");
		CSVWriter writer = new CSVWriter(response.getWriter());
		bo.writeModelToCsv(model, writer);
		writer.flush();
		writer.close();
    }
    
    
    @RequestMapping
    public ModelAndView exportexcel(${model.simpleName} model,Integer pageindex,Integer rows,HttpServletRequest request,HttpServletResponse response)  throws Exception, BusinessException {
    	ModelAndView mav=null;
    	if(pageindex==null)
    	{
    		pageindex=1;
    	}
    	if(rows==null)
    	{
    		rows=10;
    	}
		ListAndTotalCount<${model.simpleName}>  modellist=bo.getPageList(model,pageindex,rows);
		if(Integer.parseInt(modellist.getTotal())>0)
    	{
    		mav = new ModelAndView(new JxlsExcelView("/WEB-INF/excel/report.xls","report.xls"));
   		 	mav.addObject("modellist",modellist);
    	}
		else
		{
			 throw new Exception("无数据");
		}
        return mav;
    }
    
    
}