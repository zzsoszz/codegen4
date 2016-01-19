package com.bxtel.company.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	String name;//公司名字
	
	String icon_url;//公司图标地址
	
	String intro;//介绍
	
	String address;//地址
	
	String latitude;//纬度
	
	String longitude;//经度
	
	String mobile;//手机号码
	
	String certificate_images;//证书图片地址
	
	String evaluation;//口碑
	
	String consulting_person;//咨询人数

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertificate_images() {
		return certificate_images;
	}

	public void setCertificate_images(String certificate_images) {
		this.certificate_images = certificate_images;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getConsulting_person() {
		return consulting_person;
	}

	public void setConsulting_person(String consulting_person) {
		this.consulting_person = consulting_person;
	}
}
