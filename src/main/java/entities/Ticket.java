package entities;

import java.util.Date;

public class Ticket {
	
	private int id;
	private float amount;
	private Date creationDate;
	private Date paymentDate;
	private static int cmp = 0;
	
	public Ticket(int id, float amount, Date creationDate, Date paymentDate) {
		this.id = id;
		this.amount = amount;
		this.creationDate = creationDate;
		this.paymentDate = paymentDate;
	}

	public Ticket(float amount, Date creationDate, Date paymentDate) {
		this.id = ++cmp;
		this.amount = amount;
		this.creationDate = creationDate;
		this.paymentDate = paymentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", amount=" + amount + ", creationDate=" + creationDate + ", paymentDate="
				+ paymentDate + "]";
	}
	
	
}
