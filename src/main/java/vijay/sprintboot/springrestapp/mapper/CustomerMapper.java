package vijay.sprintboot.springrestapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vijay.sprintboot.springrestapp.domain.Customer;
import vijay.sprintboot.springrestapp.model.CustomerDTO;

@Mapper(componentModel="spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
