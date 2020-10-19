package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	// need to inject the customer dao into controller
	//@Autowired
	//private CustomerDAO customerDAO;
	//instead of injectin the DAO, inject the service, commenting the above lines
	@Autowired
	private CustomerService customerService;
	
	
//	@RequestMapping("/list")
	@GetMapping("/list")
	public String listCustomers(Model theModel)
	{   //get customers from the dao
		List<Customer> thecustomers=customerService.getCustomers();
		// add those customers to the model
		theModel.addAttribute("customers",thecustomers);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		//create model attribute to bind form data
		Customer thecustomer= new Customer();
		theModel.addAttribute("customer",thecustomer);
		
		return "add-customer-form";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		//save the customer using our service
		return "redirect:/customer/list";
		
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel)
	{
		// get the customers from the service
		System.out.println("The ID is "+theId);
		Customer thecustomer= customerService.getCustomer(theId);
		//set the customer as  model attribute to prepopulate the form
		System.out.println(thecustomer.getFirstName());
		System.out.println(thecustomer.getLastName());
		System.out.println(thecustomer.getEmail());
		theModel.addAttribute("customer",thecustomer);
		// send it over to form
		
		return "add-customer-form";
		
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		System.out.println(theId);
		 customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
		
	}
	

}
