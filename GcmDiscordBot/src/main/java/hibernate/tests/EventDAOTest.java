package hibernate.tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.DiscordSessionUtil;
import hibernate.dao.EventDAO;
import hibernate.model.Event;

public class EventDAOTest {

	public static void main(String[] args) {

		Session session = DiscordSessionUtil.getSession();
		int id = 3;

		// --------- addEvent() Test -----------//

		// addTestEvent();

		// --------- deleteEvent() Test -----------//

		// deleteEventTest(id);

		// --------- getEvents() Test to get a List of all events in
		// database-----------//

		// getEventList();

		// --------- getEvent() Test to get one specific event by id -----------//

		// getEventTest(id);

		// --------- updateEvent() Test -----------//

//		String string = "PartyTime";
//		updateEventTest(id,string);

//		getMembersByEventIdTest(1);
//		getEventsByMemberIdTest(1); 
		deleteEventFromMemberTest(2);

	}

	public static void addTestEvent() {
		Event test = new Event("EVENT", // event title
				"event descr", // eventdescription
				LocalDate.of(1990, 8, 30), // tournament date
				LocalTime.of(22, 58), // start time
				LocalTime.of(2, 30), // end time
				"lorem ipsum", // additional notes
				false, // reoccuring
				null); // members

		EventDAO.addEvent(test);
	}

	public static void updateEventTest(int id, String s) {
		Session session = DiscordSessionUtil.getSession();

		// Vorhandenen Event anhand id aus DB holen
		Event m = session.get(Event.class, id);

		// Event m ClanName wert neu setzen
		m.setEventTitle(s);

		// Event m in Datenbank updaten
		EventDAO.updateEvent(id, m);

		System.out.println(m);
	}

	public static void deleteEventTest(int id) {
		EventDAO.deleteEvent(id);

		Event event = EventDAO.getEvent(id);
		System.out.println(event);

	}

	public static void getEventTest(int id) {
		System.out.println(EventDAO.getEvent(id));
	}

	public static void getEventList() {
		List<Event> events = EventDAO.getEvents();
		ArrayList<Event> ol = new ArrayList<Event>();

		for (Event e : events) {
			ol.add(e);
			System.out.println(e);
		}
	}

	public static void getMembersByEventIdTest(int id) {
		EventDAO.getMembersByEventId(id);
	}

	public static void getEventsByMemberIdTest(int id) {
		EventDAO.getEventsByMemberId(id);
	}

	public static void deleteEventFromMemberTest(int id) {
		EventDAO.deleteEventFromMember(id);
	}

}
