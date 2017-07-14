package com.cn.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.cn.domain.Customer;
import com.cn.domain.Linkman;
import com.cn.domain.PageBean;
import com.cn.service.LinkmanService;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3319414037708464328L;
	
	private Linkman linkman = new Linkman();
	public Linkman getModel() {
		return linkman;
	}
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	/**
	 * ��ҳ����
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		//��ȡ����ϵ�˵�����
		String lkm_name = linkman.getLkm_name();
		System.out.println("��ϵ�����ƣ�"+lkm_name);
		if(lkm_name != null && !lkm_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		
		
		//��ȡ�ͻ�
		Customer customer = linkman.getCustomer();
		if(customer != null && customer.getCust_id()!= null){
			//ƴ�Ӳ�ѯ������
			criteria.add(Restrictions.eq("customer.cust_id", customer.getCust_id()));
		}
		
		//����ҵ���
		PageBean<Linkman> page = linkmanService.findByPage(this.getPageCode(),this.getPageSize(),criteria);
		//ѹջ
		this.setVs("page", page);
		return "page";
	}
	
	/**
	 * ��ʼ�������ҳ��
	 * @return
	 */
	public String initAddUI(){
	
		return "initAddUI";
	}
	/**
	 * ������ϵ�˵ķ���
	 * @return
	 */
	public String save(){
		linkmanService.save(linkman);
		return "save";
	}
	/**
	 * ɾ����ϵ��
	 * 
	 * @return
	 */
	public String delete(){
		
		//ɾ���ͻ�
		linkmanService.delete(linkman);

		return "delete";
	}
	/**
	 * ��ת����ʼ���޸�ҳ��
	 * @return
	 */
	public String initUpdate(){
		//linkmanĬ��ѹջ��ActionĬ��ѹջ��model��Action�����дgetModel(����linkman����)
		linkman = linkmanService.findById(linkman.getLkm_id());
		return "initUpdate";
	}
	/**
	 * �޸Ŀͻ��Ĺ���
	 * @return
	 * @throws IOException 
	 */
	public String update(){
		//������ϵ�˵���Ϣ
		linkmanService.update(linkman);
		
		return "update";
	}


}
