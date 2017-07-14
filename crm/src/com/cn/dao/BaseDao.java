package com.cn.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.cn.domain.PageBean;

/**
 * �Ժ����е�dao����Ҫ�̳�BaseDao�ӿ�
 * �Զ��巺�ͽӿ�
 * @author heting
 *
 */
public interface BaseDao<T> {
	
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
