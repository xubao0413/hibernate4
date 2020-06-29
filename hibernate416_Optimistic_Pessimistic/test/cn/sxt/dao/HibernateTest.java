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
		//第一个参数 是否打印脚本，第二个参数是否导入数据库
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
	 * 乐观锁
	 * 实现方式：User.hbm.xml，class标签添加<version name="version"/>
	 */
	@Test
	public void testOptimistic(){
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		User u1 = (User)session.get(User.class, 1);
		u1.setAge(20);
		    //模拟其他线程
			Session session1 = HibernateUtil.getSession();
			session1.getTransaction().begin();
			User u2 = (User)session.get(User.class, 1);
			u2.setAge(30);
			session1.getTransaction().commit();
		session.getTransaction().commit();
	}
	/**
	 * 悲观锁
	 * 结果：线程会阻塞，因为悲观锁会等到其他未提交的事务提交完成，再操作该记录
	 */
	@Test
	public void testPessimistic(){
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
			//模拟其他线程
			User user = (User)session.get(User.class, 1);
			user.setAge(55);
			session.update(user);
		session.getTransaction().commit();
	}
	@Test
	public void testPessimistic1(){
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		//使用悲观锁 懒加载不起作用
		User user = (User)session.load(User.class, 1, LockOptions.UPGRADE);
		user.setAge(20);
		session.getTransaction().commit();
		
	}
}
