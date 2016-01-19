package ${packageName}.bo;
import ${packageName}.model.*;
import ${packageName}.dao.*;
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

@Service("${model.simpleName}BO")
public class ${model.simpleName}BO 
{
	@Resource
	public ${model.simpleName}DAO dao;
	
	private static final Log logger = LogFactory.getLog(${model.simpleName}BO.class);
	
	public ${model.simpleName} add(${model.simpleName} model)  throws BusinessException, DAOException  {
			return dao.add(model);
	}
	public int delete(${model.simpleName} model)   throws BusinessException, DAOException  {
			return dao.deleteByCoud(model);
	}
	
	public int update(${model.simpleName} model)  throws BusinessException, DAOException  {
			int row=dao.updateCoudByRowId(model);
			if(row!=1)
			{
				new DAOException("update faild");
			}
			return 0;
	}
	
	public int updateByCoudAndWhere(${model.simpleName} model,${model.simpleName} wheremodel)  throws BusinessException, DAOException  {
			return dao.updateByCoudAndWhere(model,wheremodel);
	}
	
	
	public List<${model.simpleName}> get${model.simpleName}ListByCoud(${model.simpleName} model)   throws BusinessException, DAOException  {
			return dao.get${model.simpleName}ListByCoud(model);
	}
	
	
	public List<${model.simpleName}> get${model.simpleName}ListByExact(${model.simpleName} model)  throws BusinessException, DAOException  {
			return dao.get${model.simpleName}ListByExact(model);
	}
	
	
	
	
	public ${model.simpleName} get${model.simpleName}ByCoud(${model.simpleName} model)  throws BusinessException, DAOException  {
			return dao.get${model.simpleName}ByCoud(model);
	}
	
	public ${model.simpleName} get${model.simpleName}ByExact(${model.simpleName} model)  throws BusinessException, DAOException  {
			return dao.get${model.simpleName}ByExact(model);
	}
	
	
	
	
	public List<Map<String, Object>> getListMapByCoud(${model.simpleName} model)  throws BusinessException, DAOException {
			return dao.getListMapByCoud(model);
	}
	
	
	public List<Map<String, Object>> getListMapByExact(${model.simpleName} model)   throws BusinessException, DAOException {
			return dao.getListMapByExact(model);
	}
	
	public ListAndTotalCount<${model.simpleName}> getPageList(${model.simpleName} model, int pageIndex,int rows)  throws BusinessException, DAOException 
	{
			List<${model.simpleName}> list = dao.getPageListByCound(model,pageIndex,rows);
			ListAndTotalCount<${model.simpleName}> lst = new ListAndTotalCount<${model.simpleName}>();
			if(list!=null&& list.size()>0)
			{
				lst.setTotal(list.get(0).getTotalCount());
				lst.setRows(list);
			}
			return lst;
	}
	public void writeModelToCsv(${model.simpleName} model,CSVWriter writer)throws Exception{
		dao.get${model.simpleName}ListByCoud(model,writer);
	}
}