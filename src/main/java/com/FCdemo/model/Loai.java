package com.FCdemo.model;

public class Loai {
	private int ID;
	private String Name;
	private String Description;
	private String KindID;
	public Loai() {
		super();
	}
	
	public final int getID() {
		return ID;
	}
	
	public final void setID(int iD) {
		if(iD>0)
			ID = iD;
	}
	
	public final String getName() {
		return Name;
	}
	
	public final void setName(String name) {
		Name = name;
	}
	
	public final String getDescription() {
		return Description;
	}
	
	public final void setDescription(String description) {
		Description = description;
	}
	
	public final String getKindID() {
		return KindID;
	}
	
	public final void setKindID(String kindID) {
		KindID = kindID;
	}
	
}
