package com.bxtel.knowledge.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
//@Table(name = "qy_knowledge")
public class Knowledge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String type;//知识类型（准备，拆改，水电，泥木，油漆，竣工，软装，入住，设计，施工，风水，风格，装修经验）
	
	private String title;//标题
	
	private String image_title;//图片标题
	
	private String intro;//简介
	
	private String browsecount;//浏览次数
	
	private String html;//内容
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_title() {
		return image_title;
	}

	public void setImage_title(String image_title) {
		this.image_title = image_title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBrowsecount() {
		return browsecount;
	}

	public void setBrowsecount(String browsecount) {
		this.browsecount = browsecount;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
}
