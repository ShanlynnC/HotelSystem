/**
 * CustomerRepository.java
 * Repository class for the Customer
 * Author: Brandon Wise - 220049173
 * Date: 07 April 2023
 */
package za.ac.cput.repository.impl;

import za.ac.cput.domain.Customer;
import za.ac.cput.repository.ICustomerRepository;

import java.util.HashSet;
import java.util.Set;

public class CustomerRepositoryImpl implements ICustomerRepository {
    private static CustomerRepositoryImpl repository = null;
    private Set<Customer> customerDB = null;

    private CustomerRepositoryImpl(){
        customerDB = new HashSet<Customer>();
    }

    public static CustomerRepositoryImpl getRepository(){
        if(repository == null) {
            repository = new CustomerRepositoryImpl();
        }
        return repository;
    }
    @Override
    public Customer create(Customer customer) {
        boolean success = customerDB.add(customer);
        if(!success)
            return null;
        return customer;
    }

    @Override
    public Customer read(String customerID) {
        for (Customer c : customerDB) {
            if (c.getCustomerID().equals(customerID))
                return c;
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        Customer oldCustomer = read(customer.getCustomerID());
        if(oldCustomer != null){
            customerDB.remove(oldCustomer);
            customerDB.add(customer);
            return customer;
        }
        return null;
    }

    @Override
    public boolean delete(String customerID) {
        Customer customerToDelete = read(customerID);
        if(customerToDelete == null)
            return false;
        customerDB.remove(customerToDelete);
        return true;
    }

    @Override
    public Set<Customer> getAll() {
        return customerDB;
    }
}