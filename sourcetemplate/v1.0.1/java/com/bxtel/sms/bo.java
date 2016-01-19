package com.bxtel.sms.bo;
import com.bxtel.sms.model.*;
import com.bxtel.sms.dao.*;
import com.bxtel.exception.BusinessException;
import com.bxtel.exception.DAOException;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import dinamica.*;
import dinamica.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.annotation.Resource;
import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.springframework.jdbc.core.RowCallbackHandler;

@Service("SmsBO")
public class SmsBO 
{
	@Resource
	public SmsDAO dao;
	
	private static final Log logger = LogFactory.getLog(SmsBO.class);
	
	public Sms add(Sms model)  throws BusinessException, DAOException  {
			return dao.add(model);
	}
	public int delete(Sms model)   throws BusinessException, DAOException  {
			return dao.deleteByCoud(model);
	}
	
	public int update(Sms model)  throws BusinessException, DAOException  {
			int row=dao.updateCoudByRowId(model);
			if(row!=1)
			{
				new DAOException("update faild");
			}
			return 0;
	}
	
	public int updateByCoudAndWhere(Sms model,Sms wheremodel)  throws BusinessException, DAOException  {
			return dao.updateByCoudAndWhere(model,wheremodel);
	}
	
	
	public List<Sms> getSmsListByCoud(Sms model)   throws BusinessException, DAOException  {
			return dao.getSmsListByCoud(model);
	}
	
	
	public List<Sms> getSmsListByExact(Sms model)  throws BusinessException, DAOException  {
			return dao.getSmsListByExact(model);
	}
	
	
	
	
	public Sms getSmsByCoud(Sms model)  throws BusinessException, DAOException  {
			return dao.getSmsByCoud(model);
	}
	
	public Sms getSmsByExact(Sms model)  throws BusinessException, DAOException  {
			return dao.getSmsByExact(model);
	}
	
	
	
	
	public List<Map<String, Object>> getListMapByCoud(Sms model)  throws BusinessException, DAOException {
			return dao.getListMapByCoud(model);
	}
	
	
	public List<Map<String, Object>> getListMapByExact(Sms model)   throws BusinessException, DAOException {
			return dao.getListMapByExact(model);
	}
	
	public ListAndTotalCount<Sms> getPageList(Sms model, int pageIndex,int rows)  throws BusinessException, DAOException 
	{
			List<Sms> list = dao.getPageListByCound(model,pageIndex,rows);
			ListAndTotalCount<Sms> lst = new ListAndTotalCount<Sms>();
			if(list!=null&& list.size()>0)
			{
				lst.setTotal(list.get(0).getTotalCount());
				lst.setRows(list);
			}
			return lst;
	}
	
	
	public void writeModelToCsv(Sms model,CSVWriter writer)throws Exception{
		dao.getSmsListByCoud(model,writer);
	}
	
}