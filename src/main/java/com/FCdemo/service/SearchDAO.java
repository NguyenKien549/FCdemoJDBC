package com.FCdemo.service;


import java.util.List;
import com.FCdemo.model.NhanSu;


public interface SearchDAO {
	public List<NhanSu> searchbyLoai(String loai);
	public List<NhanSu> searchbyName(String name);
	public List<NhanSu> searchbySex(String sex);
	public List<NhanSu> searchbyAge(int age);
}
