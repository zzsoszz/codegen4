package com.bxtel.cases.bo;
import com.bxtel.cases.model.*;
import com.bxtel.cases.dao.*;
import com.bxtel.exception.BusinessException;
import com.bxtel.exception.DAOException;
import java.util.*;
import javax.annotation.Resource;
import dinamica.*;
import dinamica.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.*;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SortParse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@Service("CasesBO")
public class CasesBO 
{
	@Resource
	public CasesRepository dao;
	
	//@Cacheable(value="user")
	public List<Cases> search(Map<String, Object> searchParams,Map<String, Object> sortParams)
	{
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Cases> spec =  DynamicSpecifications.bySearchFilter(filters.values(),Cases.class);
		Sort sort=SortParse.parse(sortParams);
		List<Cases> list = dao.findAll(spec,sort);
		return list;
	}
	
	
	//@Cacheable(value="user")
	public Page<Cases> search(Map<String, Object> searchParams,Map<String, Object> sortParams,Integer page,Integer pagesize)
	{
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Cases> spec = DynamicSpecifications.bySearchFilter(filters.values(), Cases.class);
		Sort sort=SortParse.parse(sortParams);
		PageRequest pageRequest=null;
		if(sort!=null)
		{
			pageRequest=new PageRequest(page,pagesize,sort);
		}else{
			pageRequest=new PageRequest(page,pagesize);
		}
		Page<Cases> p = dao.findAll(spec,pageRequest);
		p.getContent();
		return p;
	}
	
}