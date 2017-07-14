package com.cn.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.LinkmanDao;
import com.cn.domain.Linkman;
import com.cn.domain.PageBean;
/**
 * ҵ���
 * @author heting
 *
 */
@Transactional
public class LinkmanServiceImpl implements LinkmanService {
	private LinkmanDao linkmanDao;
	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}
	/**
	 * ��ҳ�ķ���
	 */
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}
	/**
	 * ����
	 */
	public void save(Linkman linkman) {
		linkmanDao.save(linkman);
	}
	/**
	 * ɾ��
	 */
	public void delete(Linkman linkman) {
		linkmanDao.delete(linkman);
	}
	/**
	 * ������ϵ��id������ϵ��
	 */
	public Linkman findById(Long lkm_id) {
		return linkmanDao.findById(lkm_id);
	}
	/**
	 * ������ϵ��
	 */
	public void update(Linkman linkman) {
		linkmanDao.update(linkman);
	}
	
	
}
