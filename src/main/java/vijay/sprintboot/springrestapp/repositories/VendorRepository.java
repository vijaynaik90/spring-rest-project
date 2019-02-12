package vijay.sprintboot.springrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vijay.sprintboot.springrestapp.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
