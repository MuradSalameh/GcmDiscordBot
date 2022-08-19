package hibernate.tests;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.DiscordSessionUtil;
import hibernate.dao.RoleDAO;
import hibernate.model.Role;

public class RoleDAOTest {

	public static void main(String[] args) {

		Session session = DiscordSessionUtil.getSession();
		int id = 1;
		int id2 = 5;
		int id3 = 5;

		// --------- addRole() Test -----------//

//		addTestRole();
//		addTestRole();
//		addTestRole();
//		addTestRole();

		// --------- getRoles() Test to get a List of all roles in database-----------//

//		getRoleList();

		// --------- deleteRole() Test -----------//

//		deleteRoleTest(id);

		// --------- getRole() Test to get one specific role by id -----------//

//		getRoleTest(id);

		// --------- updateRole() Test -----------//

//		String s = "BOBO role";
//		updateRoleTest(id,s);

		// --------- getRolesByMemberTest() -----------//

//	getRolesByMemberTest(1);

		deleteRoleFromMemberTest(4, 1);

	}

	public static void addTestRole() {
		Role test = new Role("test", // role name
				"ttttt", null); // desc

		RoleDAO.addRole(test);
	}

	public static void updateRoleTest(int id, String s) {
		Session session = DiscordSessionUtil.getSession();

		// Vorhandenen Role anhand id aus DB holen
		Role m = session.get(Role.class, id);

		// Role m ClanName wert neu setzen
		m.setRoleName(s);

		// Role m in Datenbank updaten
		RoleDAO.updateRole(id, m);

		System.out.println(m);
	}

	public static void deleteRoleTest(int id) {
		RoleDAO.deleteRole(id);

		Role role = RoleDAO.getRole(id);
		System.out.println(role);

	}

	public static void getRoleTest(int id) {
		System.out.println(RoleDAO.getRole(id));
	}

	public static void getRolesByMemberTest(int id) {
		Session session = DiscordSessionUtil.getSession();
		List<Role> roles = RoleDAO.getRolesByMemberId(id);
		ArrayList<Role> ol = new ArrayList<Role>();

		for (Role m : roles) {
			ol.add(m);
			System.out.println(m);
		}
	}

	public static void getRoleList() {
		List<Role> roles = RoleDAO.getRoles();
		ArrayList<Role> ol = new ArrayList<Role>();

		for (Role m : roles) {
			ol.add(m);
			System.out.println(m);
		}
	}

	public static void deleteRoleFromMemberTest(int roleid, int memberid) {
		RoleDAO.deleteRoleFromMember(roleid, memberid);
	}

}
