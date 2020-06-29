package cn.sxt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import cn.sxt.pojo.User;

public class Test {
	public static void main(String[] args) {
		//读取src下hibernate.cfg.xml 如果不为configure指明参数 默认读取hibernate.cfg.xml
		Configuration cfg = new Configuration().configure();
		//3.x版本不需要ServiceRegistry
		//4.0 ServiceRegistryBuilder
		//获取注册对象4.3的创建办法
		ServiceRegistry registry = new StandardServiceRegistryBuilder()
									.applySettings(cfg.getProperties())
									.build();
		//SessionFactory是一个重量级对象 session的工厂 生命周期是进程级别的 支持集群 线程安全的
		SessionFactory factory = cfg.buildSessionFactory(registry);
		/*Session (org.hibernate.Session) 
			表示应用程序与持久储存层之间交互操作的一个单线程对象，
			此对象生存期很短。 其隐藏了JDBC连接，也是Transaction的工厂。
			 其会持有一个针对持久化对象的必选（第一级）缓存，在遍历对象图或者根据持久化标识查找对象时会用到
		   session支持数据库操作
		 * */
		Session session = null;
		//事务对象
		Transaction tx =null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			User u = new User("小红",22);
			//保存数据
			session.save(u);
			//提交事务
			tx.commit();
		}catch(Exception e){
			if(tx!=null)
			//回滚事务
			tx.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
		factory.close();
	}
}
