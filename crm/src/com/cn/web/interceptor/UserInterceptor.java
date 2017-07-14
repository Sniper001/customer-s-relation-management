package com.cn.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.cn.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * �û������������ж��û��Ƿ��Ѿ���¼��ִ����һ��������
 * ���û�е�¼�����ص���½ҳ�棨���ܶ����е��������أ�login��regist������
 * �̳�ָ��������
 * @author heting
 *
 */
public class UserInterceptor extends MethodFilterInterceptor{

	/**
	 * ����Ŀ��Action�ķ���
	 */
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//��ȡsession
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user == null){
			return "login";
		}
		
		return invocation.invoke();
	}

}
