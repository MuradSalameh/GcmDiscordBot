package hibernate.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity
@Table(name = "tournament")
public class Tournament implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "tournament_title")
	private String touramentTitle;

	@Column(name = "tournament_description")
	private String tournamentDescription;

	@Column(name = "tournament_date")
	private LocalDate tournamentDate;

	@Column(name = "tournament_time_beginn")
	private LocalTime tournamentTimeBeginn;

	@Column(name = "tournament_time_end")
	private LocalTime tournamentTimeEnd;

	@OneToMany(mappedBy = "tournament")
	private Set<TournamentsTeams> tournamentsTeams = new HashSet<>();

	@OneToMany(mappedBy = "tournament")
	private Set<TournamentGame> tournamentGame = new HashSet<>();

	@Column(name = "tournament_result")
	private String tournamentResult;

	/*
	 * @PreRemove public void checkAssociationBeforeRemoval() {
	 * if(!this.teams.isEmpty()) { throw new
	 * RuntimeException("Can't remove Tournament that has teams"); } }
	 */

	public Tournament() {
		super();
	}

	public Tournament(String touramentTitle, String tournamentDescription, LocalDate tournamentDate,
			LocalTime tournamentTimeBeginn, LocalTime tournamentTimeEnd, Set<TournamentsTeams> tournamentsTeams,
			Set<TournamentGame> tournamentGame, String tournamentResult) {
		super();
		this.touramentTitle = touramentTitle;
		this.tournamentDescription = tournamentDescription;
		this.tournamentDate = tournamentDate;
		this.tournamentTimeBeginn = tournamentTimeBeginn;
		this.tournamentTimeEnd = tournamentTimeEnd;
		this.tournamentsTeams = tournamentsTeams;
		this.tournamentGame = tournamentGame;
		this.tournamentResult = tournamentResult;
	}

	@XmlElement(name = "TournamentTitle")
	public String getTouramentTitle() {
		return touramentTitle;
	}

	public void setTouramentTitle(String touramentTitle) {
		this.touramentTitle = touramentTitle;
	}

	@XmlElement(name = "TournamentDescription")
	public String getTournamentDescription() {
		return tournamentDescription;
	}

	public void setTournamentDescription(String tournamentDescription) {
		this.tournamentDescription = tournamentDescription;
	}

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(name = "TournamentDate")
	public LocalDate getTournamentDate() {
		return tournamentDate;
	}

	public void setTournamentDate(LocalDate tournamentDate) {
		this.tournamentDate = tournamentDate;
	}

	@XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
	@XmlElement(name = "TournamentTimeBeginn")
	public LocalTime getTournamentTimeBeginn() {
		return tournamentTimeBeginn;
	}

	public void setTournamentTimeBeginn(LocalTime tournamentTimeBeginn) {
		this.tournamentTimeBeginn = tournamentTimeBeginn;
	}

	@XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
	@XmlElement(name = "TournamentTimeEnd")
	public LocalTime getTournamentTimeEnd() {
		return tournamentTimeEnd;
	}

	public void setTournamentTimeEnd(LocalTime tournamentTimeEnd) {
		this.tournamentTimeEnd = tournamentTimeEnd;
	}

	@XmlTransient
	public Set<TournamentsTeams> getTournamentsTeams() {
		return tournamentsTeams;
	}

	public void setTournamentsTeams(Set<TournamentsTeams> tournamentsTeams) {
		this.tournamentsTeams = tournamentsTeams;
	}

	@XmlTransient
	public Set<TournamentGame> getTournamentGame() {
		return tournamentGame;
	}

	public void setTournamentGame(Set<TournamentGame> tournamentGame) {
		this.tournamentGame = tournamentGame;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@XmlElement(name = "TournamentResult")
	public String getTournamentResult() {
		return tournamentResult;
	}

	public void setTournamentResult(String tournamentResult) {
		this.tournamentResult = tournamentResult;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nTournament [id=" + id + "\ntouramentTitle=" + touramentTitle + "\ntournamentDescription="
				+ tournamentDescription + "\ntournamentDate=" + tournamentDate + "\ntournamentTimeBeginn="
				+ tournamentTimeBeginn + "\ntournamentTimeEnd=" + tournamentTimeEnd + "\ntournamentResult="
				+ tournamentResult + "\n----------------------------------" + "\n";
	}

}