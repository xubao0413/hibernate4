package cn.sxt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import cn.sxt.pojo.Role;
import cn.sxt.pojo.User;
import cn.sxt.util.HibernateUtil;


public class HibernateTest {
	//hql查询  
	//from
	@Test
	public void testFrom(){
		try{
			Session session = HibernateUtil.getSession();
			//相当于sql: select * from t_role
			String hql="from cn.sxt.pojo.Role";
			//创建Query对象
			Query query = session.createQuery(hql);
			//查询
			List<Role> list = query.list();
			for(Role r:list){
				System.out.println("角色名："+r.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//设置别名 可以使用 as 关键字 也可以 在类名后加 空格 和别名
	@Test
	public void testFrom1(){
		Session session = HibernateUtil.getSession();
		//相当于 select * from t_role
		String hql="from cn.sxt.pojo.Role r";
		//创建Query对象
		Query query = session.createQuery(hql);
		//查询
		List<Role> list = query.list();
		for(Role r:list){
			System.out.println("角色名："+r.getName());
		}
	}
	//where语句
	@Test
	public void testWhere(){
		Session session = HibernateUtil.getSession();
		//根据id查询时  使用get/load会更好
		String hql="from Role where id=1";
		List<Role> list = session.createQuery(hql).list();
		for(Role r:list){
			System.out.println("角色名："+r.getName());
		}
	}
	//以参数查询  参数下标从0开始
	@Test
	public void testParams(){
		Session session = HibernateUtil.getSession();
		//根据id查询时  使用get/load会更好
		String hql="from Role where id=?";
		List<Role> list = session.createQuery(hql)
					.setInteger(0, 1)
					.list();
		for(Role r:list){
			System.out.println("角色名："+r.getName());
		}
	}
	//指定参数名
	@Test
	public void testParams1(){
		Session session = HibernateUtil.getSession();
		//根据id查询时  使用get/load会更好 
		String hql="from Role where id=:id";
		List<Role> list = session.createQuery(hql)
					//.setInteger("id", 1)
					.setParameter("id", 1)
					.list();
		for(Role r:list){
			System.out.println("角色名："+r.getName());
		}
	}
	//select语句
	@Test
	public void testSelect(){
		Session session = HibernateUtil.getSession();
		String hql="select u.name,u.age from User u";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object[] objs:list){
			System.out.println(objs[0]+"---"+objs[1]);
		}
	}
	//select语句  以对象返回
	@Test
	public void testSelectObject(){
		Session session = HibernateUtil.getSession();
		String hql="select new User(u.name,u.age) from User u";
		List<User> list = session.createQuery(hql).list();
		for(User u:list){
			System.out.println(u.getName()+"---"+u.getAge());
		}
	}
	//聚合函数
	@Test
	public void testFunction(){
		Session session = HibernateUtil.getSession();
		String hql="select count(u.id),max(u.age),min(u.age),avg(u.age) from User u";
		List<Object[]> list = session.createQuery(hql).list();
		System.out.println("人数\t最高年龄\t最小年龄\t平均年龄");
		for(Object[] objs:list){
			System.out.println(objs[0]+"\t"+objs[1]+"\t"+objs[2]+"\t"+objs[3]);
		}
	}
	//表达式
	@Test 
	public void testExpression(){
		Session session = HibernateUtil.getSession();
		String hql="from User where id in (:ids)";
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		List<User> list = session.createQuery(hql)
				.setParameterList("ids", ids)
				.list();
		for(User u:list){
			System.out.println(u.getName()+"---"+u.getAge());
		}
	}
	//order by
	@Test
	public void testOrderBy(){
		Session session = HibernateUtil.getSession();
		String hql="from User order by id desc";
		List<User> list = session.createQuery(hql)
				.list();
		for(User u:list){
			System.out.println(u.getId()+"----"+u.getName()+"---"+u.getAge());
		}
	}
	//group by 
	@Test
	public void testGroup(){
		Session session = HibernateUtil.getSession();
		String hql="select sex,count(*) from User group by sex";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object[] objs:list){
			System.out.println(objs[0]+"\t"+objs[1]);
		}
	}
	//分页
	@Test
	public void testPage(){
		Session session = HibernateUtil.getSession();
		String hql="from User order by id desc";
		List<User> list = session.createQuery(hql)
				.setFirstResult(2)//(currentPage-1)*pageSize
				.setMaxResults(2)//pageSize
				.list();
		for(User u:list){
			System.out.println(u.getId()+"----"+u.getName()+"---"+u.getAge());
		}
	}
	//unique唯一
	@Test
	public void testUnique(){
		Session session = HibernateUtil.getSession();
		//根据id查询时  使用get/load会更好
		String hql="from Role where id=?";
		Role role = (Role)session.createQuery(hql).setInteger(0, 1).uniqueResult();
		System.out.println(role.getName());
	}
	@Test
	public void testUnique1(){
		Session session = HibernateUtil.getSession();
		//根据id查询时  使用get/load会更好
		String hql="from Role";
		//返回多个值时 将会抛出异常
		Role role = (Role)session.createQuery(hql).uniqueResult();
		System.out.println(role.getName());
	}
	//命名查询--使用命名查询，名称在应用中不能重复
	@Test
	public void testNamedQuery(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.getNamedQuery("getAllUser").list();
		for(User u:list){
			System.out.println(u.getName()+"---"+u.getAge());
		}
	}
	//join
	@Test
	public void testJoin(){
		Session session = HibernateUtil.getSession();
		User user = (User)session.get(User.class, 1);
		System.out.println(user+"\t 角色名："+user.getRole().getName());
	}
	@Test
	public void testJoin1(){
		Session session = HibernateUtil.getSession();
		String hql="from User u where u.role.name='岛主'";
		List<User> list = session.createQuery(hql).list();
		for(User u:list){
			System.out.println(u.getName()+"---"+u.getAge()+"---");
		}
	}
	@Test
	public void testJoin2(){
		Session session = HibernateUtil.getSession();
		String hql="select u.id,u.name,u.age,u.sex,r.name from User u left join u.role r";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object[] objs:list){
			System.out.println(objs[0]+"\t"+objs[1]+"\t"+objs[2]+"\t"+objs[3]+"\t"+objs[4]);
		}
	}
	//--本地查询
	@Test
	public void testSQL1(){
		Session session = HibernateUtil.getSession();
		String sql="select * from t_role";
		List<Role> list = session.createSQLQuery(sql).addEntity(Role.class).list();
		for(Role r:list){
			System.out.println(r);
		}
	}
	@Test
	public void testSQL2(){
		Session session = HibernateUtil.getSession();
		String sql="select id,name from t_role";
		List<Object[]> list = session.createSQLQuery(sql).list();
		for(Object[] r:list){
			System.out.println(r[0]+"--"+r[1]);
		}
	}
	@Test
	public void testSQL3(){
		Session session = HibernateUtil.getSession();
		String sql="select age div 60 age,count(*) from t_user group by age div 60";
		List<Object[]> list = session.createSQLQuery(sql).list();
		for(Object[] r:list){
			System.out.println(r[0]+"--"+r[1]);
		}
	}
	//本地查询--命名查询
	@Test
	public void testSQL4(){
		Session session = HibernateUtil.getSession();
		List<Object[]> list = session.getNamedQuery("getAllRole1").list();
		for(Object[] r:list){
			System.out.println(r[0]+"--"+r[1]);
		}
	}
	@Test
	public void testSQL5(){
		Session session = HibernateUtil.getSession();
		List<Role> list = session.getNamedQuery("getAllRole2").list();
		for(Role r:list){
			System.out.println(r);
		}
	}
	//Critera查询--完全面向对象的查询
	//查询所有
	@Test
	public void testCriteraGetAll(){
		Session session = HibernateUtil.getSession();
		List<Role> list = session.createCriteria(Role.class).list();
		for(Role r:list){
			System.out.println(r);
		}
	}
	//条件查询
	@Test
	public void testCriteraCondition(){
		Session session = HibernateUtil.getSession();
		List<Role> list = session.createCriteria(Role.class)
					.add(Restrictions.eq("id", 1))
					.list();
		for(Role r:list){
			System.out.println(r);
		}
	}
	@Test
	public void testCriteraCondition1(){
		Session session = HibernateUtil.getSession();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		List<Role> list = session.createCriteria(Role.class)
					.add(Restrictions.in("id", ids))
					.list();
		for(Role r:list){
			System.out.println(r);
		}
	}
	//and 连接的条件
	@Test
	public void testCriteraCondition2(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(
							Restrictions.and(Restrictions.eq("sex", "男"),
									Restrictions.lt("age", 60)))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//or 连接的条件
	@Test
	public void testCriteraCondition3(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(
							Restrictions.or(Restrictions.eq("sex", "男"),
									Restrictions.lt("age", 60)))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//模糊查询
	@Test
	public void testCriteraCondition4(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(Restrictions.like("name", "郭%"))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//模糊查询1
	@Test
	public void testCriteraCondition5(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(Restrictions.like("name", "郭",MatchMode.START))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}	
	//模糊查询2
	@Test
	public void testCriteraCondition6(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					//忽略大小写匹配	
					.add(Restrictions.ilike("name", "l",MatchMode.ANYWHERE))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//分页
	@Test
	public void testCriteriaPage(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
							//分页
							.setFirstResult(0)
							.setMaxResults(3)
							.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//order by
	@Test
	public void testCriteriaOrder(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
							.addOrder(Order.desc("id"))
							.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//投影--统计个数
	@Test
	public void testCriteriaProjection(){
		Session session = HibernateUtil.getSession();
		List list = session.createCriteria(User.class)
							.setProjection(Projections.rowCount())
							.list();
		System.out.println(list.get(0));
	}
	@Test
	public void testCriteriaProjection1(){
		Session session = HibernateUtil.getSession();
		List<Object[]> list = session.createCriteria(User.class)
							.setProjection(Projections.projectionList()
									.add(Projections.rowCount())
									.add(Projections.avg("age"))
									.add(Projections.max("age"))
									)
							.list();
		System.out.println(list.get(0)[0]+"--"+list.get(0)[1]+"---"+list.get(0)[2]);
	}
	//过滤查询
	@Test
	public void testFilter(){
		Session session = HibernateUtil.getSession();
		//启动
		session.enableFilter("getUserFilter").setParameter("id", 1);
//		List list = session.createQuery("select name from User")
//				.list();
		List<User> list = session.createCriteria(User.class)
				.list();
		System.out.println(list.get(0));
	}
}
