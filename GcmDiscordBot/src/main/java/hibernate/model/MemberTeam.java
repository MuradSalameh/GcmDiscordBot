package hibernate.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MemberTeam")

@Entity
@Table(name = "member_team")
public class MemberTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id")
	private Team team;

	public MemberTeam() {
		super();
	}

	public MemberTeam(int id, Member member, Team team) {
		super();
		this.id = id;
		this.member = member;
		this.team = team;
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

	@XmlElement(name = "Team")
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team teamId) {
		this.team = teamId;
	}

	@Override
	public String toString() {
		return "\nMemberTeams " + "\nmemberId=" + member.getClanName() + "\nteamId=" + team.getTeamName()
				+ "\n----------------------------------" + "\n";
	}

}
