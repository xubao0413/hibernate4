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
		//��һ������ �Ƿ��ӡ�ű����ڶ��������Ƿ������ݿ�
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
			idCard.setAddress("����");
			IdCard idCard1 = new IdCard();
			idCard1.setId("654352345243523");
			idCard1.setAddress("���");
			session.save(idCard);
			session.save(idCard1);
			
			User u1 = new User("����",23,idCard);
			User u2 = new User("����",22,idCard1);
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
			System.out.println("������"+user.getName()+"  ���֤��"+user.getIdCard().getId()+" ��ַ��"+user.getIdCard().getAddress());
			System.out.println("==================");
			IdCard card = (IdCard)session.get(IdCard.class, "321023123123123");
			System.out.println("���֤��"+card.getId()+"  ��ַ��"+card.getAddress()+"  ������"+card.getUser().getName());
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
