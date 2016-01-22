package com.bxtel.company.dao;
import com.bxtel.company.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
public interface CompanyRepository  extends PagingAndSortingRepository<Company, Long>,JpaSpecificationExecutor<Company>  {
	
}