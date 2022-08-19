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
@Table(name = "social")
public class Social implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "social_platform")
	private String socialPlatform;

	@Column(name = "social_username")
	private String socialUsername;

	@Column(name = "social_link")
	private String socialLink;

	@Column(name = "social_notes")
	private String socialNotes;

	@OneToMany(mappedBy = "social")
	private Set<MemberSocials> membersocials = new HashSet<>();

	public Social() {
		super();
	}

	public Social(String socialPlatform, String socialUsername, String socialLink, String socialNotes,
			Set<MemberSocials> membersocials) {
		super();
		this.socialPlatform = socialPlatform;
		this.socialUsername = socialUsername;
		this.socialLink = socialLink;
		this.socialNotes = socialNotes;
		this.membersocials = membersocials;
	}

	@XmlElement(name = "SocialPlatform")
	public String getSocialPlatform() {
		return socialPlatform;
	}

	public void setSocialPlatform(String socialPlatform) {
		this.socialPlatform = socialPlatform;
	}

	@XmlElement(name = "SocialUsername")
	public String getSocialUsername() {
		return socialUsername;
	}

	public void setSocialUsername(String socialUsername) {
		this.socialUsername = socialUsername;
	}

	@XmlElement(name = "SocialLink")
	public String getSocialLink() {
		return socialLink;
	}

	public void setSocialLink(String socialLink) {
		this.socialLink = socialLink;
	}

	@XmlElement(name = "SocialNotes")
	public String getSocialNotes() {
		return socialNotes;
	}

	public void setSocialNotes(String socialNotes) {
		this.socialNotes = socialNotes;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@XmlTransient
	public Set<MemberSocials> getMembersocials() {
		return membersocials;
	}

	public void setMembersocials(Set<MemberSocials> membersocials) {
		this.membersocials = membersocials;
	}

	@Override
	public String toString() {
		return "\nSocial [id=" + id + "\nsocialPlatform=" + socialPlatform + "\nsocialUsername=" + socialUsername
				+ "\nsocialLink=" + socialLink + "\nsocialNotes=" + socialNotes + "\n----------------------------------"
				+ "\n";

	}

}
