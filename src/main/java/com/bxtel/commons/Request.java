package com.bxtel.commons;
import javax.validation.Valid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
//
//import com.bxtel.user.model.User;
//import com.bxtel.user.vo.RegistInfo;

import dinamica.coder.RSACoderTest;
import dinamica.util.JsonHelper;

public class Request<T> {
	@NotNull
	String imsi;
	
	@Pattern(regexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$",message="必须为yyyy-MM-dd HH:mm:ss")
	@NotNull
	String timestamp;
	
	//版本号
	String version;
	
	@Valid
	@NotNull
	T data;
	
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static void main(String[] args) throws Exception {
//		Request<User> req=new Request<User>();
//		User d=new User();
//		d.setMobile("13730666347");
//		d.setPassword(RSACoderTest.encode("123456"));
//		req.setData(d);
//		System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(req));
		
		
//		Request<RegistInfo> req=new Request<RegistInfo>();
//		RegistInfo data=new RegistInfo();
//		User u=new User();
//		data.setYzm("123456");
//		data.setUser(u);
//		req.setData(data);
//		System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(req));
		
		 Request<String>  req=new Request<String>();
		 req.setData("13730666347");
		 System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(req));
	}
}
