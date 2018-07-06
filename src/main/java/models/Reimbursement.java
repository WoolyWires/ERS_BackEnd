package models;

import java.time.Instant;

/**
 * Class to create reimbursement tickets for ERS Reimbursement system.
 * 
 * @author Aisha Peters
 *
 */

public final class Reimbursement {
	private int id;
	private int author;
	private double amount;
	private Instant submitted;
	private Instant resolved;
	private String description;
	private String receipt;
	private String type;
	private String status;
	
	public Reimbursement(double amount, Instant submitted, Instant resolved, String description, String receipt,
			String type, String status) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.type = type;
		this.status = status;
	}
	
	public Reimbursement() {
		super();
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getStatusId() {
		if(this.status.toLowerCase().equals("pending"))
			return 0;
		else if(this.status.toLowerCase().equals("approved"))
			return 1;
		else
			return 2;
	}
	
	public int getTypeId() {
		if(this.status.toLowerCase().equals("lodging"))
			return 0;
		else if(this.status.toLowerCase().equals("travel"))
			return 1;
		else if(this.status.toLowerCase().equals("food"))
			return 2;
		else
			return 3;
	}
	
	public void setType(int type) {
		if(type == 0)
			this.type = "lodging";
		else if(type == 1)
			this.type = "travel";
		else if(type == 2)
			this.type = "food";
		else
			this.type = "other";
	}
	
	public void setStatus(int status) {
		if(status == 0)
			this.status = "pending";
		else if(status == 1)
			this.status = "approved";
		else
			this.status = "denied";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Instant getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Instant submitted) {
		this.submitted = submitted;
	}
	public Instant getResolved() {
		return resolved;
	}
	public void setResolved(Instant resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getType() {
		return type;
	}
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", author=" + author + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", receipt=" + receipt + ", type=" + type
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
