package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Event;
import hibernate.model.Member;


public class EventDAO {
    
    // database access methods


    // add new event
	public static void addEvent(Event bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.persist(bean); 
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// get event
	public static Event getEvent(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Event event = session.get(Event.class, id);
		
		tx.commit();
		 session.clear();
		 session.close();
		return event;
		
	}

	
	// get events by member id from MemberEvents table
	public static List<Event> getEventsByMemberId(int id) {

		Session session = SessionUtil.getSession();
		List<Event> eventsMember = session.createQuery(
			"select event e from MemberEvents me where member.id= :id",
			Event.class)
			.setParameter("id", id).getResultList();


		for (Event e : eventsMember) {		
			System.out.println(e);
		}
		
		
		 session.clear();
		 session.close();
		return eventsMember;
	}

	
	// get members by event id from MemberEvents table
	public static List<Member> getMembersByEventId(int id) {
	    Session session = SessionUtil.getSession();
	    
		List<Member> eventsMember = session.createQuery(
			"select member e from MemberEvents me where event.id= :id",
			Member.class)
			.setParameter("id", id).getResultList();


		for (Member e : eventsMember) {		
			System.out.println(e);
		}

		
		 session.clear();
		 session.close();
		return eventsMember;
	}

	
	// get list of all events
	public static List<Event> getEvents() {
		Session session = SessionUtil.getSession();
		List<Event> list = session.createQuery(
			"select o from Event o",
			Event.class)
			.getResultList();


		for (Event t : list) {		
			System.out.println(t);
		}
		
		 session.clear();
		 session.close();
		return list;
	}

	
	// delete event from all members in MemberEvents table
	public static void deleteEventFromMember(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "delete from MemberEvents me where event.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		int count = query.executeUpdate();
		System.out.println(count + " Record(s) Deleted.");

		tx.commit();
		session.clear();
		session.close();
	}

	
	// delete event
	public static void deleteEvent(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Event event = session.get(Event.class, id);
		session.remove(event);
		tx.commit();
		 session.clear();
		 session.close();

	}

	
	// update event
	public static void updateEvent(int id, Event event) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Event old = session.get(Event.class, id);

		old.setEventTitle(event.getEventTitle());
		old.setEventDescription(event.getEventDescription());
		old.setDate(event.getDate());
		old.setEventStartTime(event.getEventStartTime());
		old.setEventEndTime(event.getEventEndTime());
		old.setEventAddidtionalNotes(event.getEventAddidtionalNotes());
		old.setReoccuring(event.isReoccuring());

		session.saveOrUpdate(old);
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}

}
