package cn.sxt.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;


public class HibernateTest {
	@Test
	public void testGet(){
		Session session = HibernateUtil.getSession();
		//即时加载
		User u = (User)session.get(User.class, 1);
		System.out.println(u.getName());
		System.out.println("------------------");
		User u1 = (User)session.get(User.class, 1);
		System.out.println(u1.getName());
		session.close();
	}
	@Test
	public void testLoad(){
		Session session = HibernateUtil.getSession();
		User u = (User)session.load(User.class, 1);
		System.out.println(u.getId());
		System.out.println(u.getName());
		System.out.println("------------------");
		User u1 = (User)session.load(User.class, 1);
		System.out.println(u1.getName());
		session.close();
	}
	@Test
	public void testList(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class).list();
		for(User u:list){
			System.out.println(u);
		}
		System.out.println("-------------------");
		User u1 = (User)session.get(User.class, 4);
		System.out.println("name="+u1.getName());
		System.out.println("-------------------");
		List<User> list1 = session.createCriteria(User.class).list();
		for(User u:list1){
			System.out.println(u);
		}
	}
	@Test
	public void testIterate(){
		Session session = HibernateUtil.getSession();
		Iterator<User> iterator = session.createQuery("from User").iterate();
		for(;iterator.hasNext();){
			System.out.println(iterator.next());
		}
	}
	@Test
	public void testIterate1(){
		Session session = HibernateUtil.getSession();
		Iterator<User> iterator = session.createQuery("from User").iterate();
		for(;iterator.hasNext();){
			System.out.println(iterator.next());
		}
		System.out.println("-----------------------");
		Iterator<User> iterator1 = session.createQuery("from User").iterate();
		for(;iterator1.hasNext();){
			System.out.println(iterator1.next());
		}
	}
	@Test
	public void testClear(){
		Session session = HibernateUtil.getSession();
		//即时加载
		User u = (User)session.get(User.class, 1);
		System.out.println(u.getName());
		//清空缓存中的所有数据
		session.clear();
		System.out.println("------------------");
		User u1 = (User)session.get(User.class, 1);
		System.out.println(u1.getName());
		session.close();
	}
	@Test
	public void testFlush(){
		Session session = HibernateUtil.getSession();
		User u = (User)session.get(User.class, 1);
		System.out.println(u.getName());
		u.setAge(100);
		//刷新
		session.flush();
		System.out.println("------------------");
		User u1 = (User)session.get(User.class, 1);
		System.out.println(u1.getName());
		session.close();
	}
	@Test
	public void testEvict(){
		Session session = HibernateUtil.getSession();
		User u = (User)session.get(User.class, 1);
		System.out.println(u.getName());
		//从缓存中清除指定对象
		session.evict(u);
		System.out.println("------------------");
		User u1 = (User)session.get(User.class, 1);
		System.out.println(u1.getName());
		session.close();
	}
	@Test
	public void testSecondCache(){
		Session session = HibernateUtil.getSession();
		//即时加载
		User u = (User)session.get(User.class, 1);
		System.out.println(u.getName());
		session.close();
		System.out.println("------------------");
		session = HibernateUtil.getSession(); 
		User u1 = (User)session.get(User.class, 1);
		System.out.println(u1.getName());
		session.close();
	}
	@Test
	public void testQueryCache(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class).setCacheable(true).list();
		for(User u:list){
			System.out.println(u);
		}
		System.out.println("-------------------");
		User u1 = (User)session.get(User.class, 4);
		System.out.println("name="+u1.getName());
		System.out.println("-------------------");
		List<User> list1 = session.createCriteria(User.class).setCacheable(true).list();
		for(User u:list1){
			System.out.println(u);
		}
	}
}
