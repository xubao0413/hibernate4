package cn.sxt.dao;

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
}
