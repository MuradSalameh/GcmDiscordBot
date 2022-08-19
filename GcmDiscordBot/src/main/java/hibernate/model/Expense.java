package hibernate.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity
@Table(name = "expense")
public class Expense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "expense_title")
	private String expenseTitle;

	@Column(name = "expense_description")
	private String expenseDescription;

	@Column(name = "amount")
	private double amount;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "recipient_name")
	private String recipientName;

	public Expense() {
		super();
	}

	public Expense(String expenseTitle, String expenseDescription, double amount, LocalDate date,
			String recipientName) {
		super();
		this.expenseTitle = expenseTitle;
		this.expenseDescription = expenseDescription;
		this.amount = amount;
		this.date = date;
		this.recipientName = recipientName;

	}

	@XmlElement(name = "ExpenseTitle")
	public String getExpenseTitle() {
		return expenseTitle;
	}

	public void setExpenseTitle(String expenseTitle) {
		this.expenseTitle = expenseTitle;
	}

	@XmlElement(name = "ExpenseDescription")
	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	@XmlElement(name = "Amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(name = "Date")
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@XmlElement(name = "RecipientName")
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nExpense id=" + id + "\nexpenseTitle=" + expenseTitle + "\nexpenseDescription=" + expenseDescription
				+ "\namount=" + amount + "\ndate=" + date + "\nrecipientName=" + recipientName
				+ "\n----------------------------------" + "\n";
	}

}
