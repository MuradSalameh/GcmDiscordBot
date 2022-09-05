package hibernate.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.SessionUtil;
import hibernate.dao.MemberDAO;
import hibernate.model.Member;

public class MemberDAOTest {

	public static void main(String[] args) {

		Session session = SessionUtil.getSession();
		int id = 5;
		int id2 = 5;
		int id3 = 5;

		// --------- addMember() Test -----------//

		// addTestMember();
		// addTestMember();
		// addTestMember();
		// addTestMember();

		// --------- getMembers() Test to get a List of all members in
		// database-----------//

//		getMemberList();

//		getMemberWithHighestIdTest();

		// --------- deleteMember() Test -----------//

		// deleteMemberTest(id);

		// --------- getMember() Test to get one specific member by id -----------//

		// getMemberTest(id);

		// --------- updateMember() Test -----------//

		// String s = "BOBO";
		// updateMemberTest(id,s);

		// --------- deleteMemberFromEventsTest() Test -----------//

//		deleteMemberFromEventsTest(1);

		// --------- deleteMemberFromTeamsTest() Test -----------//

//		deleteMemberFromTeamsTest(1);

		// --------- deleteMemberFromGames() Test -----------//

		// deleteMemberFromGamesTest(1);

		// getMembersByTeamIdTest(1);

//		addMemberToTeamTest(1, 1);

		getTodaysMembersBirthdaysTest();

	}

	public static void addTestMember() {
		Member test = new Member("test", // clan name
				"ttttt", // clan id
				"tttttt 1", // real name
				"44", // address
				"1160", // adress post code
				"Vienna", // city
				"Austria", // country
				"ulli@email.com", // mail
				"+43 677 678 643 44", // phone number
				null, // role
				null, // socials
				null, // games
				null, // events
				LocalDate.of(1981, 4, 11), // birthday
				null); // teams

		MemberDAO.addMember(test);
	}

	public static void updateMemberTest(int id, String s) {
		Session session = SessionUtil.getSession();

		// Vorhandenen Member anhand id aus DB holen
		Member m = session.get(Member.class, id);

		// Member m ClanName wert neu setzen
		m.setClanName(s);

		// Member m in Datenbank updaten
		MemberDAO.updateMember(id, m);

		System.out.println(m);
	}

	public static void deleteMemberTest(int id) {
		MemberDAO.deleteMember(id);

		Member member = MemberDAO.getMember(id);
		System.out.println(member);

	}

	public static void getMemberTest(int id) {
		System.out.println(MemberDAO.getMember(id));
	}

	public static void getMemberList() {
		List<Member> members = MemberDAO.getMembers();
		ArrayList<Member> ol = new ArrayList<Member>();

		for (Member m : members) {
			ol.add(m);
			System.out.println(m);
		}
	}

	public static void getMemberWithHighestIdTest() {
		Member m = MemberDAO.getMemberWithHighestId();
		System.out.println(m);
	}

	public static void deleteMemberFromEventsTest(int id) {
		MemberDAO.deleteMemberFromEvents(id);

	}

	public static void deleteMemberFromTeamsTest(int id) {
		MemberDAO.deleteMemberFromTeams(id);

	}

	public static void deleteMemberFromGamesTest(int id) {
		MemberDAO.deleteMemberFromGames(id);

	}

	public static void getMembersByTeamIdTest(int id) {
		MemberDAO.getMembersByTeamId(id);
	}

	public static void addMemberToTeamTest(int memberid, int teamid) {
		MemberDAO.addMemberToTeam(memberid, teamid);
	}

	public static void getTodaysMembersBirthdaysTest() {
		List<Member> members = MemberDAO.getTodaysMembersBirthdays();
		if (members.isEmpty()) {
			System.out.println("No Birthdays Today");
		}

		ArrayList<Member> ol = new ArrayList<Member>();

		for (Member m : members) {
			ol.add(m);
			System.out.println(m);

		}
	}

}
