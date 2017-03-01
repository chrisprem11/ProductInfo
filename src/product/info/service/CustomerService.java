package product.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.info.DTO.CustomerDTO;
import product.info.model.Customer;
import product.info.model.CustomerAddress;
import product.info.repository.CustomerAddressRepository;
import product.info.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerAddressRepository customerAddressRepository;

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer createNewCustomer(CustomerDTO customerDTO) {

		Customer customer = new Customer();
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setContact(customerDTO.getContact());
		customer.setAdditonalDetailOne(customerDTO.getAdditonalDetailOne());
		customer.setAdditonalDetailTwo(customerDTO.getAdditonalDetailTwo());
		customerRepository.save(customer);
		System.out.println("Customer created.");

		Customer savedCustomer = customerRepository.findById(customer.getId());
		CustomerAddress address = new CustomerAddress();
		address.setCustomer(savedCustomer);
		address.setAddress(customerDTO.getAddress());
		customerAddressRepository.save(address);
		System.out.println("Address saved.");

		return customer;
	}
}
