package com.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    //need to inject the session factory
	@Autowired
	private SessionFactory sessionfactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session session=sessionfactory.getCurrentSession();
		//create the query
		Query<Customer> thequery=session.createQuery("from Customer order by lastName",Customer.class);
		//get the list of customer by execute the query
		List<Customer> customers= thequery.getResultList();
		//return the list of customer thta recieved
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		//get the current hibernate session
		Session session=sessionfactory.getCurrentSession();
		//save the customer
		//session.save(theCustomer);
		//save or update the customer
		session.saveOrUpdate(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {
		     // get the current hibernate session
				Session session=sessionfactory.getCurrentSession();
				//now retrieve from database using the primary key
				Customer thecustomer=session.get(Customer.class, theId);
			// return the customer
				return thecustomer;
				
		
	}


	@Override
	public void deleteCustomer(int theId) {
		  // get the current hibernate session
		Session session=sessionfactory.getCurrentSession();
		Customer customer =session.get(Customer.class, theId);
		//get the customer details using the id
		session.delete(customer);
		//delete the customer
		
		//or we can also use delete using the query 
		//Query thequery= session.createQuery("delete from Customer  where id=:theCustomerId");
		//thequery.setParameter("theCustomerId", theId);
		//thequery.executeUpdate();
	}

}
