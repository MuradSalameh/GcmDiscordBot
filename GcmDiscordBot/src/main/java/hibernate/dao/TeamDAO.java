package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Member;
import hibernate.model.Team;
import hibernate.model.Tournament;
import hibernate.model.TournamentsTeams;

public class TeamDAO {
    
    // database access methods
    

    	// add team to db
	public static void addTeam(Team bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// assign team to tournament in TournamentsTeams table
	public static void addTeamToTournament(int teamID, int tournamentID) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Tournament m = session.get(Tournament.class, tournamentID);
		Team g = session.get(Team.class, teamID);

		TournamentsTeams mr = new TournamentsTeams();
		mr.setTournament(m);
		mr.setTeam(g);

		session.save(mr);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// get team from db
	public static Team getTeam(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Team t = session.get(Team.class, id);
		tx.commit();
		 session.clear();
		 session.close();
		return t;
		
		
	}

	
	// get list of all teams from db
	public static List<Team> getTeams() {
	    
		Session session = SessionUtil.getSession();
		List<Team> list = session.createQuery(
			"select o from Team o",
			Team.class)
			.getResultList();


		for (Team t : list) {		
			System.out.println(t);
		}
		
		
		 session.clear();
		 session.close();
		return list;
	}

	
	// get teams by member id from MemberTeam table
	public static List<Team> getTeamsByMemberId(int id) {
		// SQL: SELECT * FROM gcm.member_teams where member_id= '3'
	    
		Session session = SessionUtil.getSession();
//		String hql = "from MemberTeam team_id where member_id= :id";
//		Query query = session.createQuery(hql);
			
		List<Team> teamsMember = session.createQuery(
			"select team t from MemberTeam mt where member.id= :id",
			Team.class)
			.setParameter("id", id).getResultList();


		for (Team t : teamsMember) {		
			System.out.println(t);
		}
		 session.clear();
		 session.close();		
		return teamsMember;
	}

	
	// get teams by tournament from TournamentsTeams table
	public static List<Team> getTeamsByTournamentId(int id) {

		Session session = SessionUtil.getSession();
		List<Team> teamsTournament = session.createQuery(
			"select team t from TournamentsTeams tt where tournament.id= :id",
			Team.class)
			.setParameter("id", id).getResultList();

		for (Team t : teamsTournament) {		
			System.out.println(t);
		}
		
		 session.clear();
		 session.close();
		return teamsTournament;
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

	
	// delete team from all members in MemberTeams table
	public static void deleteTeamFromMember(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
//		int deletedEntities = session.createQuery(
//			"ddelete team t from MemberTeam mt where team.id= :id")
//		.setParameter("id", id)
//		.executeUpdate();

		// Delete connection from MemberTeams Table
		String hql = "delete from MemberTeam mt where team.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

//		session.remove(team);

		tx.commit();
		session.clear();
		session.close();
	}

	// delete team from all tournaments in TournamentsTeams table
	public static void deleteTeamFromTournaments(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		System.out.println("delete team from all tournaments");
		// Delete connection from MemberTeams Table
		String hql = "delete from TournamentsTeams tt where team.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete team from specific tournament in TournamentsTeams table
	public static void deleteTeamFromTournament(int teamid, int tournamentid) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		System.out.println("delete team from specific tournament");

		String hql = "delete from TournamentsTeams tt where team.id= :teamid and tournament.id= :tournamentid";
		Query query = session.createQuery(hql);
		query.setParameter("teamid", teamid);
		query.setParameter("tournamentid", tournamentid);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete team
	public static void deleteTeam(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Team team = session.get(Team.class, id);
		session.remove(team);
		
		tx.commit();
		 session.clear();
		 session.close();

	}

	// update team
	public static void updateTeam(int id, Team team) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Team old = session.get(Team.class, id);

		old.setTeamName(team.getTeamName());
		old.setTeamDescription(team.getTeamDescription());
		// old.setMembers(team.getMembers());

		session.saveOrUpdate(old);
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}

}
