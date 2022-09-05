package hibernate.tests;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.SessionUtil;
import hibernate.dao.SocialDAO;
import hibernate.model.Social;

public class SocialDAOTest {

	public static void main(String[] args) {

		Session session = SessionUtil.getSession();
		int id = 1;
		int id2 = 5;
		int id3 = 5;

		// --------- addSocial() Test -----------//

//		addTestSocial();
//		addTestSocial();
//		addTestSocial();
//		addTestSocial();

		// --------- getSocials() Test to get a List of all socials in
		// database-----------//

//		getSocialList();

		// --------- deleteSocial() Test -----------//

		deleteSocialTest(id);

		// --------- getSocial() Test to get one specific social by id -----------//

//		getSocialTest(id);

		// --------- updateSocial() Test -----------//

//		String s = "BOBO NewPlatform";
//		updateSocialTest(id,s);

		// --------- getSocialById() -----------//

		// getSocialsByMemberIdTest(1);
//		deleteSocialFromMemberTest(1);

	}

	public static void addTestSocial() {
		Social test = new Social("test", // platform
				"ttttt", // username
				"tttttt 1", // link
				"44", // notes
				null); // members

		SocialDAO.addSocial(test);
	}

	public static void updateSocialTest(int id, String s) {
		Session session = SessionUtil.getSession();

		// Vorhandenen Social anhand id aus DB holen
		Social m = session.get(Social.class, id);

		// Social m ClanName wert neu setzen
		m.setSocialPlatform(s);

		// Social m in Datenbank updaten
		SocialDAO.updateSocial(id, m);

		System.out.println(m);
	}

	public static void deleteSocialTest(int id) {
		SocialDAO.deleteSocial(id);

		Social social = SocialDAO.getSocial(id);
		System.out.println(social);

	}

	public static void getSocialTest(int id) {
		System.out.println(SocialDAO.getSocial(id));
	}

	public static void getSocialList() {
		List<Social> socials = SocialDAO.getSocials();
		ArrayList<Social> ol = new ArrayList<Social>();

		for (Social m : socials) {
			ol.add(m);
			System.out.println(m);
		}
	}

	public static void getSocialsByMemberIdTest(int id) {
		// Session session = SessionUtil.getSession();
		/*
		 * List<Social> socials = SocialDAO.getSocialsByMemberId(id); ArrayList<Social>
		 * ol = new ArrayList<Social>();
		 * 
		 * for(Social m : socials) { // ol.add(m); System.out.println(m); }
		 */

		SocialDAO.getSocialsByMemberId(id);
	}

	public static void deleteSocialFromMemberTest(int id) {
		SocialDAO.deleteSocialFromMember(id);
	}

}
