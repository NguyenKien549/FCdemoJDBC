package com.FCdemo.model;

import java.text.DecimalFormat;

public class TaiChinh {
	private int ID;
	private String Name;
	private double Amount;
	private String KindID;
	
	


	public final int getID() {
		return ID;
	}

	public final void setID(int iD) {
		if (iD > 0)
			ID = iD;
	}

	public final String getName() {
		return Name;
	}

	public final void setName(String name) {
		Name = name;
	}

	public final double getAmount() {
		return Amount;
	}

	public final void setAmount(double amount) {
		
		if (amount > 0) {
			DecimalFormat df = new DecimalFormat(".###");
			Amount =Double.parseDouble(df.format(amount));
		} else
			System.out.println("tai khoan k am");

	}

	public final String getKindID() {
		return KindID;
	}

	public final void setKindID(String kindID) {
		KindID = kindID;
	}
}
