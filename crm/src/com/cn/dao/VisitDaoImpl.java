package com.cn.dao;


import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.domain.Visit;
/**
 * �ͻ��ݷõĳ־ò�
 * @author heting
 *
 */
@Repository(value="visitDao")
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

	/*����
	 * @Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	�ᱨ��
	*/
	@Resource(name="sessionFactory")
	public void set2SessionFactory(SessionFactory sessionFactory){
		//�ؼ������ø��෽��
		super.setSessionFactory(sessionFactory);
	}
	

}
