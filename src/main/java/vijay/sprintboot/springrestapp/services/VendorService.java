package vijay.sprintboot.springrestapp.services;

import vijay.sprintboot.springrestapp.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById (Long id);

    VendorDTO createVendor(VendorDTO Vendor);

    VendorDTO updateVendorById(Long id, VendorDTO VendorDTO);

    VendorDTO patchVendorById(Long id, VendorDTO VendorDTO);

    void deleteVendorById(Long id);
}
