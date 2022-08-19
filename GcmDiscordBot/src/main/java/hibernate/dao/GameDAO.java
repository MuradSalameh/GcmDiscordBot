package hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.DiscordSessionUtil;
import hibernate.model.Game;
import hibernate.model.Member;
import hibernate.model.MemberGames;
import hibernate.model.Tournament;
import hibernate.model.TournamentGame;

public class GameDAO {

	public static void addGame(Game bean) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); // Dafür die add game nicht mehr aufrufen, da direkt im bean gespeichert wird.
		tx.commit();
		session.close();
	}

	public static void addGameToMember(int memberID, int gameID) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member m = session.get(Member.class, memberID);
		Game g = session.get(Game.class, gameID);

		MemberGames mr = new MemberGames();
		mr.setMember(m);
		mr.setGame(g);

		session.save(mr);
		tx.commit();
		session.close();
	}

	public static void addGameToTournament(int gameID, int tournamentID) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Tournament m = session.get(Tournament.class, tournamentID);
		Game g = session.get(Game.class, gameID);

		TournamentGame mr = new TournamentGame();
		mr.setTournament(m);
		mr.setGame(g);

		session.save(mr);
		tx.commit();
		session.close();
	}

	public static Game getGame(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Game game = session.get(Game.class, id);

		return game;
	}

	public static List<Game> getGames() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Game";
		Query query = session.createQuery(hql);
		List<Game> games = query.list();
		session.close();
		return games;
	}

	// ---------------------------------------------
	public static List<Game> getGamesByMemberId(int id) {
		// SQL: SELECT * FROM gcm.member_games where member_id= '3'

		Session session = DiscordSessionUtil.getSession();
		String hql = "from MemberGames game_id where member_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<MemberGames> gamesMember = query.list();
		List<Game> filteredGamesList = new ArrayList<>();

		for (MemberGames m : gamesMember) {
			int sId = m.getGame().getId();
			Game s = session.get(Game.class, sId);
			filteredGamesList.add(s);
			System.out.println(s);
		}

		session.close();
		return filteredGamesList;
	}

	public static List<Game> getGamesByTournamentId(int id) {
		// SQL: SELECT * FROM gcm.tournament_games where tournament_id= '3'

		Session session = DiscordSessionUtil.getSession();
		String hql = "from TournamentGame game_id where tournament_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<TournamentGame> gamesTournament = query.list();
		List<Game> filteredGamesList = new ArrayList<>();

		for (TournamentGame m : gamesTournament) {
			int sId = m.getGame().getId();
			Game s = session.get(Game.class, sId);
			filteredGamesList.add(s);
			System.out.println(s);
		}

		session.close();
		return filteredGamesList;
	}

	public static List<Member> getMembersByGameId(int id) {
		// SQL: SELECT * FROM gcm.member_games where member_id= '3'

		Session session = DiscordSessionUtil.getSession();
		String hql = "from MemberGames member_id where game_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<MemberGames> membersGame = query.list();
		List<Member> filteredGamesList = new ArrayList<>();

		for (MemberGames m : membersGame) {
			int sId = m.getMember().getId();
			Member s = session.get(Member.class, sId);
			filteredGamesList.add(s);
			System.out.println(s);
		}

		session.close();
		return filteredGamesList;
	}
	// ---------------------------------------------

	public static void deleteGameFromMember(int gameid, int memberid) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from MemberGames id where game_id= :gameid and member_id= :memberid";
		Query query = session.createQuery(hql);
		query.setParameter("gameid", gameid);
		query.setParameter("memberid", memberid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Game Table
		// Game game = session.get(Game.class, id);
		// session.remove(game);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteGameFromAllMembers(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from MemberGames id where game_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Game Table
//		Game game = session.get(Game.class, id);
//		session.remove(game);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteGameFromAllTournaments(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from TournamentGame id where game_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Game Table
//		Game game = session.get(Game.class, id);
//		session.remove(game);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteGameFromTournament(int gameid, int tournamentid) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from TournamentGame id where game_id= :gameid and tournament_id= :tournamentid";
		Query query = session.createQuery(hql);
		query.setParameter("gameid", gameid);
		query.setParameter("tournamentid", tournamentid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		// Remove from Game Table
		// Game game = session.get(Game.class, id);
		// session.remove(game);

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteGame(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Game game = session.get(Game.class, id);
		session.remove(game);
		tx.commit();
		session.close();

	}

	public static void updateGame(int id, Game game) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Game old = session.get(Game.class, id);

		old.setGameTitle(game.getGameTitle());
		old.setReleaseDate(game.getReleaseDate());

		// old.setMembers(game.getMembers());
		// old.setTournaments(game.getTournaments());
		old.setGameAdditionalNotes(game.getGameAdditionalNotes());

		session.saveOrUpdate(old);
		session.flush();
		tx.commit();
		session.close();
	}

}
