package com.FCdemo.service;

import com.FCdemo.model.TaiChinh;

public interface TaiChinhDAO {
	public int addTaiChinh(TaiChinh taiChinh);
	public int updateTaiChinh(TaiChinh taiChinh);
	public int deleteTaiChinh(int id);
}
