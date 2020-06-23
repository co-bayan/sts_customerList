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
		d1.setFamilyName("セラク");
		d1.setFirstName("太郎");
		repository.saveAndFlush(d1);
		
		CustomerData d2 = new CustomerData();
		d2.setFamilyName("SMT");
		d2.setFirstName("次郎");
		repository.saveAndFlush(d2);
		
		CustomerData d3 = new CustomerData();
		d3.setFamilyName("新宿");
		d3.setFirstName("三郎");
		repository.saveAndFlush(d3);
		
		CustomerData d4 = new CustomerData();
		d4.setFamilyName("東京");
		d4.setFirstName("四郎");
		repository.saveAndFlush(d4);
	}

}
