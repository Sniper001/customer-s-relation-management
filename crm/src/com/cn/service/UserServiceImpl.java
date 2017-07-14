package com.cn.service;

import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.UserDao;
import com.cn.domain.User;
import com.cn.utils.MD5Utils;

/**
 * �û���ҵ���
 * @author heting
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}
	@Override
	public void save(User user) {
		//���������
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		//�û���״̬Ĭ����1״̬
		user.setUser_state("1");
		//���ó־ò�
		userDao.save(user);
		
		
	}
	/**
	 * ��½ͨ����½����������У��
	 * �ȸ�������ܣ��ٲ�ѯ
	 */
	public User login(User user) {
		//���������
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}
	
}
