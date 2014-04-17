/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.CustomerDao;
import com.thinkgem.jeesite.modules.sys.entity.Customer;
import com.thinkgem.jeesite.modules.sys.entity.Log;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class CustomerService extends BaseService {

	@Autowired
	private CustomerDao customerDao;
	
	public Customer get(Long id) {
		return customerDao.get(id);
	}
	
	public Page<Customer> findCustomer(Page<Customer> page, Customer customer) {
		DetachedCriteria dc = customerDao.createDetachedCriteria();		
		if (StringUtils.isNotEmpty(customer.getName())){
			dc.add(Restrictions.like("name", "%"+customer.getName()+"%"));
		}
		
		//dc.add(Restrictions.eq(User.FIELD_DEL_FLAG, User.DEL_FLAG_NORMAL));
		if (!StringUtils.isNotEmpty(page.getOrderBy())){
			dc.addOrder(Order.desc("name"));
		}
		return customerDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void saveCustomer(Customer customer) {
		customerDao.clear();
		customerDao.save(customer);
		
	}

	@Transactional(readOnly = false)
	public void deleteCustomer(Long id) {
		customerDao.deleteById(id);
	}
	
}
