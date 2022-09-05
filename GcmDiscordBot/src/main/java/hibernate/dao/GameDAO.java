package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Game;
import hibernate.model.Member;
import hibernate.model.MemberGames;
import hibernate.model.Tournament;
import hibernate.model.TournamentGame;



public class GameDAO {
    // database access methods
    
    	// add new game
	public static void addGame(Game bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); 
		
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// assign game to member in MemberGames table
	public static void addGameToMember(int memberID, int gameID) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Member m = session.get(Member.class, memberID);
		Game g = session.get(Game.class, gameID);

		MemberGames mr = new MemberGames();
		mr.setMember(m);
		mr.setGame(g);

		session.save(mr);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// assign game to tournament in TournamentGame table
	public static void addGameToTournament(int gameID, int tournamentID) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Tournament m = session.get(Tournament.class, tournamentID);
		Game g = session.get(Game.class, gameID);

		TournamentGame mr = new TournamentGame();
		mr.setTournament(m);
		mr.setGame(g);

		session.save(mr);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// get game
	public static Game getGame(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Game game = session.get(Game.class, id);

		tx.commit();
		 session.clear();
		 session.close();
		return game;
		
		
		 
	}

	
	//get list of all games
	public static List<Game> getGames() {
		Session session = SessionUtil.getSession();
		List<Game> list = session.createQuery(
			"select o from Game o",
			Game.class)
			.getResultList();


		for (Game t : list) {		
			System.out.println(t);
		}
		
		 session.clear();
		 session.close();
		return list;
	}

	
	// get games by member id from MemberGames table
	public static List<Game> getGamesByMemberId(int id) {

		Session session = SessionUtil.getSession();
		List<Game> list = session.createQuery(
			"select game t from MemberGames mt where member.id= :id",
			Game.class)
			.setParameter("id", id).getResultList();

		
		for (Game o : list) {		
			System.out.println(o);
		}
		
		
		 session.clear();
		session.close();
		return list;
	}
	
	// get games by tournament id from TournamentGame table
	public static List<Game> getGamesByTournamentId(int id) {

		Session session = SessionUtil.getSession();
		List<Game> list = session.createQuery(
			"select game g from TournamentGame mg where tournament.id= :id",
			Game.class)
			.setParameter("id", id).getResultList();

		
		for (Game o : list) {		
			System.out.println(o);
		}
		
		 session.clear();
		session.close();
		return list;
	}
	
	// get Members by game id from MemberGames table
	public static List<Member> getMembersByGameId(int id) {

		Session session = SessionUtil.getSession();
		List<Member> list = session.createQuery(
			"select member t from MemberGames mt where game.id= :id",
			Member.class)
			.setParameter("id", id).getResultList();


		for (Member o : list) {		
			System.out.println(o);
		}
		
		 session.clear();
		session.close();
		return list;
	}
	

	// delete game from secific member in MemberGames table
	public static void deleteGameFromMember(int gameid, int memberid) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		int deletedEntities = session.createQuery(
			"delete from MemberGames mg where game.id= :gameid and member.id= :memberid")
		.setParameter("gameid", gameid).setParameter("memberid", memberid).executeUpdate();
		
//		String hql = "delete game g from MemberGames mg where game.id= :gameid and member.id= :memberid";
//		Query query = session.createQuery(hql);
//		query.setParameter("gameid", gameid).setParameter("memberid", memberid);
//
//		int count = query.executeUpdate();
		System.out.println(deletedEntities + " Record(s) Deleted.");

		// Remove from Game Table
		// Game game = session.get(Game.class, id);
		// session.remove(game);

		tx.commit();
		
		 session.clear();
		session.close();
	}

	
	// delete game from all members in MemberGame table
	public static void deleteGameFromAllMembers(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from MemberGames mg where game.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");



		tx.commit();
		session.clear();
		session.close();
	}

	// delete game from all tournaments in TournamentGame table
	public static void deleteGameFromAllTournaments(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from TournamentGame tg where game.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");


		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete game from specific tournament in TournamentGame table
	public static void deleteGameFromTournament(int gameid, int tournamentid) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from TournamentGame tg where game.id= :gameid and tournament.id= :tournamentid";
		Query query = session.createQuery(hql);
		query.setParameter("gameid", gameid);
		query.setParameter("tournamentid", tournamentid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");


		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete game
	public static void deleteGame(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Game game = session.get(Game.class, id);
		session.remove(game);
		tx.commit();
		 session.clear();
		 session.close();

	}

	
	// update game
	public static void updateGame(int id, Game game) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Game old = session.get(Game.class, id);

		old.setGameTitle(game.getGameTitle());
		old.setReleaseDate(game.getReleaseDate());

		old.setGameAdditionalNotes(game.getGameAdditionalNotes());

		session.saveOrUpdate(old);
	//	session.flush();
		tx.commit();
		 session.clear();
		session.close();
	}

}
