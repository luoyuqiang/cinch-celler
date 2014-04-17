/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.common.persistence;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2013-05-28
 */
@MappedSuperclass
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	//protected String id;		// 编号
	protected Long id;
	
	public IdEntity() {
		super();
	}
	
//	@PrePersist
//	public void prePersist(){
//		super.prePersist();
//		this.id = id;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
