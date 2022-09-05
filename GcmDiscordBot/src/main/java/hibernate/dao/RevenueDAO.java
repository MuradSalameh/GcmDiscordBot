package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.SessionUtil;
import hibernate.model.Revenue;

public class RevenueDAO {
    // database access methods
    
    // add revenue
	public static void addRevenue(Revenue bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean);
		tx.commit();
		 session.clear();
		 session.close();
	}

	
	// get revenue
	public static Revenue getRevenue(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Revenue rev = session.get(Revenue.class, id);
		tx.commit();
		 session.clear();
		 session.close();
		return rev;
	}

	
	// get list of all revenues
	public static List<Revenue> getRevenues() {
		Session session = SessionUtil.getSession();
		List<Revenue> list = session.createQuery(
			"select o from Revenue o",
			Revenue.class)
			.getResultList();


		for (Revenue t : list) {		
			System.out.println(t);
		}
		
		 session.clear();
		 session.close();
		return list;
	}
	
	

	
	// delete revenue
	public static void deleteRevenue(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Revenue revenue = session.get(Revenue.class, id);
		session.remove(revenue);
		tx.commit();
		 session.clear();
		 session.close();

	}

	
	// update revenue
	public static void updateRevenue(int id, Revenue revenue) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Revenue old = session.get(Revenue.class, id);

		old.setRevenueTitle(revenue.getRevenueTitle());
		old.setRevenueDescription(revenue.getRevenueDescription());
		old.setAmount(revenue.getAmount());
		old.setDate(revenue.getDate());

		session.saveOrUpdate(old);
		//session.flush();
		tx.commit();
		 session.clear();
		 session.close();
	}

}
