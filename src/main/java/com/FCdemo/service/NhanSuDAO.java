package com.FCdemo.service;

import com.FCdemo.model.NhanSu;

public interface NhanSuDAO {
	public int add(NhanSu nhanSu);
	public int update(NhanSu nhanSu);
	public NhanSu delete(NhanSu nhanSu);
}
