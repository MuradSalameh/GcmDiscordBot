package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.SessionUtil;
import hibernate.model.Partner;

public class PartnerDAO {
    // database access methods
    
    // add partner 
	public static void addPartner(Partner bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); // Dafür die add partner nicht mehr aufrufen, da direkt im bean gespeichert
								// wird.
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	//get partner
	public static Partner getPartner(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Partner partner = session.get(Partner.class, id);
		tx.commit();
		 session.clear();
		 session.close();
		return partner;
	}

	
	//get list of all partners
	public static List<Partner> getPartners() {
		Session session = SessionUtil.getSession();
		
		List<Partner> list = session.createQuery(
			"select o from Partner o",
			Partner.class)
			.getResultList();


		for (Partner t : list) {		
			System.out.println(t);
		}
	
		 session.clear();
		 session.close();
		return list;
	}

	
	// delete partner
	public static void deletePartner(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Partner partner = session.get(Partner.class, id);
		session.remove(partner);
		tx.commit();
		 session.clear();
		 session.close();

	}

	
	// update partner
	public static void updatePartner(int id, Partner partner) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Partner old = session.get(Partner.class, id);

		old.setCompanyName(partner.getCompanyName());
		old.setContactPersonName(partner.getContactPersonName());
		old.setContactPersonPhone(partner.getContactPersonPhone());
		old.setContactPersonMail(partner.getContactPersonMail());
		old.setFirstName(partner.getFirstName());
		old.setLastName(partner.getLastName());
		old.setAdressStreet(partner.getAdressStreet());
		old.setAdressNumber(partner.getAdressNumber());
		old.setAdressPostCode(partner.getAdressPostCode());
		old.setAdressCity(partner.getAdressCity());
		old.setCountry(partner.getCountry());
		old.setEmail(partner.getEmail());
		old.setPhoneNumber(partner.getPhoneNumber());

		session.saveOrUpdate(old);
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}

}
