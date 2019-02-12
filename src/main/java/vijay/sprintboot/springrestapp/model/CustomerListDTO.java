package vijay.sprintboot.springrestapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListDTO {

    private List<CustomerDTO> customers;
}
