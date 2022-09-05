package hibernate.tests;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.SessionUtil;
import hibernate.dao.TeamDAO;
import hibernate.model.Team;

public class TeamDAOTest {

	public static void main(String[] args) {

		Session session = SessionUtil.getSession();
		int id = 1;
		int id2 = 5;
		int id3 = 5;

		// --------- addTeam() Test -----------//

//		addTestTeam();
//		addTestTeam();
//		addTestTeam();
//		addTestTeam();

		// --------- getTeams() Test to get a List of all teams in database-----------//

//		getTeamList();

		// --------- deleteTeam() Test -----------//

//		deleteTeamTest(id);

		// --------- getTeam() Test to get one specific team by id -----------//

//		getTeamTest(id);

		// --------- updateTeam() Test -----------//

//		String s = "BOBO Team";
//		updateTeamTest(id,s);

		// --------- getTeamsByMemberIdTest() Test -----------//

//		getTeamsByMemberIdTest(3);

		// --------- getMembersByTeamIdTest() Test -----------//

//		getMembersByTeamIdTest(1);

		// --------- deleteTeamFromTournamentsTest() Test -----------//

//		deleteTeamFromTournamentsTest(1);

		getTeamsByTournamentIdTest(2);

		addTeamToTournamentTest(7, 2);
	}

	public static void addTestTeam() {
		Team test = new Team("test", // team name
				"ttttt", // desc
				null, // members teams
				null); // team tournaments teams

		TeamDAO.addTeam(test);
	}

	public static void updateTeamTest(int id, String s) {
		Session session = SessionUtil.getSession();

		// Vorhandenen Team anhand id aus DB holen
		Team m = session.get(Team.class, id);

		// Team m ClanName wert neu setzen
		m.setTeamName(s);

		// Team m in Datenbank updaten
		TeamDAO.updateTeam(id, m);

		System.out.println(m);
	}

	public static void deleteTeamTest(int id) {
		TeamDAO.deleteTeam(id);

		Team team = TeamDAO.getTeam(id);
		System.out.println(team);

	}

	public static void getTeamTest(int id) {
		System.out.println(TeamDAO.getTeam(id));
	}

	public static void getTeamList() {
		List<Team> teams = TeamDAO.getTeams();
		ArrayList<Team> ol = new ArrayList<Team>();

		for (Team m : teams) {
			ol.add(m);
			System.out.println(m);
		}
	}

	public static void getTeamsByMemberIdTest(int id) {

		TeamDAO.getTeamsByMemberId(id);
	}

	public static void getMembersByTeamIdTest(int id) {

		TeamDAO.getMembersByTeamId(id);
	}

	public static void deleteTeamFromTournamentsTest(int id) {

		TeamDAO.deleteTeamFromTournaments(id);
	}

	public static void getTeamsByTournamentIdTest(int id) {

		TeamDAO.getTeamsByTournamentId(id);
	}

	public static void addTeamToTournamentTest(int teamid, int tournamentd) {

		TeamDAO.addTeamToTournament(teamid, tournamentd);
	}

}
