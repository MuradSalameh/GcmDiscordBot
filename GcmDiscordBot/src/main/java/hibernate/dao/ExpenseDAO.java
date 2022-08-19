package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.DiscordSessionUtil;
import hibernate.model.Expense;

public class ExpenseDAO {

	public static void addExpense(Expense bean) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.persist(bean); // Dafür die add expense nicht mehr aufrufen, da direkt im bean gespeichert
		// wird.
		tx.commit();
		session.close();
	}

	public static Expense getExpense(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Expense expense = session.get(Expense.class, id);

		return expense;
	}

	public static List<Expense> getExpenses() {
		Session session = DiscordSessionUtil.getSession();
		String hql = "from Expense";
		Query query = session.createQuery(hql);
		List<Expense> expenses = query.list();
		session.close();
		return expenses;
	}

	public static void deleteExpense(int id) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Expense expense = session.get(Expense.class, id);
		session.remove(expense);
		tx.commit();
		session.close();

	}

	public static void updateExpense(int id, Expense expense) {
		Session session = DiscordSessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Expense old = session.get(Expense.class, id);

		old.setExpenseTitle(expense.getExpenseTitle());
		old.setExpenseDescription(expense.getExpenseDescription());
		old.setAmount(expense.getAmount());
		old.setDate(expense.getDate());
		old.setRecipientName(expense.getRecipientName());

		// old.setExpenseTypes(expense.getExpenseTypes());

		session.saveOrUpdate(old);
		session.flush();
		tx.commit();
		session.close();
	}

}
