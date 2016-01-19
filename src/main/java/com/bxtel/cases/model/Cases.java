package com.bxtel.cases.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "qy_case")
public class Cases{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long belonguserid;//所属设计师
	
	private String style;//风格
	
	private String totalprice;//总价格
	
	private String roomtype;//房型
	
	private String area;//面积
	
	private String scenestyle;//场景类型
	
	private String buildingname;//楼盘名称
	
	private String buildingaddress;//楼盘地址
	
	private String latitude;//纬度
	
	private String longitude;//经度
	
	private String designconcept;//设计理念
	
	private String image1;//邮件
	
	private String image2;//邮件
	
	private String image3;//邮件
	
	private String image4;//邮件
	
	private String image5;//邮件

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBelonguserid() {
		return belonguserid;
	}

	public void setBelonguserid(long belonguserid) {
		this.belonguserid = belonguserid;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getScenestyle() {
		return scenestyle;
	}

	public void setScenestyle(String scenestyle) {
		this.scenestyle = scenestyle;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getBuildingaddress() {
		return buildingaddress;
	}

	public void setBuildingaddress(String buildingaddress) {
		this.buildingaddress = buildingaddress;
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

	public String getDesignconcept() {
		return designconcept;
	}

	public void setDesignconcept(String designconcept) {
		this.designconcept = designconcept;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}
}
