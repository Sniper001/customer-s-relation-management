package com.cn.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.cn.domain.User;

/**
 * �־ò㣬���Լ̳�HibernateDaoSupport��
 * @author heting
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	/**
	 * ͨ����½��������֤
	 */
	public User checkCode(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * �����û�
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	/**
	 * ��½����
	 * ͨ���û���¼����������û���״̬
	 */
	public User login(User user) {
		//QBC��ѯ����������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		//ƴ�Ӳ�ѯ����
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		criteria.add(Restrictions.eq("user_state", "1"));
		//��ѯ
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list !=null &&list.size() >0){
			return list.get(0);
		}
		return null;
	}

}
