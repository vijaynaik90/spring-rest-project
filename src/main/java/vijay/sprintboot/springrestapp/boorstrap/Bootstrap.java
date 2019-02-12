package vijay.sprintboot.springrestapp.boorstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vijay.sprintboot.springrestapp.domain.Category;
import vijay.sprintboot.springrestapp.domain.Customer;
import vijay.sprintboot.springrestapp.domain.Vendor;
import vijay.sprintboot.springrestapp.repositories.CategoryRepository;
import vijay.sprintboot.springrestapp.repositories.CustomerRespository;
import vijay.sprintboot.springrestapp.repositories.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final  CategoryRepository categoryRepository;
    private final CustomerRespository customerRespository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRespository customerRespository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRespository = customerRespository;
        this.vendorRepository = vendorRepository;
    }


    //Spring boot specific class. Gets called on startup. Any arguments passed to JJVM wil be picked up
    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRespository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerRespository.save(customer2);

        System.out.println("Customers Loaded: " + customerRespository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        categoryRepository.save(dried);

        System.out.println("Category Count--->" + categoryRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Amazon");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Cisco");

        Vendor vendor3 = new Vendor();
        vendor3.setName("MSFT");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);

        System.out.println("Vendor COunt ====>"+ vendorRepository.count());
    }
}
