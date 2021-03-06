package com.cn.dao;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.cn.dao.CustomerDao;
import com.cn.domain.Customer;
import com.cn.domain.PageBean;


public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/**
	 * 统计客户的来源
	 */
	public List<Object[]> findBySource() {
		//定义HQL
		//SELECT * FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source
		//分组查询SELECT * FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source group by d.dict_id
		//查询内容SELECT d.dict_item_name,count(*) FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source group by d.dict_id
		String hql = "select c.source.dict_item_name,count(*) from Customer c inner join c.source group by c.source";
		
		return (List<Object[]>) this.getHibernateTemplate().find(hql);
	}

	/**
	 * 保存
	 * @param customer
	 
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}
	*/
	/**
	 * 分页查询
	 
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria) {
		PageBean<Customer> page = new PageBean<Customer>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//先查询总记录数，select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//总的记录数
			page.setTotalCount(totalCount);
		}
		
		//强调：把select count(*) 先清空，变成select * ..
		criteria.setProjection(null);
		
		//提供分页的查询
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,(pageCode-1)*pageSize, pageSize);
		
		//分页查询数据，每页显示的数据，使用limit
		page.setBeanList(beanList);
		
		return page;
	}
	*/
	/**
	 * 通过主键，查询客户
	 
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}
	*/
	/**
	 * 删除客户
	 
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}
	*/
	/**
	 * 更新客户
	 
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}
	*/
}
