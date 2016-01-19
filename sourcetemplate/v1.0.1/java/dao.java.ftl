package ${packageName}.dao;
import ${packageName}.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface SmsRepository extends CrudRepository<Sms, Long> {
	 @Query("select g from Sms g where msgid=:msgid" )
	 public Sms findByMsgid(@Param("msgid")String  msgid);
	 @Query("select g from Sms g where status=:status" )
	 public List<Sms> findALLBySatus(@Param("status")String  status);
}