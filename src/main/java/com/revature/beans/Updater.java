package com.revature.beans;

public class Updater {
	
	private int reimbId;
	private int reimbStatus;
	
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(int reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbId;
		result = prime * result + reimbStatus;
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
		Updater other = (Updater) obj;
		if (reimbId != other.reimbId)
			return false;
		if (reimbStatus != other.reimbStatus)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Updater [reimbId=" + reimbId + ", reimbStatus=" + reimbStatus + "]";
	}
	public Updater(int reimbId, int reimbStatus) {
		super();
		this.reimbId = reimbId;
		this.reimbStatus = reimbStatus;
	}
	public Updater() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
