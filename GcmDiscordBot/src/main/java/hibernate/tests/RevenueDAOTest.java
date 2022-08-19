package hibernate.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.DiscordSessionUtil;
import hibernate.dao.RevenueDAO;
import hibernate.model.Revenue;

public class RevenueDAOTest {

	public static void main(String[] args) {

		Session session = DiscordSessionUtil.getSession();
		int id = 4;
		int id2 = 5;
		int id3 = 5;

		// --------- addRevenue() Test -----------//

//		addTestRevenue();
//		addTestRevenue();
//		addTestRevenue();
//		addTestRevenue();

		// --------- getRevenues() Test to get a List of all revenues in
		// database-----------//

//		getRevenueList();

		// --------- deleteRevenue() Test -----------//

//		deleteRevenueTest(id);

		// --------- getRevenue() Test to get one specific revenue by id -----------//

//		getRevenueTest(id);

		// --------- updateRevenue() Test -----------//

//		String s = "BOBO Eernings";
//		updateRevenueTest(id,s);

	}

	public static void addTestRevenue() {
		Revenue test = new Revenue("Revenue", // title
				"earnings", // desc
				399999.99, // amount
				LocalDate.of(1981, 4, 11)); // date

		RevenueDAO.addRevenue(test);
	}

	public static void updateRevenueTest(int id, String s) {
		Session session = DiscordSessionUtil.getSession();

		// Vorhandenen Revenue anhand id aus DB holen
		Revenue m = session.get(Revenue.class, id);

		// Revenue m ClanName wert neu setzen
		m.setRevenueTitle(s);

		// Revenue m in Datenbank updaten
		RevenueDAO.updateRevenue(id, m);

		System.out.println(m);
	}

	public static void deleteRevenueTest(int id) {
		RevenueDAO.deleteRevenue(id);

		Revenue revenue = RevenueDAO.getRevenue(id);
		System.out.println(revenue);

	}

	public static void getRevenueTest(int id) {
		System.out.println(RevenueDAO.getRevenue(id));
	}

	public static void getRevenueList() {
		List<Revenue> revenues = RevenueDAO.getRevenues();
		ArrayList<Revenue> ol = new ArrayList<Revenue>();

		for (Revenue m : revenues) {
			ol.add(m);
			System.out.println(m);
		}
	}

}
