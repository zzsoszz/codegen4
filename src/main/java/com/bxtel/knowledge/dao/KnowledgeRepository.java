package com.bxtel.knowledge.dao;
import com.bxtel.knowledge.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
public interface KnowledgeRepository  extends PagingAndSortingRepository<Knowledge, Long>,JpaSpecificationExecutor<Knowledge>  {
	
}