package com.cn.action;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.alibaba.fastjson.annotation.JSONField;
import com.cn.domain.Customer;
import com.cn.domain.Dict;
import com.cn.domain.PageBean;
import com.cn.service.CustomerService;
import com.cn.utils.FastJsonUtil;
import com.cn.utils.UploadUtils;



public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	
	private static final long serialVersionUID = -8876151471501110413L;
	//�ֶ�new��ģ��������װ����
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	//�ṩservice�ĳ�Ա���ԣ��ṩset����
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//���������ķ�ʽ
	//��ǰҳ��Ĭ��ֵ����1
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if(pageCode ==null){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	//ÿҳ��ʾ�����ݵ�����
	private Integer pageSize = 2;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * ��ҳ��ѯ����
	 */
	public String findByPage(){
		//����Serviceҵ���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//ƴ�Ӳ�ѯ������
		String cust_name = customer.getCust_name();
		System.out.println("cust_name:"+cust_name);
		if(cust_name != null && !cust_name.trim().isEmpty()){
			//˵�����ͻ�����������ֵ��
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		
		//ƴ�ӿͻ�����
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()){
			//˵�����ͻ�����϶�ѡ����һ������
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		//�ͻ���Դ
		Dict source = customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()){
			//˵�����ͻ�����϶�ѡ����һ������
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		
		//��ѯ
		PageBean<Customer> page = customerService.findByPage(pageCode,pageSize,criteria);
		//ѹջ
		ValueStack vs = ActionContext.getContext().getValueStack();
		//ջ����map<"page",page����
		vs.set("page",page);
		
		
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
	 * �ļ��ϴ�����Ҫ��CustomerAction���ж����Ա�����ԣ��������й����
	 * private File upload;  //��ʾҪ�ϴ����ļ��������upload�Ǻ�add.jspҳ���<input type="file" name="upload"/>name����������ͬ��
	 * private String uploadFileName;//��ʾ���ϴ��ļ������ƣ�û���������룩����������������+FileName
	 * private String uploadContentType;//��ʾ�ϴ��ļ���MIME����
	 * 
	 */
	//Ҫ�ϴ����ļ�
	private File upload;
	//�ļ�����
	private String uploadFileName;
	//�ļ���MIME����
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * ����ͻ��ķ���
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException{
		if(uploadFileName != null){
			//��ӡ
			System.out.println("�ļ�����"+uploadContentType);
			//���ļ������ƴ���һ��
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//���ļ��ϴ���D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload
			String path = "D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload\\";
			
			//����file����
			File file = new File(path+uuidName);
			//�򵥷�ʽ
			FileUtils.copyFile(upload, file);
			
			//���ϴ����ļ���·�������浽�ͻ�����
			customer.setFilePath(path+uuidName);
			
		}
		
		
		
		customerService.save(customer);
		return "save";
	}
	/**
	 * ɾ���ͻ�
	 * @return
	 */
	public String delete(){
		//ɾ���ͻ�����ȡ�ͻ�����Ϣ��ȡ���ϴ��ļ���·��
		customer = customerService.findById(customer.getCust_id());
		//��ȡ�ϴ��ļ���·��
		String filePath = customer.getFilePath();
		//ɾ���ͻ�
		customerService.delete(customer);
		//��ɾ���ļ�
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		return "delete";
	}
	/**
	 * ��ת����ʼ���޸�ҳ��
	 * @return
	 */
	public String initUpdate(){
		//customerĬ��ѹջ��ActionĬ��ѹջ��model��Action�����дgetModel(����customer����)
		customer = customerService.findById(customer.getCust_id());
		return "initUpdate";
	}
	/**
	 * �޸Ŀͻ��Ĺ���
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException{
		//�жϣ�˵���ͻ��ϴ����µ�ͼƬ
		if(uploadFileName != null){
			//��ɾ���ɵ�ͼƬ
			String oldFilePath = customer.getFilePath();
			if(oldFilePath != null && !oldFilePath.trim().isEmpty()){
				//˵�����ɵ�·�����ڣ�ɾ��ͼƬ
				File f = new File(oldFilePath);
				f.delete();
			}
			//�ϴ��µ�ͼƬ
			//�ȴ����ļ������Ƶ�����
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//���ļ��ϴ���D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload
			String path = "D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload\\";
			
			//����file����
			File file = new File(path+uuidName);
			//�򵥷�ʽ
			FileUtils.copyFile(upload, file);
			
			//���ϴ����ļ���·�������浽�ͻ�����
			customer.setFilePath(path+uuidName);
		}
		//���¿ͻ�����Ϣ
		customerService.update(customer);
		
		return "update";
	}
	/**
	 * ��ѯ���еĿͻ�
	 * @return
	 */
	public String findAll(){
		List<Customer> list = customerService.findAll();
		//ת����json
		String jsonString = FastJsonUtil.toJSONString(list);//תjsonʱ�����json����ѭ�����⣬��ΪCustomer������linkmans�ļ��ϣ�Linkman������Customer����
		                                                    //��������set�ļ��ϣ���linkmans�ļ��ϣ��ϼ�@JSONField(serialize=false)//Ĭ�ϲ���set���Ͻ���json��ת��
		HttpServletResponse response = ServletActionContext.getResponse();
		
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	/**
	 * ͳ����Դ�ͻ�������
	 * @return
	 */
	public String findBySource(){
		List<Object[]> list = customerService.findBySource();
		//ѹջ
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);
		
		return "findBySource";
	}
}
