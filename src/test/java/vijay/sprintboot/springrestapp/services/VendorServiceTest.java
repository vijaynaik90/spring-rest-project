package vijay.sprintboot.springrestapp.services;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vijay.sprintboot.springrestapp.domain.Vendor;
import vijay.sprintboot.springrestapp.mapper.VendorMapper;
import vijay.sprintboot.springrestapp.model.VendorDTO;
import vijay.sprintboot.springrestapp.repositories.VendorRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VendorServiceTest {

    @Mock
    VendorRepository vendorRepository;

    VendorService underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new VendorServiceImmpl(VendorMapper.INSTANCE,vendorRepository);
    }

    @Test
    public void getAllVendors() {
        Vendor c1 = new Vendor();
        c1.setId(1L);
        c1.setName("John");

        Vendor c2 = new Vendor();
        c2.setId(2L);
        c2.setName("Vijay");

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(c1,c2));

        List<VendorDTO> customers = underTest.getAllVendors();

        Assert.assertEquals(2,customers.size());
    }

    @Test
    public void getVendorById() {

        Vendor c1 = new Vendor();
        c1.setId(1L);
        c1.setName("John");

        when(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(c1));

        VendorDTO customerDTO = underTest.getVendorById(c1.getId());

        assertEquals("John",customerDTO.getName());
    }

    @Test
    public void createVendor() {
        VendorDTO customerDTO = new VendorDTO();
        customerDTO.setName("John");

        Vendor c1 = new Vendor();
        c1.setId(1L);
        c1.setName("John");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(c1);

        VendorDTO result = underTest.createVendor(customerDTO);

        assertEquals(customerDTO.getName(), result.getName());
        assertEquals("/api/v1/vendors/1", result.getVendorUrl());
    }


    @Test
    public void updateVendorById() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("UpdatedVendor");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        Mockito.when(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO returnedDTO = underTest.updateVendorById(ArgumentMatchers.anyLong(),vendorDTO);

        Assert.assertEquals(vendorDTO.getName(),returnedDTO.getName());
        Assert.assertEquals("/api/v1/vendors/1",returnedDTO.getVendorUrl());


    }
}
