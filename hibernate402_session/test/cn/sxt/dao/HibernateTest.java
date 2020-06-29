package cn.sxt.dao;

import org.hibernate.Session;
import org.junit.Test;

import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;

public class HibernateTest {
	@Test
	public void testSession(){
		Session session = null;
		//˲ʱ״̬
		User u = new User("����",70);
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//�־�״̬----�������ݼ��  ��session���ݺ����ݿ�ͬ��
			session.save(u);
			u.setAge(80);
			//session.update(u);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null){
				
				session.close();
			}
		}
		//����u������״̬
		System.out.println("======"+u);
		
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//ɾ��
			session.delete(u);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null){
				
				session.close();
			}
		}
		//˲ʱ״̬
		System.out.println("======"+u);
	}
	@Test
	public void testGet(){
		Session session = null;
		User u =null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//�־�״̬
			/*
			 * get ��ѯ����������ݲ������򷵻�null
			 * load��ѯ����������ݲ��������׳��쳣
			 * */
			u = (User)session.get(User.class,4);
			//u = (User)session.load(User.class, 50);
			//˲ʱ״̬
			session.delete(u);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null){
				
				session.close();
			}
		}
		System.out.println("===="+u);
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//�־�״̬  saveֱ��������ݷ���һ��insert���
			session.save(u);
			//saveOrUpdate �жϱ���Ķ����Ƿ���id,������򷢳�update���
			//���û���򷢳�insert���
			//session.saveOrUpdate(u);
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
	public void testDelete(){
		Session session = null;
		User u =null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			u = new User();
			u.setId(7);
			session.delete(u);
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
	public void testUpdate(){
		Session session = null;
		User u =null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			u = new User();
			u.setId(3);
			u.setName("�ϵ�");
			u.setAge(66);
			session.update(u);
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
