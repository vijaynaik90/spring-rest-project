package vijay.sprintboot.springrestapp.services;

import vijay.sprintboot.springrestapp.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById (Long id);

    CustomerDTO createCustomer(CustomerDTO customer);

    CustomerDTO updateCustomerById(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomerById(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
