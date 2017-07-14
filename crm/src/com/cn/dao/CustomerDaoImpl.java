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
	 * ͳ�ƿͻ�����Դ
	 */
	public List<Object[]> findBySource() {
		//����HQL
		//SELECT * FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source
		//�����ѯSELECT * FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source group by d.dict_id
		//��ѯ����SELECT d.dict_item_name,count(*) FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source group by d.dict_id
		String hql = "select c.source.dict_item_name,count(*) from Customer c inner join c.source group by c.source";
		
		return (List<Object[]>) this.getHibernateTemplate().find(hql);
	}

	/**
	 * ����
	 * @param customer
	 
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}
	*/
	/**
	 * ��ҳ��ѯ
	 
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria) {
		PageBean<Customer> page = new PageBean<Customer>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//�Ȳ�ѯ�ܼ�¼����select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//�ܵļ�¼��
			page.setTotalCount(totalCount);
		}
		
		//ǿ������select count(*) ����գ����select * ..
		criteria.setProjection(null);
		
		//�ṩ��ҳ�Ĳ�ѯ
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,(pageCode-1)*pageSize, pageSize);
		
		//��ҳ��ѯ���ݣ�ÿҳ��ʾ�����ݣ�ʹ��limit
		page.setBeanList(beanList);
		
		return page;
	}
	*/
	/**
	 * ͨ����������ѯ�ͻ�
	 
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}
	*/
	/**
	 * ɾ���ͻ�
	 
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}
	*/
	/**
	 * ���¿ͻ�
	 
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}
	*/
}
