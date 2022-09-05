package hibernate.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.SessionUtil;
import hibernate.dao.ExpenseDAO;
import hibernate.model.Expense;

public class ExpenseDAOTest {

	public static void main(String[] args) {

		Session session = SessionUtil.getSession();
		int id = 2;

		// --------- addExpense() Test -----------//

		// addTestExpense();

		// --------- deleteExpense() Test -----------//

		// deleteExpenseTest(id);

		// --------- getExpense() Test to get one specific expense by id -----------//

		// getExpenseTest(id);

		// --------- getExpenses() Test to get a List of all expenses in
		// database-----------//

		getExpenseList();

		// --------- updateExpense() Test -----------//

		// String s = "Catering";
		// updateExpenseTest(id,s);

	}

	public static void addTestExpense() {
		Expense test = new Expense("test", // title
				"ttttt", // desc
				299.00, // amount
				LocalDate.of(1981, 4, 11), // date
				"heinrich" // recipient
		);

		ExpenseDAO.addExpense(test);
	}

	public static void updateExpenseTest(int id, String s) {
		Session session = SessionUtil.getSession();

		// Vorhandenen Expense anhand id aus DB holen
		Expense m = session.get(Expense.class, id);

		// Expense m ClanName wert neu setzen
		m.setExpenseTitle(s);

		// Expense m in Datenbank updaten
		ExpenseDAO.updateExpense(id, m);

		System.out.println(m);
	}

	public static void deleteExpenseTest(int id) {
		ExpenseDAO.deleteExpense(id);

		Expense expense = ExpenseDAO.getExpense(id);
		System.out.println(expense);

	}

	public static void getExpenseTest(int id) {
		System.out.println(ExpenseDAO.getExpense(id));
	}

	public static void getExpenseList() {
		List<Expense> expenses = ExpenseDAO.getExpenses();
		ArrayList<Expense> ol = new ArrayList<Expense>();

		for (Expense m : expenses) {
			ol.add(m);
			System.out.println(m);
		}
	}

}
