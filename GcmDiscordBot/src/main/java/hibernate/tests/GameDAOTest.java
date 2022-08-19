package hibernate.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.DiscordSessionUtil;
import hibernate.dao.GameDAO;
import hibernate.model.Game;

public class GameDAOTest {

	public static void main(String[] args) {

		Session session = DiscordSessionUtil.getSession();
		int id = 1;
		int id2 = 3;
		int id3 = 4;

		// --------- addGame() Test -----------//

		// addTestGame();
		// addTestGame();
		// addTestGame();
		// addTestGame();

		// --------- getGames() Test to get a List of all games in database-----------//

		// getGameList();

		// --------- deleteGame() Test -----------//

		// deleteGameTest(id);
		// deleteGameTest(id2);
		// deleteGameTest(id3);

		// --------- getGame() Test to get one specific game by id -----------//

		// getGameTest(id);

		// --------- updateGame() Test -----------//

		// String s = "BOBO the Game";
		// updateGameTest(id,s);

//		getMembersByGameIdTest(id3);

//		getGamesByMemberIdTest(id2);

//		deleteGameFromMemberTest(id3);

		deleteGameFromTournamentTest(6, 2);

	}

	public static void addTestGame() {
		Game test = new Game("test", // title
				LocalDate.of(1981, 4, 11), // release date
				null, // members
				null, // tournaments
				"lorem ipsum"); // notes

		GameDAO.addGame(test);
	}

	public static void updateGameTest(int id, String s) {
		Session session = DiscordSessionUtil.getSession();

		// Vorhandenen Game anhand id aus DB holen
		Game m = session.get(Game.class, id);

		// Game m ClanName wert neu setzen
		m.setGameTitle(s);

		// Game m in Datenbank updaten
		GameDAO.updateGame(id, m);

		System.out.println(m);
	}

	public static void deleteGameTest(int id) {
		GameDAO.deleteGame(id);

		Game game = GameDAO.getGame(id);
		System.out.println(game);

	}

	public static void getGameTest(int id) {
		System.out.println(GameDAO.getGame(id));
	}

	public static void getGameList() {
		List<Game> games = GameDAO.getGames();
		ArrayList<Game> ol = new ArrayList<Game>();

		for (Game m : games) {
			ol.add(m);
			System.out.println(m);
		}
	}

	public static void getMembersByGameIdTest(int id) {
		GameDAO.getMembersByGameId(id);
	}

	public static void getGamesByMemberIdTest(int id) {
		GameDAO.getGamesByMemberId(id);
	}

	public static void deleteGameFromMemberTest(int gameid, int memberid) {
		GameDAO.deleteGameFromMember(gameid, memberid);
	}

	public static void deleteGameFromTournamentTest(int gameid, int tournamentid) {
		GameDAO.deleteGameFromTournament(gameid, tournamentid);
	}

}
