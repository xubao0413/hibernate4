package cn.sxt.vo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;

public class HibernateTest {
	//�����ݿ��������
	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//��ȡ�������
			tx = session.getTransaction();
			//��������
			tx.begin();
			
			User u = new User("��ѧ��",55);
			
			session.save(u);
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	//��ȡ����
	@Test
	public void testGetById(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//��ȡ�������
			tx = session.getTransaction();
			//��������
			tx.begin();
			//��ȡ����   ��һ���� �Ƕ��������  �ڶ��������� ����ֵ
			//User u = (User)session.get(User.class, 2);
			User u = (User)session.load(User.class, 2);
			System.out.println(u);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	//��ȡ���ж���
	@Test
	public void testGetAll(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//��ȡ�������
			tx = session.getTransaction();
			//��������
			tx.begin();
			//��ѯ����
			List<User> list = session.createCriteria(User.class).list();
			for(User u:list){
				System.out.println(u);
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	//�޸�����
	@Test
	public void testUpdate(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//��ȡ�������
			tx = session.getTransaction();
			//��������
			tx.begin();
			//��ȡ����   ��һ���� �Ƕ��������  �ڶ��������� ����ֵ
			//User u = (User)session.get(User.class, 2);
			User u = (User)session.load(User.class, 2);
			u.setAge(60);
			//�޸�����
			session.update(u);
			System.out.println(u);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//��ȡ�������
			tx = session.getTransaction();
			//��������
			tx.begin();
			//��ȡ����   ��һ���� �Ƕ��������  �ڶ��������� ����ֵ
			//User u = (User)session.get(User.class, 2);
			User u = (User)session.load(User.class, 2);
			//ɾ������
			session.delete(u);
			System.out.println(u);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
	}
}
