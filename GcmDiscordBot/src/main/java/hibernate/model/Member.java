package hibernate.model;

import java.io.Serializable;
import java.time.LocalDate;
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

@XmlRootElement(name = "Member")

@Entity
@Table(name = "member")
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "clan_name")
	private String clanName;

	@Column(name = "clan_id")
	private String clanId;

	@Column(name = "realName")
	private String realName;

	@Column(name = "address")
	private String address;

	@Column(name = "address_postcode")
	private String addressPostCode;

	@Column(name = "address_city")
	private String addressCity;

	@Column(name = "country")
	private String country;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	// join table for member roles
	@OneToMany(mappedBy = "member")
	Set<MemberRoles> memberRoles = new HashSet<>();

	// join table for member socials
	@OneToMany(mappedBy = "member")
	Set<MemberSocials> memberSocials = new HashSet<>();

	// join table for games --- Custom ManyToMany
	@OneToMany(mappedBy = "member")
	Set<MemberGames> memberGames = new HashSet<>();

	// join table members events --- Custom ManyToMany
	@OneToMany(mappedBy = "member")
	Set<MemberEvents> memberEvents = new HashSet<>();

	@Column(name = "birthday")
	private LocalDate birthday;

	// TEAMS
	@OneToMany(mappedBy = "member")
	private Set<MemberTeam> memberTeam = new HashSet<>();

	public Member() {
		super();
	}

	public Member(String clanName, String clanId, String realName, String address, String addressPostCode,
			String addressCity, String country, String email, String phoneNumber, Set<MemberRoles> memberRoles,
			Set<MemberSocials> memberSocials, Set<MemberGames> memberGames, Set<MemberEvents> memberEvents,
			LocalDate birthday, Set<MemberTeam> memberTeam) {
		super();
		this.clanName = clanName;
		this.clanId = clanId;
		this.realName = realName;
		this.address = address;
		this.addressPostCode = addressPostCode;
		this.addressCity = addressCity;
		this.country = country;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.memberRoles = memberRoles;
		this.memberSocials = memberSocials;
		this.memberGames = memberGames;
		this.memberEvents = memberEvents;
		this.birthday = birthday;
		this.memberTeam = memberTeam;
	}

	@XmlElement(name = "ClanName")
	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	@XmlElement(name = "ClanID")
	public String getClanId() {
		return clanId;
	}

	public void setClanId(String clanId) {
		this.clanId = clanId;
	}

	@XmlElement(name = "RealName")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@XmlElement(name = "Address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement(name = "AddressPostCode")
	public String getAddressPostCode() {
		return addressPostCode;
	}

	public void setAddressPostCode(String addressPostCode) {
		this.addressPostCode = addressPostCode;
	}

	@XmlElement(name = "AddressCity")
	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name = "Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name = "PhoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(name = "Birthday")
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@XmlTransient
	public Set<MemberRoles> getMemberRoles() {
		return memberRoles;
	}

	public void setMemberRoles(Set<MemberRoles> memberRoles) {
		this.memberRoles = memberRoles;
	}

	@XmlTransient
	public Set<MemberSocials> getMemberSocials() {
		return memberSocials;
	}

	public void setMemberSocials(Set<MemberSocials> memberSocials) {
		this.memberSocials = memberSocials;
	}

	@XmlTransient
	public Set<MemberGames> getMemberGames() {
		return memberGames;
	}

	public void setMemberGames(Set<MemberGames> memberGames) {
		this.memberGames = memberGames;
	}

	@XmlTransient
	public Set<MemberEvents> getMemberEvents() {
		return memberEvents;
	}

	public void setMemberEvents(Set<MemberEvents> memberEvents) {
		this.memberEvents = memberEvents;
	}

	@XmlTransient
	public Set<MemberTeam> getMemberTeam() {
		return memberTeam;
	}

	public void setMemberTeam(Set<MemberTeam> memberTeam) {
		this.memberTeam = memberTeam;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nMember id = " + id + "\nclanName = " + clanName + "\nclanId = " + clanId + "\nrealName = " + realName
				+ "\naddress=" + address + "\naddressPostCode=" + addressPostCode + "\naddressCity=" + addressCity
				+ "\ncountry=" + country + "\nemail=" + email + "\nphoneNumber=" + phoneNumber + "\nbirthday="
				+ birthday + "\n----------------------------------" + "\n";
	}

}