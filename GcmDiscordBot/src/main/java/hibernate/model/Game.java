package hibernate.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "game")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "game_title")
	private String gameTitle;

	@Column(name = "release_date")
	private LocalDate releaseDate;

	@OneToMany(mappedBy = "game")
	Set<MemberGames> memberGames = new HashSet<>();

	@OneToMany(mappedBy = "game")
	private Set<TournamentGame> tournamentGame = new HashSet<>();

	@Column(name = "game_additional_notes")
	private String gameAdditionalNotes;

	public Game() {
		super();
	}

	public Game(String gameTitle, LocalDate releaseDate, Set<MemberGames> memberGames,
			Set<TournamentGame> tournamentGame, String gameAdditionalNotes) {
		super();
		this.gameTitle = gameTitle;
		this.releaseDate = releaseDate;
		this.memberGames = memberGames;
		this.tournamentGame = tournamentGame;
		this.gameAdditionalNotes = gameAdditionalNotes;
	}

	@XmlElement(name = "GameTitle")
	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(name = "ReleaseDate")
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@XmlTransient
	public Set<MemberGames> getMemberGames() {
		return memberGames;
	}

	public void setMemberGames(Set<MemberGames> memberGames) {
		this.memberGames = memberGames;
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

	@XmlTransient
	public Set<MemberGames> getMembers() {
		return memberGames;
	}

	public void setMembers(Set<MemberGames> memberGames) {
		this.memberGames = memberGames;
	}

	@XmlElement(name = "GameAdditionalNotes")
	public String getGameAdditionalNotes() {
		return gameAdditionalNotes;
	}

	public void setGameAdditionalNotes(String gameAdditionalNotes) {
		this.gameAdditionalNotes = gameAdditionalNotes;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nGame id=" + id + "\ngameTitle=" + gameTitle + "\nreleaseDate=" + releaseDate
				+ "\ngameAdditionalNotes=" + gameAdditionalNotes + "\n----------------------------------" + "\n";
	}
}