package com.bxtel.cases.dao;
import com.bxtel.cases.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
public interface CasesRepository  extends PagingAndSortingRepository<Cases, Long>,JpaSpecificationExecutor<Cases>  {
	
}