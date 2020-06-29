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
	//hql��ѯ  
	//from
	@Test
	public void testFrom(){
		try{
			Session session = HibernateUtil.getSession();
			//�൱��sql: select * from t_role
			String hql="from cn.sxt.pojo.Role";
			//����Query����
			Query query = session.createQuery(hql);
			//��ѯ
			List<Role> list = query.list();
			for(Role r:list){
				System.out.println("��ɫ����"+r.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//���ñ��� ����ʹ�� as �ؼ��� Ҳ���� ��������� �ո� �ͱ���
	@Test
	public void testFrom1(){
		Session session = HibernateUtil.getSession();
		//�൱�� select * from t_role
		String hql="from cn.sxt.pojo.Role r";
		//����Query����
		Query query = session.createQuery(hql);
		//��ѯ
		List<Role> list = query.list();
		for(Role r:list){
			System.out.println("��ɫ����"+r.getName());
		}
	}
	//where���
	@Test
	public void testWhere(){
		Session session = HibernateUtil.getSession();
		//����id��ѯʱ  ʹ��get/load�����
		String hql="from Role where id=1";
		List<Role> list = session.createQuery(hql).list();
		for(Role r:list){
			System.out.println("��ɫ����"+r.getName());
		}
	}
	//�Բ�����ѯ  �����±��0��ʼ
	@Test
	public void testParams(){
		Session session = HibernateUtil.getSession();
		//����id��ѯʱ  ʹ��get/load�����
		String hql="from Role where id=?";
		List<Role> list = session.createQuery(hql)
					.setInteger(0, 1)
					.list();
		for(Role r:list){
			System.out.println("��ɫ����"+r.getName());
		}
	}
	//ָ��������
	@Test
	public void testParams1(){
		Session session = HibernateUtil.getSession();
		//����id��ѯʱ  ʹ��get/load����� 
		String hql="from Role where id=:id";
		List<Role> list = session.createQuery(hql)
					//.setInteger("id", 1)
					.setParameter("id", 1)
					.list();
		for(Role r:list){
			System.out.println("��ɫ����"+r.getName());
		}
	}
	//select���
	@Test
	public void testSelect(){
		Session session = HibernateUtil.getSession();
		String hql="select u.name,u.age from User u";
		List<Object[]> list = session.createQuery(hql).list();
		for(Object[] objs:list){
			System.out.println(objs[0]+"---"+objs[1]);
		}
	}
	//select���  �Զ��󷵻�
	@Test
	public void testSelectObject(){
		Session session = HibernateUtil.getSession();
		String hql="select new User(u.name,u.age) from User u";
		List<User> list = session.createQuery(hql).list();
		for(User u:list){
			System.out.println(u.getName()+"---"+u.getAge());
		}
	}
	//�ۺϺ���
	@Test
	public void testFunction(){
		Session session = HibernateUtil.getSession();
		String hql="select count(u.id),max(u.age),min(u.age),avg(u.age) from User u";
		List<Object[]> list = session.createQuery(hql).list();
		System.out.println("����\t�������\t��С����\tƽ������");
		for(Object[] objs:list){
			System.out.println(objs[0]+"\t"+objs[1]+"\t"+objs[2]+"\t"+objs[3]);
		}
	}
	//���ʽ
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
	//��ҳ
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
	//uniqueΨһ
	@Test
	public void testUnique(){
		Session session = HibernateUtil.getSession();
		//����id��ѯʱ  ʹ��get/load�����
		String hql="from Role where id=?";
		Role role = (Role)session.createQuery(hql).setInteger(0, 1).uniqueResult();
		System.out.println(role.getName());
	}
	@Test
	public void testUnique1(){
		Session session = HibernateUtil.getSession();
		//����id��ѯʱ  ʹ��get/load�����
		String hql="from Role";
		//���ض��ֵʱ �����׳��쳣
		Role role = (Role)session.createQuery(hql).uniqueResult();
		System.out.println(role.getName());
	}
	//������ѯ--ʹ��������ѯ��������Ӧ���в����ظ�
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
		System.out.println(user+"\t ��ɫ����"+user.getRole().getName());
	}
	@Test
	public void testJoin1(){
		Session session = HibernateUtil.getSession();
		String hql="from User u where u.role.name='����'";
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
	//--���ز�ѯ
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
	//���ز�ѯ--������ѯ
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
	//Critera��ѯ--��ȫ�������Ĳ�ѯ
	//��ѯ����
	@Test
	public void testCriteraGetAll(){
		Session session = HibernateUtil.getSession();
		List<Role> list = session.createCriteria(Role.class).list();
		for(Role r:list){
			System.out.println(r);
		}
	}
	//������ѯ
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
	//and ���ӵ�����
	@Test
	public void testCriteraCondition2(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(
							Restrictions.and(Restrictions.eq("sex", "��"),
									Restrictions.lt("age", 60)))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//or ���ӵ�����
	@Test
	public void testCriteraCondition3(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(
							Restrictions.or(Restrictions.eq("sex", "��"),
									Restrictions.lt("age", 60)))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//ģ����ѯ
	@Test
	public void testCriteraCondition4(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(Restrictions.like("name", "��%"))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//ģ����ѯ1
	@Test
	public void testCriteraCondition5(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					.add(Restrictions.like("name", "��",MatchMode.START))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}	
	//ģ����ѯ2
	@Test
	public void testCriteraCondition6(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
					//���Դ�Сдƥ��	
					.add(Restrictions.ilike("name", "l",MatchMode.ANYWHERE))
					.list();
		for(User r:list){
			System.out.println(r);
		}
	}
	//��ҳ
	@Test
	public void testCriteriaPage(){
		Session session = HibernateUtil.getSession();
		List<User> list = session.createCriteria(User.class)
							//��ҳ
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
	//ͶӰ--ͳ�Ƹ���
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
	//���˲�ѯ
	@Test
	public void testFilter(){
		Session session = HibernateUtil.getSession();
		//����
		session.enableFilter("getUserFilter").setParameter("id", 1);
//		List list = session.createQuery("select name from User")
//				.list();
		List<User> list = session.createCriteria(User.class)
				.list();
		System.out.println(list.get(0));
	}
}
