package hibernate.model;

import java.io.Serializable;
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

@XmlRootElement
@Entity
@Table(name = "team")
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "team_description")
	private String teamDescription;

	@OneToMany(mappedBy = "team")
	private Set<MemberTeam> memberTeam = new HashSet<>();

	@OneToMany(mappedBy = "team")
	private Set<TournamentsTeams> tournamentsTeams = new HashSet<>();

	public Team() {
		super();
	}

	public Team(String teamName, String teamDescription, Set<MemberTeam> memberTeam,
			Set<TournamentsTeams> tournamentsTeams) {
		super();
		this.teamName = teamName;
		this.teamDescription = teamDescription;
		this.memberTeam = memberTeam;
		this.tournamentsTeams = tournamentsTeams;
	}

	@XmlElement(name = "Teamname")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@XmlElement(name = "TeamDescription")
	public String getTeamDescription() {
		return teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	@XmlTransient
	public Set<MemberTeam> getMemberTeam() {
		return memberTeam;
	}

	public void setMemberTeam(Set<MemberTeam> memberTeam) {
		this.memberTeam = memberTeam;
	}

	@XmlTransient
	public Set<TournamentsTeams> getTournamentsTeams() {
		return tournamentsTeams;
	}

	public void setTournamentsTeams(Set<TournamentsTeams> tournamentsTeams) {
		this.tournamentsTeams = tournamentsTeams;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nTeam [id=" + id + "\nteamName=" + teamName + "\nteamDescription=" + teamDescription
				+ "\n----------------------------------" + "\n";
	}

}
