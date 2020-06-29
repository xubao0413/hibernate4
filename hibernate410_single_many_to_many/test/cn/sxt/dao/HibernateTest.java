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
		//第一个参数 是否打印脚本，第二个参数是否导入数据库
		se.create(true, true);
	}
	@Test 
	public void testInit(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Func func1 = new Func("系统管理","system.do");
			Func func2 = new Func("用户管理","user.do");
			Func func3 = new Func("计划管理","plan.do");
			Func func4 = new Func("销售管理","sale.do");
			session.save(func1);
			session.save(func2);
			session.save(func3);
			session.save(func4);
			Role r1 = new Role("管理员");
			r1.getFuncs().add(func1);
			r1.getFuncs().add(func2);
			r1.getFuncs().add(func3);
			r1.getFuncs().add(func4);
			Role r2 = new Role("用户");
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
			System.out.println("角色名："+role.getName());
			System.out.println("=====================");
			System.out.println("角色所对应的功能");
			for(Func func:role.getFuncs()){
				System.out.println("功能名："+func.getName()+"  url:"+func.getUrl());
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
