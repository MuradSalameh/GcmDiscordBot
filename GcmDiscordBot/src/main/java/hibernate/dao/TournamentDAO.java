package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Tournament;

public class TournamentDAO {
    
    // database access methods

    	// add tournament to DB
	public static void addTournament(Tournament bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); 
		tx.commit();
		 session.clear();
		 session.close();
	}
	
	// Get tournament from DB
	public static Tournament getTournament(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Tournament t = session.get(Tournament.class, id);
		tx.commit();
		 session.clear();
		 session.close();
		return t;
	}

	// get list of all tournaments from DB
	public static List<Tournament> getTournaments() {
		Session session = SessionUtil.getSession();
		
		List<Tournament> tlist = session.createQuery(
			"select t from Tournament t",
			Tournament.class)
			.getResultList();


		for (Tournament t : tlist) {		
			System.out.println(t);
		}
		
		 session.clear();
		 session.close();
		return tlist;
	}

	// delete tournament from DB
	public static void deleteTournament(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Tournament tournament = session.find(Tournament.class, id);
		session.remove(tournament);
		tx.commit();
		 session.clear();
		 session.close();

	}

	
	// delete tournament from TournamentTeams table
	public static void deleteTournamentsFromTeams(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberTeams Table
		String hql = "delete from TournamentsTeams tt where tournament.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete tournament from Games in TournamentGame table
	public static void deleteTournamentFromGame(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from TournamentGame tg where tournament.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// update turnament
	public static void updateTournament(int id, Tournament tournament) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Tournament old = session.find(Tournament.class, id);

		old.setTouramentTitle(tournament.getTouramentTitle());
		old.setTournamentDescription(tournament.getTournamentDescription());
		old.setTournamentDate(tournament.getTournamentDate());
		old.setTournamentTimeBeginn(tournament.getTournamentTimeBeginn());
		old.setTournamentTimeEnd(tournament.getTournamentTimeEnd());
		old.setTournamentResult(tournament.getTournamentResult());

		session.saveOrUpdate(old);
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}

}
