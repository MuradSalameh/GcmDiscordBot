package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.SessionUtil;
import hibernate.model.Member;
import hibernate.model.MemberSocials;
import hibernate.model.Social;

public class SocialDAO {


    // database access methods

    // add new social in DB
    public static void addSocial(Social bean) {
	Session session = SessionUtil.getSession();
	Transaction tx = session.beginTransaction();

	session.persist(bean);
	tx.commit();
	 session.clear();
	 session.close();
    }


    //assign social to member in MemberSocials table
    public static void addSocialToMember(int memberID, int socialID) {
	Session session = SessionUtil.getSession();
	Transaction tx = session.beginTransaction();

	Member m = session.get(Member.class, memberID);
	Social s = session.get(Social.class, socialID);

	MemberSocials ms = new MemberSocials();
	ms.setMember(m);
	ms.setSocial(s);

	session.save(ms);
	tx.commit();
	 session.clear();
	 session.close();
    }


    // get social by id
    public static Social getSocial(int id) {
	Session session = SessionUtil.getSession();
	Transaction tx = session.beginTransaction();
	Social s = session.get(Social.class, id);
	tx.commit();
	 session.clear();
	 session.close();
	return s;
    }


    // get social with highest id
    public static Social getSocialWithHighestId() {
	Session session = SessionUtil.getSession();
	// String hql = "select max(id) from Social";
	Integer maxId = (Integer) session.createNativeQuery("select max(id) from Social").getSingleResult();

	System.out.println(maxId);

	Social social = session.get(Social.class, maxId);
	
	 session.clear();
	 session.close();
	return social;
    }


    // get list of all socials
    public static List<Social> getSocials() {
	Session session = SessionUtil.getSession();

	List<Social> list = session.createQuery(
		"select o from Social o",
		Social.class)
		.getResultList();


	for (Social t : list) {		
	    System.out.println(t);
	}
	
	 session.clear();
	 session.close();
	return list;
    }



    // get socials by member id from MemberSocials table
    public static List<Social> getSocialsByMemberId(int id) {
	// SQL: SELECT * FROM gcm.member_socials where member_id= '3'

	Session session = SessionUtil.getSession();

	List<Social> list = session.createQuery(
		"select social s from MemberSocials ms where member.id= :id",
		Social.class)
		.setParameter("id", id).getResultList();


	for (Social o : list) {		
	    System.out.println(o);
	}
	
	 session.clear();
	 session.close();
	return list;
    }


    //delete social from MemberSocials table
    public static void deleteSocialFromMember(int id) {
	Session session = SessionUtil.getSession();
	Transaction tx = session.beginTransaction();

	// Delete connection from MemberSocials Table
	String hql = "delete from MemberSocials ms where social.id= :id";
	Query query = session.createQuery(hql);
	query.setParameter("id", id);

	int count = query.executeUpdate();
	System.out.println(count + " Record(s) Deleted.");

	// Remove from Social Table
	Social social = session.get(Social.class, id);
	session.remove(social);

	tx.commit();
	session.clear();
	session.close();
    }


    // delete social 
    public static void deleteSocial(int id) {
	Session session = SessionUtil.getSession();
	Transaction tx = session.beginTransaction();

	Social social = session.get(Social.class, id);
	session.remove(social);
	tx.commit();
	 session.clear();
	 session.close();

    }


    //update social 
    public static void updateSocial(int id, Social social) {
	Session session = SessionUtil.getSession();
	Transaction tx = session.beginTransaction();
	Social old = session.get(Social.class, id);

	old.setSocialPlatform(social.getSocialPlatform());
	old.setSocialUsername(social.getSocialUsername());
	old.setSocialLink(social.getSocialLink());
	old.setSocialNotes(social.getSocialNotes());

	session.saveOrUpdate(old);
	//session.flush();
	tx.commit();
	 session.clear();
	 session.close();
    }

}
