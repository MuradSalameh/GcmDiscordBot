package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DiscordSessionUtil {

	private static DiscordSessionUtil instance = new DiscordSessionUtil();
	private static SessionFactory sessionFactory;

	public static DiscordSessionUtil getInstance() {
		return instance;
	}

	private DiscordSessionUtil() {
		Configuration configuration = new Configuration();
		configuration.configure("/main/resources/hibernate.cfg.xml");

		sessionFactory = configuration.buildSessionFactory();
	}

	public static Session getSession() {
		Session session = getInstance().sessionFactory.openSession();

		return session;
	}

}
