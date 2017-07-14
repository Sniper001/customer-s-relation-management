package com.cn.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cn.domain.Dict;
import com.cn.service.DictService;
import com.cn.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2100016199707614879L;
	
	private Dict dict = new Dict();
	@Override
	public Dict getModel() {
		return dict;
	}
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	/**
	 * ͨ���ֶε�type_codeֵ����ѯ�ͻ�������߿ͻ�����Դ
	 * @return
	 */
	public String findByCode(){
		//����ҵ���
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
		
		//ʹ��fastjson,��listת����json�ַ���
		String jsonString = FastJsonUtil.toJSONString(list);
		//��json�ַ���д�������
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//���
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	

}
