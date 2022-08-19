package hibernate.tests;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.DiscordSessionUtil;
import hibernate.dao.PartnerDAO;
import hibernate.model.Partner;

public class PartnerDAOTest {

	public static void main(String[] args) {

		Session session = DiscordSessionUtil.getSession();
		int id = 2;

		// --------- addPartner() Test -----------//

		addTestPartner();
		addTestPartner();
		addTestPartner();
		addTestPartner();
		addTestPartner();

		// --------- deletePartner() Test -----------//

		// deletePartnerTest(id);

		// --------- getPartner() Test to get one specific partner by id -----------//

		// getPartnerTest(id);

		// --------- getPartners() Test to get a List of all partners in
		// database-----------//

//		getPartnerList();

		// --------- updatePartner() Test -----------//

		// String newClanNameValue = "BOBO";
		// updatePartnerTest(id,newClanNameValue);

	}

	public static void addTestPartner() {
		Partner test = new Partner("COMPANY", // company name
				"ttttt", // contact person name
				"tttttt 1", // contact person phone
				"contact@mail.com", // contact person mail
				"dfvdfv", // first name
				"rororor", // last name
				"StreetCompany", // adress street
				"99", // addess number
				"2345", // post code
				"Dublin", // city
				"Ireland", // country
				"company@company.com", // email
				"+43 677 678 643 44" // phone
		); // reveues

		PartnerDAO.addPartner(test);
	}

	public static void updatePartnerTest(int id, String s) {
		Session session = DiscordSessionUtil.getSession();

		// Vorhandenen Partner anhand id aus DB holen
		Partner m = session.get(Partner.class, id);

		// Partner m ClanName wert neu setzen
		m.setCompanyName(s);

		// Partner m in Datenbank updaten
		PartnerDAO.updatePartner(id, m);

		System.out.println(m);
	}

	public static void deletePartnerTest(int id) {
		PartnerDAO.deletePartner(id);

		Partner partner = PartnerDAO.getPartner(id);
		System.out.println(partner);

	}

	public static void getPartnerTest(int id) {
		System.out.println(PartnerDAO.getPartner(id));
	}

	public static void getPartnerList() {
		List<Partner> partners = PartnerDAO.getPartners();
		ArrayList<Partner> ol = new ArrayList<Partner>();

		for (Partner m : partners) {
			ol.add(m);
			System.out.println(m);
		}
	}

}
