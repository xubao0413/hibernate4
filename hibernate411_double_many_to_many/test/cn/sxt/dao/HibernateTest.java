package cn.sxt.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import cn.sxt.pojo.Func;
import cn.sxt.pojo.Role;
import cn.sxt.util.HibernateUtil;

public class HibernateTest {
	@Test
	public void testCreateDB(){
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		//��һ������ �Ƿ��ӡ�ű����ڶ��������Ƿ������ݿ�
		se.create(true, true);
	}
	@Test 
	public void testInit(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			
			Func func1 = new Func("ϵͳ����","system.do");
			Func func2 = new Func("�û�����","user.do");
			Func func3 = new Func("�ƻ�����","plan.do");
			Func func4 = new Func("���۹���","sale.do");
			session.save(func1);
			session.save(func2);
			session.save(func3);
			session.save(func4);
			Role r1 = new Role("����Ա");
			r1.getFuncs().add(func1);
			r1.getFuncs().add(func2);
			r1.getFuncs().add(func3);
			r1.getFuncs().add(func4);
			Role r2 = new Role("�û�");
			r2.getFuncs().add(func3);
			r2.getFuncs().add(func4);
			session.save(r1);
			session.save(r2);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null){	
				session.close();
			}
		}
	}
	@Test
	public void testGet(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Role role = (Role)session.get(Role.class, 1);
			System.out.println("��ɫ����"+role.getName());
			System.out.println("=====================");
			System.out.println("��ɫ����Ӧ�Ĺ���");
			for(Func func:role.getFuncs()){
				System.out.println("��������"+func.getName()+"  url:"+func.getUrl());
			}
			System.out.println("====================");
			Func func = (Func)session.get(Func.class, 3);
			System.out.println("�������ƣ�"+func.getName());
			for(Role r:func.getRoles()){
				System.out.println("��ɫ����"+r.getName());
			}
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
}
