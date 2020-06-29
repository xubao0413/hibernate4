package cn.sxt.vo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;

public class HibernateTest {
	//向数据库添加数据
	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//获取事务对象
			tx = session.getTransaction();
			//开启事务
			tx.begin();
			
			User u = new User("张学友",55);
			
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
	//获取数据
	@Test
	public void testGetById(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//获取事务对象
			tx = session.getTransaction();
			//开启事务
			tx.begin();
			//获取对象   第一参数 是对象的类型  第二个参数是 主键值
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
	//获取所有对象
	@Test
	public void testGetAll(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//获取事务对象
			tx = session.getTransaction();
			//开启事务
			tx.begin();
			//查询所有
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
	//修改数据
	@Test
	public void testUpdate(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			//获取事务对象
			tx = session.getTransaction();
			//开启事务
			tx.begin();
			//获取对象   第一参数 是对象的类型  第二个参数是 主键值
			//User u = (User)session.get(User.class, 2);
			User u = (User)session.load(User.class, 2);
			u.setAge(60);
			//修改数据
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
			//获取事务对象
			tx = session.getTransaction();
			//开启事务
			tx.begin();
			//获取对象   第一参数 是对象的类型  第二个参数是 主键值
			//User u = (User)session.get(User.class, 2);
			User u = (User)session.load(User.class, 2);
			//删除对象
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
