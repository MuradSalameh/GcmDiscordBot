package hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MemberEvents")

@Entity
@Table(name = "member_events")
public class MemberEvents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_id")
	private Event event;

	public MemberEvents() {
		super();
	}

	public MemberEvents(int id, Member member, Event event) {
		super();
		this.id = id;
		this.member = member;
		this.event = event;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "Member")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@XmlElement(name = "Event")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event eventId) {
		this.event = eventId;
	}

	@Override
	public String toString() {
		return "\nMemberEvents " + "\nmemberId=" + member.getClanName() + "\neventId=" + event.getEventTitle()
				+ "\n----------------------------------" + "\n";
	}

}
