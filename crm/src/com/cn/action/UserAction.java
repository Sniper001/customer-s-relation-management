package com.cn.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cn.domain.User;
import com.cn.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6259594293594562529L;

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ע��
	 * @return
	 */
	public String regist(){
		userService.save(user);
		return LOGIN;
	}
	/**
	 * ͨ����½�����жϣ���¼���Ƿ��Ѿ�����
	 * @return
	 */
	public String checkCode(){
		//����ҵ��㣬��ѯ
		User u = userService.checkCode(user.getUser_code());
		//��ȡresponse����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("test/html;charset=UTF-8");
		try {
			//��ȡ�����
			PrintWriter writer = response.getWriter();
			//�����ж�
			if(u !=null){
				//˵����½����ѯ���û��ˣ�˵����½���Ѿ����ڣ�����ע��
				writer.print("no");
			}else{
				//˵���������ڵ�¼��������ע��
				writer.print("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	/**
	 * ��½
	 */
	public String login(){
		User existUser = userService.login(user);
		//�жϣ���¼�������������
		if(existUser == null){
			return LOGIN;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);//���û���ӵ�session��
			return "loginOK";
		}
	}
	/**
	 * ��ȫ�˳�
	 */
	public String exit(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");//���û���session������
		return LOGIN;
	}
}
