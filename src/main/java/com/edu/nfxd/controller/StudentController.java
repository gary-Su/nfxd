package com.edu.nfxd.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.edu.nfxd.model.Student;
import com.edu.nfxd.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	public StudentService service;
	
	@RequestMapping(value="/preList",method = RequestMethod.GET)
	public String preList(Model model) {
		return "preList";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public ModelAndView  listUser(ModelAndView modelAndView, @Valid String command, BindingResult bindingResult) {
		  if(bindingResult.hasErrors()){
	            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
	            modelAndView.setViewName("preList");
	            return modelAndView;
	        }

	        if(!"9527".equals(command)){
	            modelAndView.addObject("error","口令错误！！！");
	            modelAndView.setViewName("preList");
	            return modelAndView;
	        }
	        
	        List<Student> list = service.selectAll();
	        modelAndView.addObject("list",list);
	        modelAndView.setViewName("list");
	        return modelAndView;
	}
	@RequestMapping(value="/index")
	public String index(Model model) {
		 model.addAttribute("student", new Student());
		return "index";
	}
	 
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView add(@ModelAttribute Student student,ModelAndView modelAndView) {
		int result = service.insertSelective(student);
		if(result == 1) {
			 modelAndView.addObject("result","报备成功！！！");
		}else {
			 modelAndView.addObject("result","系统异常！！！");
		}
		 modelAndView.setViewName("index");
		 return modelAndView;
	}
}