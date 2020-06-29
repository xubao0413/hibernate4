package cn.sxt.dao;

import org.hibernate.Session;
import org.junit.Test;

import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;

public class HibernateTest {
	@Test
	public void testSession(){
		Session session = null;
		//瞬时状态
		User u = new User("西毒",70);
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//持久状态----做脏数据检查  将session数据和数据库同步
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
		//对象u是游离状态
		System.out.println("======"+u);
		
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//删除
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
		//瞬时状态
		System.out.println("======"+u);
	}
	@Test
	public void testGet(){
		Session session = null;
		User u =null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			//持久状态
			/*
			 * get 查询数据如果数据不存在则返回null
			 * load查询数据如果数据不存在则抛出异常
			 * */
			u = (User)session.get(User.class,4);
			//u = (User)session.load(User.class, 50);
			//瞬时状态
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
			//持久状态  save直接添加数据发出一条insert语句
			session.save(u);
			//saveOrUpdate 判断保存的对象是否有id,如果有则发出update语句
			//如果没有则发出insert语句
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
			u.setName("南帝");
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
