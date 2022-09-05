package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Member;
import hibernate.model.MemberRoles;
import hibernate.model.Role;

public class RoleDAO {

    // database access methods
    	// add role 
	public static void addRole(Role bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// assign role to member in MemberRoles table
	public static void addRoleToMember(int memberID, int roleID) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member m = session.get(Member.class, memberID);
		Role r = session.get(Role.class, roleID);

		MemberRoles mr = new MemberRoles();
		mr.setMember(m);
		mr.setRole(r);

		session.save(mr);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// get role
	public static Role getRole(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Role r = session.get(Role.class, id);
		tx.commit();
		 session.clear();
		 session.close();
		return r;
	}

	
	// get role with highest id
	public static Role getRoleWithHighestId() {
		Session session = SessionUtil.getSession();
		// String hql = "select max(id) from Role";
		Integer maxId = (Integer) session.createNativeQuery("select max(id) from Role").getSingleResult();

		Role role = session.get(Role.class, maxId);
		
		 session.clear();
		 session.close();
		return role;
	}

	
	// get list of all roles
	public static List<Role> getRoles() {
		Session session = SessionUtil.getSession();
		List<Role> list = session.createQuery(
			"select o from Role o",
			Role.class)
			.getResultList();


		for (Role t : list) {		
			System.out.println(t);
		}
		
		 session.clear();
		 session.close();
		return list;
	}
	
	// get roles by member id from MemberRoles table
	public static List<Role> getRolesByMemberId(int id) {

		// SELECT * FROM gcm.member_socials where member_id= '3'

		Session session = SessionUtil.getSession();
		List<Role> list = session.createQuery(
			"select role r from MemberRoles m where member.id= :id",
			Role.class)
			.setParameter("id", id).getResultList();


		for (Role o : list) {		
			System.out.println(o);
		}
		
		 session.clear();
		 session.close();
		return list;
	}

	// delete role from specific member in MemberRoles table
	public static void deleteRoleFromMember(int roleid, int memberid) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "delete from MemberRoles mr where role.id= :roleid and member.id= :memberid";
		Query query = session.createQuery(hql);
		query.setParameter("roleid", roleid);
		query.setParameter("memberid", memberid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete role
	public static void deleteRole(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Role role = session.get(Role.class, id);
		session.remove(role);
		tx.commit();
		 session.clear();
		 session.close();

	}

	
	// update role
	public static void updateRole(int id, Role role) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Role old = session.get(Role.class, id);

		old.setRoleName(role.getRoleName());
		old.setRoleDescription(role.getRoleDescription());

		session.saveOrUpdate(old);
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}
}
