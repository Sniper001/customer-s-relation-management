package com.cn.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cn.domain.PageBean;
import com.cn.domain.User;
import com.cn.domain.Visit;
import com.cn.service.VisitService;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �ͻ��ݷõĿ�����
 * @author heting
 *Controller(value="visitAction") = <bean id="visitAction" class=".." scope="">
 */
@Controller(value="visitAction")
@Scope(value="prototype")
public class VisitAction extends BaseAction implements ModelDriven<Visit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1290362602043159963L;
	
	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		return visit;
	}
	@Resource(name="visitService")
	private VisitService visitService;
	
	private String beginDate;
	private String endDate;
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * ��ҳ�Ĳ�ѯ
	 * ��ѯ�ͻ��İݷü�¼�������û���������ѯ
	 * select * from sale_visit where visit_user_id=?
	 * @return
	 */
	public String findByPage(){
		//�Ȼ�ȡ��ǰ��¼���û�
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//�ж�
		if(user == null){
			//����ȫ�ֽ����ת
			return LOGIN;
		}
		//��ѯ���û��µ����аݷü�¼
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		//ƴ�Ӳ�ѯ����
		if(beginDate != null && !beginDate.trim().isEmpty()){
			criteria.add(Restrictions.ge("visit_time", beginDate));
		}
		//select * from xxx where visit_time >=? and visit_time <=?
		if(endDate != null && !endDate.trim().isEmpty()){
			criteria.add(Restrictions.le("visit_time", endDate));
		}
		
		
		//��Ӳ�ѯ������
		criteria.add(Restrictions.eq("user.user_id", user.getUser_id()));
		//��ҳ
		PageBean<Visit> page = visitService.findByPage(this.getPageCode(),this.getPageSize(),criteria);
		this.setVs("page", page);
		
		return "page";
	}
	/**
	 * ����ݷü�¼
	 * @return
	 */
	public String save(){
		//���û���ȡ�������õ���ǰ�İݷü�¼�У��ٱ���
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		System.out.println("�û���"+user);
		//�ж�
		if(user == null){
			//����ȫ�ֽ����ת
			return LOGIN;
		}
		//����
		visit.setUser(user);
		//��������
		visitService.save(visit);
		return "save";
	}

}
