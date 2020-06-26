package com.coba;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coba.repository.CustomerDataRepository;

@Controller
public class CustomerManagementController {
	
	@Autowired
	CustomerDataRepository repository;
	
	@RequestMapping(path = "customers", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("customer") 
			CustomerData customer,
			ModelAndView mav) {
		mav.setViewName("customers");
		Iterable<CustomerData> list = repository.findAll();
		mav.addObject("customers", list);
		return mav;
	}
	
	@RequestMapping(path = "customers", method = RequestMethod.POST)
	public ModelAndView save(@Validated @ModelAttribute("customer")
			CustomerData customer,
			BindingResult result,
			ModelAndView mav) {
		if (result.hasErrors()) {
			Iterable<CustomerData> list = repository.findAll();
			mav.addObject("customers", list);
			return mav;
		} else {
			repository.saveAndFlush(customer);
			return new ModelAndView("redirect:/customers");
		}
	}
	
	@RequestMapping(path = "customers", method = RequestMethod.POST,
					params = "delete")
	public ModelAndView delete(@ModelAttribute("customers")
			CustomerData customer,
			ModelAndView mav) {
		repository.delete(customer);
		return new ModelAndView("redirect:/customers");
	}
	
	@RequestMapping(path = "customers/edit", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("customer")
			CustomerData customer,
			ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("id", customer.getId());
		mav.addObject("familyName", customer.getFamilyName());
		mav.addObject("firstName", customer.getFirstName());
		return mav;
	}
	
	@RequestMapping(path = "customers/edit", method = RequestMethod.POST,
					params = "update")
	public ModelAndView update(@Validated @ModelAttribute("customer")
			CustomerData customer,
			BindingResult result,
			ModelAndView mav) {
		if (result.hasErrors()) {
			mav.setViewName("edit");
			mav.addObject("id", customer.getId());
			mav.addObject("familyName", customer.getFamilyName());
			mav.addObject("firstName", customer.getFirstName());
		} else {
			mav.setViewName("redirect:/customers");
			repository.saveAndFlush(customer);
			Iterable<CustomerData> list = repository.findAll();
			mav.addObject("customers", list);
		}
		return mav;
	}
	
	@RequestMapping(path = "customers/delete", method = RequestMethod.POST)
	public ModelAndView confirmDelete(@ModelAttribute("customer")
			CustomerData customer,
			ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("id", customer.getId());
		mav.addObject("familyName", customer.getFamilyName());
		mav.addObject("firstName", customer.getFirstName());
		return mav;
	}
	
	@PostConstruct
	public void init() { 
		CustomerData d1 = new CustomerData();
		d1.setFamilyName("山田");
		d1.setFirstName("太郎");
		repository.saveAndFlush(d1);
		
		CustomerData d2 = new CustomerData();
		d2.setFamilyName("出来杉");
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
