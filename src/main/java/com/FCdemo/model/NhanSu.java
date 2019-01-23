package com.FCdemo.model;

public class NhanSu {
	private int ID;
	private String Name;
	private int Age;
	private String Sex;

	private String KindID;

	private String Avatar;

	public NhanSu() {

	}

	public final int getID() {
		return ID;
	}

	public final void setID(int iD) {
		if (iD > 0 && iD < Math.pow(10, 10))
			ID = iD;
	}

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
		if (age > 0) {
			this.Age = age;
		} else {
			Age = 0;
			System.out.println("tuoi k hop le");
		}
	}

	public final String getSex() {
		return Sex;
	}

	public final void setSex(String sex) {
		if (sex.toLowerCase().equals("female") || sex.toLowerCase().equals("male") || sex.toLowerCase().equals("nam")
				|| sex.toLowerCase().equals("nữ"))
			Sex = sex;
		else {
			Sex = "";
			System.out.println("loi nhap gioi tinh");
		}
	}

	public final String getKindID() {
		return KindID;
	}

	public final void setKindID(String kindID) {
		KindID = kindID;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

}
