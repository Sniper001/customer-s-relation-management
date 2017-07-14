package com.cn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8021804624950246255L;
	
	
	//���������ķ�ʽ
		//��ǰҳ��Ĭ��ֵ����1
		private Integer pageCode = 1;
		
		public Integer getPageCode() {
			return pageCode;
		}
		public void setPageCode(Integer pageCode) {
			if(pageCode ==null){
				pageCode = 1;
			}
			this.pageCode = pageCode;
		}
		
		//ÿҳ��ʾ�����ݵ�����
		private Integer pageSize = 2;
		
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		/**
		 * ����ֵջ�����set����
		 */
		public void setVs(String key,Object obj){
			ActionContext.getContext().getValueStack().set(key, obj);
		}
		/**
		 * ����ֵջ��push�ķ���
		 * @param obj
		 */
		public void pushVs(Object obj){
			ActionContext.getContext().getValueStack().push(obj);
		}
}
