package vijay.sprintboot.springrestapp.mapper;

import org.junit.Test;
import vijay.sprintboot.springrestapp.domain.Customer;
import vijay.sprintboot.springrestapp.model.CustomerDTO;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String FIRST_NAME = "JOHN";
    public static final String LAST_NAME = "Doe";
    public static final long ID = 1L;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(LAST_NAME,customerDTO.getLastName());
        assertEquals(FIRST_NAME,customerDTO.getFirstName());
    }
}
