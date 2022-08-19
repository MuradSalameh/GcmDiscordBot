package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.DiscordSessionUtil;
import hibernate.model.Revenue;

public class RevenueDAO {

	public static void addRevenue(Revenue bean) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); // Dafür die add revenue nicht mehr aufrufen, da direkt im bean gespeichert
								// wird.
		tx.commit();
		session.close();
	}

	public static Revenue getRevenue(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Revenue rev = session.get(Revenue.class, id);

		return rev;
	}

	public static List<Revenue> getRevenues() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Revenue";
		Query query = session.createQuery(hql);
		List<Revenue> revenues = query.list();
		session.close();
		return revenues;
	}

	public static void deleteRevenue(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Revenue revenue = session.get(Revenue.class, id);
		session.remove(revenue);
		tx.commit();
		session.close();

	}

	public static void updateRevenue(int id, Revenue revenue) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Revenue old = session.get(Revenue.class, id);

		old.setRevenueTitle(revenue.getRevenueTitle());
		old.setRevenueDescription(revenue.getRevenueDescription());
		old.setAmount(revenue.getAmount());
		old.setDate(revenue.getDate());

		session.saveOrUpdate(old);
		session.flush();
		tx.commit();
		session.close();
	}

}
