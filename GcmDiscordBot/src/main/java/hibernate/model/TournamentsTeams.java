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

@XmlRootElement(name = "TournamentsTeams")

@Entity
@Table(name = "tournaments_teams")
public class TournamentsTeams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id")
	private Team team;

	public TournamentsTeams() {
		super();
	}

	public TournamentsTeams(int id, Tournament tournament, Team team) {
		super();
		this.id = id;
		this.tournament = tournament;
		this.team = team;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "Tournament")
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
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
		return "\nTournamentTeams " + "\ntournamentId=" + tournament.getTouramentTitle() + "\nteamId="
				+ team.getTeamName() + "\n----------------------------------" + "\n";
	}

}
