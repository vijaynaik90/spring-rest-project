package vijay.sprintboot.springrestapp.services;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import vijay.sprintboot.springrestapp.domain.Customer;
import vijay.sprintboot.springrestapp.mapper.CustomerMapper;
import vijay.sprintboot.springrestapp.model.CustomerDTO;
import vijay.sprintboot.springrestapp.repositories.CustomerRespository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerMapper customerMapper;
    private final CustomerRespository customerRespository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRespository customerRespository) {
        this.customerMapper = customerMapper;
        this.customerRespository = customerRespository;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRespository.findAll().stream()
                .map(customer -> {
                 CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                 customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                 return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRespository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        //convert dto to customer object
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return saveAndReturnCustomerDTO(customer);
    }

    private CustomerDTO saveAndReturnCustomerDTO(Customer customer) {
        Customer savedCustomer = customerRespository.save(customer);
        CustomerDTO result = customerMapper.customerToCustomerDTO(savedCustomer);
        result.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
        return result;
    }

    @Override
    public CustomerDTO updateCustomerById(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnCustomerDTO(customer);
    }


    @Override
    public CustomerDTO patchCustomerById(Long id, CustomerDTO customerDTO) {

        //find customer by id and then update

        return customerRespository.findById(id).map(customer -> {

            if(customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO result = customerMapper.customerToCustomerDTO(customerRespository.save(customer));
            result.setCustomerUrl("/api/v1/customer/" + id);
            return result;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRespository.deleteById(id);
    }
}
