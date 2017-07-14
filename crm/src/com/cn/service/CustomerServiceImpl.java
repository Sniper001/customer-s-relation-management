package com.cn.service;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.CustomerDao;
import com.cn.domain.Customer;
import com.cn.domain.PageBean;
import com.cn.service.CustomerService;




@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public void save(Customer customer) {
		customerDao.save(customer);
	}

	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria) {
		return customerDao.findByPage(pageCode,pageSize,criteria);
	}

	/**
	 * ͨ����������ѯ�ͻ�
	 */
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	/**
	 * ɾ���ͻ�
	 */
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	/**
	 * ���¿ͻ�
	 */

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	/**
	 * ��ѯ���пͻ�
	 */
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	/**
	 * ͳ�ƿͻ�����Դ
	 */
	@Override
	public List<Object[]> findBySource() {
		return customerDao.findBySource();
	}

}
