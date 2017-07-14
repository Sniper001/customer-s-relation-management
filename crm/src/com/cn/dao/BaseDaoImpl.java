package com.cn.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.cn.domain.Customer;
import com.cn.domain.PageBean;
/**
 * �Ժ����е�Dao��ʵ���࣬������ �̳�BaseDaoImpl,��ɾ�Ĳ��ҳ����������д��
 * @author heting
 *
 * @param <T>
 */
@SuppressWarnings("all")//ѹ�ƻ��߾���
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	//�����Ա����
	private Class clazz;
	public BaseDaoImpl(){
		//this��ʾ���࣬c��ʾ����CustomerDaoImpl��class����
		Class c = this.getClass();
		//CustomerDaoImpl extends BaseDaoImpl<Customer>
		//��2������ȡ��BaseDaoImpl<Customer>
		Type type = c.getGenericSuperclass();
		
		//Ŀ�ģ���type�ӿ�ת�����ӽӿ�
		if(type instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType) type;
			
			//��ȡcustomer
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}
	/**
	 * ���
	 */
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	/**
	 * ɾ��
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	/**
	 * �޸�
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	/**
	 * ͨ��������ѯ
	 */
	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}
	/**
	 * ��ѯ��������
	 */
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	/**
	 * ��ҳ��ѯ
	 */

	public PageBean<T> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria) {
		PageBean<T> page = new PageBean<T>();
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
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria,(pageCode-1)*pageSize, pageSize);
		
		//��ҳ��ѯ���ݣ�ÿҳ��ʾ�����ݣ�ʹ��limit
		page.setBeanList(beanList);
		
		return page;
	}

}
