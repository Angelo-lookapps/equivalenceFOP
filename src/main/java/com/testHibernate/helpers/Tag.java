package com.testHibernate.helpers;

public class Tag {

	public Long id;
	public String tagName;

	//getter and setter methods

	public Tag(Long id, String tagName) {
		this.id = id;
		this.tagName = tagName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}