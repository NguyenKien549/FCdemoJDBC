package com.FCdemo.service;

import java.util.List;

import com.FCdemo.model.Sum;

public interface FinanceDAO {
	public List<Sum> filterEMP(String name, String type);
	public int searchbyKind(String kind);
	public float sumAmount(List<Sum> list);
}
