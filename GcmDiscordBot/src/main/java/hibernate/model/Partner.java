package hibernate.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "partner")
public class Partner implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contact_person_name")
	private String contactPersonName;

	@Column(name = "contact_person_phone")
	private String contactPersonPhone;

	@Column(name = "contact_person_mail")
	private String contactPersonMail;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "address_street")
	private String adressStreet;

	@Column(name = "address_number")
	private String adressNumber;

	@Column(name = "address_postcode")
	private String adressPostCode;

	@Column(name = "address_city")
	private String adressCity;

	@Column(name = "country")
	private String country;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	public Partner() {
		super();
	}

	public Partner(String companyName, String contactPersonName, String contactPersonPhone, String contactPersonMail,
			String firstName, String lastName, String adressStreet, String adressNumber, String adressPostCode,
			String adressCity, String country, String email, String phoneNumber) {
		super();
		this.companyName = companyName;
		this.contactPersonName = contactPersonName;
		this.contactPersonPhone = contactPersonPhone;
		this.contactPersonMail = contactPersonMail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adressStreet = adressStreet;
		this.adressNumber = adressNumber;
		this.adressPostCode = adressPostCode;
		this.adressCity = adressCity;
		this.country = country;
		this.email = email;
		this.phoneNumber = phoneNumber;

	}

	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@XmlElement(name = "ContactPersonName")
	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	@XmlElement(name = "ContactPersonPhone")
	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	@XmlElement(name = "ContactPersonMail")
	public String getContactPersonMail() {
		return contactPersonMail;
	}

	public void setContactPersonMail(String contactPersonMail) {
		this.contactPersonMail = contactPersonMail;
	}

	@XmlElement(name = "FirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name = "LastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "AddressStreet")
	public String getAdressStreet() {
		return adressStreet;
	}

	public void setAdressStreet(String adressStreet) {
		this.adressStreet = adressStreet;
	}

	@XmlElement(name = "AddressNumber")
	public String getAdressNumber() {
		return adressNumber;
	}

	public void setAdressNumber(String adressNumber) {
		this.adressNumber = adressNumber;
	}

	@XmlElement(name = "AddressPostCode")
	public String getAdressPostCode() {
		return adressPostCode;
	}

	public void setAdressPostCode(String adressPostCode) {
		this.adressPostCode = adressPostCode;
	}

	@XmlElement(name = "AddressCity")
	public String getAdressCity() {
		return adressCity;
	}

	public void setAdressCity(String adressCity) {
		this.adressCity = adressCity;
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

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nPartner id=" + id + "\ncompanyName=" + companyName + "\ncontactPersonName=" + contactPersonName
				+ "\ncontactPersonPhone=" + contactPersonPhone + "\ncontactPersonMail=" + contactPersonMail
				+ "\nfirstName=" + firstName + "\nlastName=" + lastName + "\nadressStreet=" + adressStreet
				+ "\nadressNumber=" + adressNumber + "\nadressPostCode=" + adressPostCode + "\nadressCity=" + adressCity
				+ "\ncountry=" + country + "\nemail=" + email + "\nphoneNumber=" + phoneNumber;
	}

}
