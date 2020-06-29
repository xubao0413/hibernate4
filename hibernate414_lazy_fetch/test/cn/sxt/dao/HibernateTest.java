package cn.sxt.dao;

import org.hibernate.Session;
import org.junit.Test;
import cn.sxt.pojo.Role;
import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;

public class HibernateTest {
	@Test
	public void testGet(){
		Session session = HibernateUtil.getSession();
		//即时加载
		User u = (User)session.get(User.class, 1);
		System.out.println("id="+u.getId());
		System.out.println("----------");
		System.out.println("name="+u.getName());
	}
	@Test
	public void testLoad(){
		Session session = HibernateUtil.getSession();
		//延迟加载
		User u = (User)session.load(User.class, 1);
		System.out.println("id="+u.getId());
		System.out.println("----------");
		System.out.println("name="+u.getName());
	}
	@Test
	public void testManyToOne(){
		Session session = HibernateUtil.getSession();
		//即时加载
		User u = (User)session.get(User.class, 1);
		System.out.println("name="+u.getName());
		System.out.println(u.getRole());
		System.out.println("roleName="+u.getRole().getName());
	}
	@Test
	public void testOneToMany(){
		Session session = HibernateUtil.getSession();
		//即时加载
		Role role = (Role)session.get(Role.class, 1);
		System.out.println("name="+role.getName());
		System.out.println("-----------------");
		System.out.println(role.getUsers().size());
	}
	@Test
	public void testFetch(){
		Session session = HibernateUtil.getSession();
		User u = (User)session.get(User.class, 1);
		System.out.println("name="+u.getName());
		System.out.println(u.getRole());
		System.out.println("roleName="+u.getRole().getName());
	}
}
