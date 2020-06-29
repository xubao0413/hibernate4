package cn.sxt.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import cn.sxt.pojo.Role;
import cn.sxt.pojo.User;
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
			
			User u1 = new User();
			u1.setName("��ҩʦ");
			u1.setAge(66);
			User u2 = new User();
			u2.setName("����");
			u2.setAge(56);
			User u3 = new User();
			u3.setName("����");
			u3.setAge(63);
			
			
			Role r1 = new Role("����");
			r1.getUsers().add(u1);
			r1.getUsers().add(u3);
			Role r2 = new Role("����");
			r2.getUsers().add(u2);
			session.save(r1);
			session.save(r2);
			
			session.save(u1);
			session.save(u2);
			session.save(u3);
			
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
			System.out.println("--------------");
			for(User u:role.getUsers()){
				System.out.println(u);
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
