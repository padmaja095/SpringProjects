package com.springdemo.rest;

import java.util.List;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	
	//autowire the customerservice
	@Autowired
	private CustomerService customerService;
	
	//add mapping for Get /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
		}
	
	//add mapping for Get /customers/{customerId}
		@GetMapping("/customers/{customerId}")
		public Customer getCustomer(@PathVariable int customerId)
		{
			Customer customer=customerService.getCustomer(customerId);
		    if(customer== null)
		    {
		    	throw new CustomerNotFoundException("Customer id not found-"+customerId);
		    }
			return customer;
		}
		// add mappinf for POST / customers- add new customer
		@PostMapping("/customers")
		public Customer addCustomer(@RequestBody Customer customer)
		{
			// also just in case the pass an id in JSOn .. set id up to 0
			//this is force save for new  item instead of update
			customer.setId(0);
			customerService.saveCustomer(customer);
		    return customer;
		}
		@PutMapping("/customers")
		public Customer updateCustomer(@RequestBody Customer customer)
		{
		     customerService.saveCustomer(customer);
		     return customer;
		}
		
		@DeleteMapping("/customers/{customerId}")
		public String deleteCustomer(@PathVariable int customerId)
		{
			
			Customer customer=customerService.getCustomer(customerId);
			if(customer== null)
			{
				throw new CustomerNotFoundException("Customer id is not found-"+customerId);
			}
			customerService.deleteCustomer(customerId);
			return "Deleted customer Id is ="+customerId;
			
		}
	
	
	

}
