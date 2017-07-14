package com.cn.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.DictDao;
import com.cn.domain.Dict;

@Transactional
public class DictServiceImpl implements DictService {

	private DictDao dictDao;
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	/**
	 * 通过客户类别编码查询字段
	 */
	@Override
	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}
	
}
