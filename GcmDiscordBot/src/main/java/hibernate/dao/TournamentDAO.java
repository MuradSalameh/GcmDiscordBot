package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.DiscordSessionUtil;
import hibernate.model.Tournament;

public class TournamentDAO {

	public static void addTournament(Tournament bean) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); // Dafür die add tournament nicht mehr aufrufen, da direkt im bean gespeichert
								// wird.
		tx.commit();
		session.close();
	}

	public static Tournament getTournament(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Tournament t = session.get(Tournament.class, id);

		return t;
	}

	public static List<Tournament> getTournaments() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Tournament";
		Query query = session.createQuery(hql);
		List<Tournament> tournaments = query.list();
		session.close();
		return tournaments;
	}

	public static void deleteTournament(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Tournament tournament = session.find(Tournament.class, id);
		session.remove(tournament);
		tx.commit();
		session.close();

	}

	public static void deleteTournamentsFromTeams(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		// Delete connection from MemberTeams Table
		String hql = "delete from TournamentsTeams id where tournament_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	public static void deleteTournamentFromGame(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "delete from TournamentGame id where tournament_id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	public static void updateTournament(int id, Tournament tournament) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Tournament old = session.find(Tournament.class, id);

		old.setTouramentTitle(tournament.getTouramentTitle());
		old.setTournamentDescription(tournament.getTournamentDescription());
		old.setTournamentDate(tournament.getTournamentDate());
		old.setTournamentTimeBeginn(tournament.getTournamentTimeBeginn());
		old.setTournamentTimeEnd(tournament.getTournamentTimeEnd());
		// old.setTeams(tournament.getTeams());
		// old.setGame(tournament.getGame());
		old.setTournamentResult(tournament.getTournamentResult());

		session.saveOrUpdate(old);
		session.flush();
		tx.commit();
		session.close();
	}

}
