package com.edu.nfxd.dao;

import java.util.List;

import com.edu.nfxd.model.Student;

public interface StudentDao {
	
    public int deleteByPrimaryKey(Integer id);

    public int insert(Student record);

    public int insertSelective(Student record);

    public Student selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Student record);

    public int updateByPrimaryKey(Student record);
    
    public List<Student> selectAll();
}