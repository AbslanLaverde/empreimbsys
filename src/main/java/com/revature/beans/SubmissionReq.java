package com.revature.beans;

public class SubmissionReq {
	
	private double amount;
	private String description;
	private String reimbReceipt;
	private int managerId;
	private int reimbType;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReimbReceipt() {
		return reimbReceipt;
	}
	public void setReimbReceipt(String reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getReimbType() {
		return reimbType;
	}
	public void setReimbType(int reimbType) {
		this.reimbType = reimbType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + managerId;
		result = prime * result + ((reimbReceipt == null) ? 0 : reimbReceipt.hashCode());
		result = prime * result + reimbType;
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
		SubmissionReq other = (SubmissionReq) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (managerId != other.managerId)
			return false;
		if (reimbReceipt == null) {
			if (other.reimbReceipt != null)
				return false;
		} else if (!reimbReceipt.equals(other.reimbReceipt))
			return false;
		if (reimbType != other.reimbType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SubmissionReq [amount=" + amount + ", description=" + description + ", reimbReceipt=" + reimbReceipt
				+ ", managerId=" + managerId + ", reimbType=" + reimbType + "]";
	}
	public SubmissionReq(double amount, String description, String reimbReceipt, int managerId, int reimbType) {
		super();
		this.amount = amount;
		this.description = description;
		this.reimbReceipt = reimbReceipt;
		this.managerId = managerId;
		this.reimbType = reimbType;
	}
	public SubmissionReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
