package hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.DiscordSessionUtil;
import hibernate.model.Member;
import hibernate.model.MemberTeam;
import hibernate.model.Team;

public class MemberDAO {

	public static void addMember(Member bean) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean);
		tx.commit();
		session.close();
	}

	public static void addMemberToTeam(int memberID, int teamID) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member m = session.get(Member.class, memberID);
		Team t = session.get(Team.class, teamID);

		MemberTeam mt = new MemberTeam();
		mt.setMember(m);
		mt.setTeam(t);

		session.save(mt);
		tx.commit();
		session.close();
	}

	public static Member getMember(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member member = session.get(Member.class, id);

		return member;
	}

	public static Member getMemberWithHighestId() {
		Session session = DiscordSessionUtil.getSession();
		// String hql = "select max(id) from Member";
		Integer maxId = (Integer) session.createNativeQuery("select max(id) from Member").getSingleResult();

		Member member = session.get(Member.class, maxId);

		return member;
	}

	public static List<Member> getMembers() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Member";
		Query query = session.createQuery(hql);
		List<Member> members = new ArrayList<Member>(query.list());
		session.close();
		return members;
	}

	public static List<Member> getMembersByTeamId(int id) {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from MemberTeam member_id where team_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<MemberTeam> membersTeam = query.list();

		List<Member> filteredMembersList = new ArrayList<>();

		for (MemberTeam m : membersTeam) {
			int sId = m.getMember().getId();
			Member s = session.get(Member.class, sId);
			filteredMembersList.add(s);
			System.out.println(s);
		}

		session.close();
		return filteredMembersList;
	}

	// -------- Get all members with Birthday ------

	public static List<Member> getTodaysMembersBirthdays() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Member m where day(m.birthday) = day(CURRENT_DATE) and month(m.birthday) = month(CURRENT_DATE)";
		Query query = session.createQuery(hql);
		List<Member> membersBirthday = query.list();

		session.close();
		return membersBirthday;

	}

	public static void deleteMember(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Member member = session.get(Member.class, id);
		Member member = session.get(Member.class, id);
		session.remove(member);
		// session.delete(session.find(Member.class, id));
		tx.commit();
		session.close();
	}

	public static void deleteMemberFromRoles(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberRoles Table
		// ROM a WHERE a.b = :par1 OR a.c = :par2").setParameter("par1",
		// obj).setParameter("par2", obj);
		String hql = "delete from MemberRoles id where member_id= :memberid";
		Query query = session.createQuery(hql);
		query.setParameter("memberid", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Role Table
//		Role role = session.get(Role.class, id);
//		session.remove(role);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteMemberFromEvents(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberMembers Table
		String hql = "delete from MemberEvents id where member_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Member Table
		// Member member = session.get(Member.class, id);
		// session.remove(member);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteMemberFromTeams(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberMembers Table
		String hql = "delete from MemberTeam id where member_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Member Table
		// Member member = session.get(Member.class, id);
		// session.remove(member);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteMemberFromTeam(int memberid, int teamid) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from MemberTeam id where member_id= :memberid and team_id= :teamid";
		Query query = session.createQuery(hql);
		query.setParameter("memberid", memberid);
		query.setParameter("teamid", teamid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Game Table
		// Game game = session.get(Game.class, id);
		// session.remove(game);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteMemberFromSocials(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberMembers Table
		String hql = "delete from MemberSocials id where member_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Member Table
		// Member member = session.get(Member.class, id);
		// session.remove(member);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteMemberFromGames(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberMembers Table
		String hql = "delete from MemberGames id where member_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Member Table
		// Member member = session.get(Member.class, id);
		// session.remove(member);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void updateMember(int id, Member member) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member old = session.get(Member.class, id);

		old.setClanName(member.getClanName());
		old.setClanId(member.getClanId());
		old.setRealName(member.getRealName());
		old.setAddress(member.getAddress());
		old.setAddressPostCode(member.getAddressPostCode());
		old.setAddressCity(member.getAddressCity());
		old.setCountry(member.getCountry());
		old.setEmail(member.getEmail());
		old.setPhoneNumber(member.getPhoneNumber());
		old.setBirthday(member.getBirthday());

		session.saveOrUpdate(old);
		session.flush();
		tx.commit();
		session.close();
	}
}
