package hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.DiscordSessionUtil;
import hibernate.model.Member;
import hibernate.model.MemberRoles;
import hibernate.model.Role;

public class RoleDAO {

	public static void addRole(Role bean) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean);
		tx.commit();
		session.close();
	}

	public static void addRoleToMember(int memberID, int roleID) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member m = session.get(Member.class, memberID);
		Role r = session.get(Role.class, roleID);

		MemberRoles mr = new MemberRoles();
		mr.setMember(m);
		mr.setRole(r);

		session.save(mr);
		tx.commit();
		session.close();
	}

	public static Role getRole(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Role r = session.get(Role.class, id);

		return r;
	}

	public static Role getRoleWithHighestId() {
		Session session = DiscordSessionUtil.getSession();
		// String hql = "select max(id) from Role";
		Integer maxId = (Integer) session.createNativeQuery("select max(id) from Role").getSingleResult();

		Role role = session.get(Role.class, maxId);

		return role;
	}

	public static List<Role> getRoles() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Role";
		Query query = session.createQuery(hql);
		List<Role> roles = query.list();
		session.close();
		return roles;
	}

	public static List<Role> getRolesByMemberId(int id) {

		// SELECT * FROM gcm.member_socials where member_id= '3'

		Session session = DiscordSessionUtil.getSession();
		String hql = "from MemberRoles role_id where member_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<MemberRoles> rolesMember = query.list();
		List<Role> filteredRolesList = new ArrayList<>();

		for (MemberRoles m : rolesMember) {
			int sId = m.getRole().getId();
			Role s = session.get(Role.class, sId);
			filteredRolesList.add(s);

		}

		session.close();
		return filteredRolesList;
	}

	public static void deleteRoleFromMember(int roleid, int memberid) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberRoles Table
		// ROM a WHERE a.b = :par1 OR a.c = :par2").setParameter("par1",
		// obj).setParameter("par2", obj);
		String hql = "delete from MemberRoles id where role_id= :roleid and member_id= :memberid";
		Query query = session.createQuery(hql);
		query.setParameter("roleid", roleid);
		query.setParameter("memberid", memberid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Role Table
//		Role role = session.get(Role.class, id);
//		session.remove(role);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteRole(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Role role = session.get(Role.class, id);
		session.remove(role);
		tx.commit();
		session.close();

	}

	public static void updateRole(int id, Role role) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Role old = session.get(Role.class, id);

		old.setRoleName(role.getRoleName());
		old.setRoleDescription(role.getRoleDescription());

		session.saveOrUpdate(old);
		session.flush();
		tx.commit();
		session.close();
	}
}
