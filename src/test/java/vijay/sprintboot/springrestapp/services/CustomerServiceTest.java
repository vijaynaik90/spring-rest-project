package vijay.sprintboot.springrestapp.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vijay.sprintboot.springrestapp.domain.Customer;
import vijay.sprintboot.springrestapp.mapper.CustomerMapper;
import vijay.sprintboot.springrestapp.model.CustomerDTO;
import vijay.sprintboot.springrestapp.repositories.CustomerRespository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Mock
    CustomerRespository customerRespository;
    CustomerService underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CustomerServiceImpl(CustomerMapper.INSTANCE,customerRespository);
    }

    @Test
    public void getAllCustomers() {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstName("Vijay");
        c2.setLastName("Naik");

        when(customerRespository.findAll()).thenReturn(Arrays.asList(c1,c2));

        List<CustomerDTO> customers = underTest.getAllCustomers();

        Assert.assertEquals(2,customers.size());
    }

    @Test
    public void getCustomerById() {

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");

        when(customerRespository.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(c1));

        CustomerDTO customerDTO = underTest.getCustomerById(c1.getId());

        assertEquals("John",customerDTO.getFirstName());
    }

    @Test
    public void createCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");

        when(customerRespository.save(any(Customer.class))).thenReturn(c1);

        CustomerDTO result = underTest.createCustomer(customerDTO);

        assertEquals(customerDTO.getFirstName(), result.getFirstName());
        assertEquals("/api/v1/customer/1", result.getCustomerUrl());
    }


    @Test
    public void saveCustomerByDTO() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");
        customerDTO.setLastName("Anderson");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRespository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = underTest.updateCustomerById(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void patchCustomerById() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Mesut");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName("Ozil");
        savedCustomer.setId(1l);

        when(customerRespository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        Optional<CustomerDTO> optionalSavedDTO = Optional.of(underTest.patchCustomerById(anyLong(), customerDTO));

        CustomerDTO savedDto = optionalSavedDTO.get();
        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(savedCustomer.getLastName(), savedDto.getLastName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        underTest.deleteCustomerById(id);

        verify(customerRespository, times(1)).deleteById(anyLong());
    }
}
