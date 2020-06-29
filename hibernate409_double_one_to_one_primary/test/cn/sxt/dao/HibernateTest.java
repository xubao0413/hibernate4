package cn.sxt.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import cn.sxt.pojo.IdCard;
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
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			IdCard idCard = new IdCard();
			idCard.setId("321023123123123");
			idCard.setAddress("北京");
			IdCard idCard1 = new IdCard();
			idCard1.setId("654352345243523");
			idCard1.setAddress("天津");
			session.save(idCard);
			session.save(idCard1);
			
			User u1 = new User("张三",23,idCard);
			User u2 = new User("李四",22,idCard1);
			session.save(u1);
			session.save(u2);
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
			User user = (User)session.get(User.class, "321023123123123");
			System.out.println("姓名："+user.getName()+"  身份证："+user.getIdCard().getId()+" 地址："+user.getIdCard().getAddress());
			System.out.println("==================");
			IdCard card = (IdCard)session.get(IdCard.class, "321023123123123");
			System.out.println("身份证："+card.getId()+"  地址："+card.getAddress()+"  姓名："+card.getUser().getName());
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
