package hibernate.tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.SessionUtil;
import hibernate.dao.TournamentDAO;
import hibernate.model.Tournament;

public class TournamentDAOTest {

	public static void main(String[] args) {

		Session session = SessionUtil.getSession();
		int id = 2;
		int id2 = 5;
		int id3 = 5;

		// --------- addTournament() Test -----------//

//		addTestTournament();
//		addTestTournament();
//		addTestTournament();
//		addTestTournament();

		// --------- getTournaments() Test to get a List of all tournaments in
		// database-----------//

//		getTournamentList();

		// --------- deleteTournament() Test -----------//

//		deleteTournamentTest(id);

		// --------- getTournament() Test to get one specific tournament by id
		// -----------//

//		getTournamentTest(id);

		// --------- updateTournament() Test -----------//

		String s = "BOBO TOURNAMENT";
		updateTournamentTest(id, s);

	}

	public static void addTestTournament() {
		Tournament test = new Tournament("TestTournament", // title
				"Testing", // description
				LocalDate.of(1990, 8, 30), // tournament date
				LocalTime.of(22, 58), // start time
				LocalTime.of(2, 30), // end time
				null, // teams list
				null, // games list
				null); // result string

		TournamentDAO.addTournament(test);
	}

	public static void updateTournamentTest(int id, String s) {
		Session session = SessionUtil.getSession();

		// Vorhandenen Tournament anhand id aus DB holen
		Tournament m = session.get(Tournament.class, id);

		// Tournament m ClanName wert neu setzen
		m.setTouramentTitle(s);

		// Tournament m in Datenbank updaten
		TournamentDAO.updateTournament(id, m);

		System.out.println(m);
	}

	public static void deleteTournamentTest(int id) {
		TournamentDAO.deleteTournament(id);

		Tournament tournament = TournamentDAO.getTournament(id);
		System.out.println(tournament);

	}

	public static void getTournamentTest(int id) {
		System.out.println(TournamentDAO.getTournament(id));
	}

	public static void getTournamentList() {
		List<Tournament> tournaments = TournamentDAO.getTournaments();
		ArrayList<Tournament> ol = new ArrayList<Tournament>();

		for (Tournament m : tournaments) {
			ol.add(m);
			System.out.println(m);
		}
	}

}
