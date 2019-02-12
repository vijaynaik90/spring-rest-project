package vijay.sprintboot.springrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vijay.sprintboot.springrestapp.domain.Customer;

public interface CustomerRespository extends JpaRepository<Customer,Long> {
}
