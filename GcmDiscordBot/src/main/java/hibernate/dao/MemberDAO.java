package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Member;
import hibernate.model.MemberTeam;
import hibernate.model.Team;


public class MemberDAO {
    // database access methods
    
    // add member
	public static void addMember(Member bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// assign member to team in MemberTeams table
	public static void addMemberToTeam(int memberID, int teamID) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member m = session.get(Member.class, memberID);
		Team t = session.get(Team.class, teamID);

		MemberTeam mt = new MemberTeam();
		mt.setMember(m);
		mt.setTeam(t);

		session.save(mt);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// get member
	public static Member getMember(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member member = session.get(Member.class, id);
		tx.commit();
		 session.clear();
		 session.close();
		return member;
	}

	
	// get member with highest id
	public static Member getMemberWithHighestId() {
		Session session = SessionUtil.getSession();
		// String hql = "select max(id) from Member";
		Integer maxId = (Integer) session.createNativeQuery("select max(id) from Member").getSingleResult();

		Member member = session.get(Member.class, maxId);
		
		 session.clear();
		 session.close();
		return member;
	}

	
	// get list of all members
	public static List<Member> getMembers() {
		Session session = SessionUtil.getSession();
		
		List<Member> list = session.createQuery(
			"select o from Member o",
			Member.class)
			.getResultList();


		for (Member t : list) {		
			System.out.println(t);
		}
		
		
		 session.clear();
		 session.close();
		return list;
	}

	
	// get members by team id from MemberTeams table
	public static List<Member> getMembersByTeamId(int id) {
	    

		Session session = SessionUtil.getSession();
	
		List<Member> teamsMembers = session.createQuery(
			"select member m from MemberTeam tt where team.id= :id",
			Member.class)
			.setParameter("id", id).getResultList();

		for (Member m : teamsMembers) {		
			System.out.println(m);
		}



		
		 session.clear();
		 session.close();
		return teamsMembers;
	}

	// get all members who have birthday today

	public static List<Member> getTodaysMembersBirthdays() {
		Session session = SessionUtil.getSession();
		String hql = "from Member m where day(m.birthday) = day(CURRENT_DATE) and month(m.birthday) = month(CURRENT_DATE)";
		Query query = session.createQuery(hql);
		List<Member> membersBirthday = query.list();

		
		 session.clear();
		 session.close();
		return membersBirthday;

	}

	
	// delete member
	public static void deleteMember(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Member member = session.get(Member.class, id);
		Member member = session.get(Member.class, id);
		session.remove(member);
		// session.delete(session.find(Member.class, id));
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// delete member from all roles in MemberRoles table
	public static void deleteMemberFromRoles(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from MemberRoles mr where member.id= :memberid";
		Query query = session.createQuery(hql);
		query.setParameter("memberid", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");


		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete member from all events in MemberEvents table
	public static void deleteMemberFromEvents(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();


		String hql = "delete from MemberEvents me where member.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");


		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete member from all teams in MemberTeam table
	public static void deleteMemberFromTeams(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();


		String hql = "delete from MemberTeam mt where member.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");


		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete member from specific team in MemberTeam table
	public static void deleteMemberFromTeam(int memberid, int teamid) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from MemberTeam mt where member.id= :memberid and team.id= :teamid";
		Query query = session.createQuery(hql);
		query.setParameter("memberid", memberid);
		query.setParameter("teamid", teamid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete member from all socials in MemberSocials table
	public static void deleteMemberFromSocials(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

	
		String hql = "delete from MemberSocials ms where member.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// Delete member from all games in MemberGames table
	public static void deleteMemberFromGames(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		
		String hql = "delete from MemberGames mg where member.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// update member
	public static void updateMember(int id, Member member) {
		Session session = SessionUtil.getSession();
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
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}
}
