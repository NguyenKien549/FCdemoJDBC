package com.FCdemo.model;

public class Sum {
	private String Name;
	private int Age;
	private String KindID;
	private double Amount;
	private String Description;
	
	public final String getName() {
		return Name;
	}
	public final void setName(String name) {
		Name = name;
	}
	public final int getAge() {
		return Age;
	}
	public final void setAge(int age) {
		Age = age;
	}
	public final String getKindID() {
		return KindID;
	}
	public final void setKindID(String kindID) {
		KindID = kindID;
	}
	public final double getAmount() {
		return Amount;
	}
	public final void setAmount(double amount) {
		Amount = amount;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
