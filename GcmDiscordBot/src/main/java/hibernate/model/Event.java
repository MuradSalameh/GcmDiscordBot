package hibernate.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity
@Table(name = "event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "event_title")
	private String eventTitle;

	@Column(name = "event_description")
	private String eventDescription;

	@Column(name = "event_date")
	private LocalDate date;

	@Column(name = "event_start_time")
	private LocalTime eventStartTime;

	@Column(name = "event_end_time")
	private LocalTime eventEndTime;

	@Column(name = "event_additional_notes")
	private String eventAddidtionalNotes;

	@Column(name = "reoccuring")
	private boolean reoccuring;

	// join table for members

	// Members
	@OneToMany(mappedBy = "event")
	Set<MemberEvents> memberEvents = new HashSet<>();

	public Event() {
		super();
	}

	public Event(String eventTitle, String eventDescription, LocalDate date, LocalTime eventStartTime,
			LocalTime eventEndTime, String eventAddidtionalNotes, boolean reoccuring, Set<MemberEvents> memberEvents) {
		super();
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
		this.date = date;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.eventAddidtionalNotes = eventAddidtionalNotes;
		this.reoccuring = reoccuring;
		this.memberEvents = memberEvents;
	}

	@XmlElement(name = "EventTitle")
	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	@XmlElement(name = "EventDescription")
	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(name = "Date")
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
	@XmlElement(name = "EventStartTime")
	public LocalTime getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(LocalTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	@XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
	@XmlElement(name = "EventEndTime")
	public LocalTime getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(LocalTime eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	@XmlElement(name = "EventAdditionalNotes")
	public String getEventAddidtionalNotes() {
		return eventAddidtionalNotes;
	}

	public void setEventAddidtionalNotes(String eventAddidtionalNotes) {
		this.eventAddidtionalNotes = eventAddidtionalNotes;
	}

	@XmlElement(name = "Reoccuring")
	public boolean isReoccuring() {
		return reoccuring;
	}

	public void setReoccuring(boolean reoccuring) {
		this.reoccuring = reoccuring;
	}

	@XmlTransient
	public Set<MemberEvents> getMembers() {
		return memberEvents;
	}

	public void setMembers(Set<MemberEvents> memberEvents) {
		this.memberEvents = memberEvents;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nEvent id=" + id + "\neventTitle=" + eventTitle + "\neventDescription=" + eventDescription + "\ndate="
				+ date + "\neventStartTime=" + eventStartTime + "\neventEndTime=" + eventEndTime
				+ "\neventAddidtionalNotes=" + eventAddidtionalNotes + "\nreoccuring=" + reoccuring
				+ "\n----------------------------------" + "\n";
	}

}
