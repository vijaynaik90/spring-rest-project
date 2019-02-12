package vijay.sprintboot.springrestapp.services;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import vijay.sprintboot.springrestapp.domain.Vendor;
import vijay.sprintboot.springrestapp.mapper.VendorMapper;
import vijay.sprintboot.springrestapp.model.VendorDTO;
import vijay.sprintboot.springrestapp.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImmpl implements VendorService {

    public static final String  VENDOR_BASE_URL = "/api/v1/vendors/";
    private final VendorMapper vendorMapper;

    private final VendorRepository vendorRepository;

    public VendorServiceImmpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public List<VendorDTO> getAllVendors() {
        //convert list<Vendor> to List<VendorDTO>
        return vendorRepository.findAll().stream()
                                .map(vendor -> {
                                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                                    vendorDTO.setVendorUrl(VENDOR_BASE_URL + vendor.getId());
                                    return vendorDTO;
                                })
                                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                                .map(vendorMapper::vendorToVendorDTO)
                                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO updateVendorById(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO patchVendorById(Long id, VendorDTO vendorDTO) {
        // like upsert
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName()!=null)
                vendor.setName(vendorDTO.getName());
            Vendor updatedVendor = vendorRepository.save(vendor);
            VendorDTO result = vendorMapper.vendorToVendorDTO(vendor);
            result.setVendorUrl(VENDOR_BASE_URL + updatedVendor.getId());
            return result;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO result = vendorMapper.vendorToVendorDTO(savedVendor);
        result.setVendorUrl(VENDOR_BASE_URL + savedVendor.getId());
        return result;
    }
}
