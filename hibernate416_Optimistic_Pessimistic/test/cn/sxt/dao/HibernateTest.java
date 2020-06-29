package cn.sxt.dao;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

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
		Session session = HibernateUtil.getSession();
		User user = new User("zhangx",54);
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
	}
	/**
	 * �ֹ���
	 * ʵ�ַ�ʽ��User.hbm.xml��class��ǩ���<version name="version"/>
	 */
	@Test
	public void testOptimistic(){
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		User u1 = (User)session.get(User.class, 1);
		u1.setAge(20);
		    //ģ�������߳�
			Session session1 = HibernateUtil.getSession();
			session1.getTransaction().begin();
			User u2 = (User)session.get(User.class, 1);
			u2.setAge(30);
			session1.getTransaction().commit();
		session.getTransaction().commit();
	}
	/**
	 * ������
	 * ������̻߳���������Ϊ��������ȵ�����δ�ύ�������ύ��ɣ��ٲ����ü�¼
	 */
	@Test
	public void testPessimistic(){
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
			//ģ�������߳�
			User user = (User)session.get(User.class, 1);
			user.setAge(55);
			session.update(user);
		session.getTransaction().commit();
	}
	@Test
	public void testPessimistic1(){
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		//ʹ�ñ����� �����ز�������
		User user = (User)session.load(User.class, 1, LockOptions.UPGRADE);
		user.setAge(20);
		session.getTransaction().commit();
		
	}
}
