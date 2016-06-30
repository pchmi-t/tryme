package com.tryme.managers;

import java.util.List;

import com.tryme.framework.bean.Test;

public interface TestManager {
	
	public void save(Test test) throws Exception;

	public Test get(String id) throws Exception;
	
	public List<Test> get(String subject, String grade) throws Exception;

	public List<Test> getAll() throws Exception;
	
	


}
