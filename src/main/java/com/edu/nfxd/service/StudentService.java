package com.edu.nfxd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.nfxd.dao.StudentDao;
import com.edu.nfxd.model.Student;

@Service
public class StudentService {
	@Autowired
	public StudentDao dao;
	
	public int deleteByPrimaryKey(Integer empid) {
		return dao.deleteByPrimaryKey(empid);
	}

	public int insert(Student record) {
		return dao.insert(record);
	}

	public int insertSelective(Student record) {
		return dao.insertSelective(record);
	}

	public Student selectByPrimaryKey(Integer empid) {
		return dao.selectByPrimaryKey(empid);
	}

	public int updateByPrimaryKeySelective(Student record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Student record) {
		return dao.updateByPrimaryKey(record);
	}

	public List<Student> selectAll() {
		return dao.selectAll();
	}
}
