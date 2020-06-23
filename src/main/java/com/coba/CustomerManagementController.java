package com.coba;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coba.repository.CustomerDataRepository;

@Controller
public class CustomerManagementController {
	
	@Autowired
	CustomerDataRepository repository;
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("customers") 
			CustomerData customers,
			ModelAndView mav) {
		mav.setViewName("customers");
		mav.addObject("title", "�ڋq�ꗗ");
		Iterable<CustomerData> list = repository.findAll();
		mav.addObject("customers", list);
		return mav;
	}
	
	// TODO 文字化け修正
	@PostConstruct
	public void init() {
		CustomerData d1 = new CustomerData();
		d1.setFamilyName("�Z���N");
		d1.setFirstName("���Y");
		repository.saveAndFlush(d1);
		
		CustomerData d2 = new CustomerData();
		d2.setFamilyName("SMT");
		d2.setFirstName("���Y");
		repository.saveAndFlush(d2);
		
		CustomerData d3 = new CustomerData();
		d3.setFamilyName("�V�h");
		d3.setFirstName("�O�Y");
		repository.saveAndFlush(d3);
		
		CustomerData d4 = new CustomerData();
		d4.setFamilyName("����");
		d4.setFirstName("�l�Y");
		repository.saveAndFlush(d4);
	}

}
