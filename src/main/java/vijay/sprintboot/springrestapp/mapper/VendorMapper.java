package vijay.sprintboot.springrestapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vijay.sprintboot.springrestapp.domain.Vendor;
import vijay.sprintboot.springrestapp.model.VendorDTO;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
