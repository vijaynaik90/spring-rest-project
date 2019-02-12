package vijay.sprintboot.springrestapp.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vijay.sprintboot.springrestapp.model.VendorDTO;
import vijay.sprintboot.springrestapp.model.VendorListDTO;
import vijay.sprintboot.springrestapp.services.VendorService;

@Api(description = "This is my Vendor API")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    @Autowired
    private VendorService vendorService;

    public static final String BASE_URL = "/api/v1/vendors";

    @ApiOperation(value = "View List of Vendors", notes="These are some API Notes")
    @GetMapping
    public ResponseEntity<VendorListDTO> getVendors() {
        return new ResponseEntity<>(
                new VendorListDTO(vendorService.getAllVendors()),HttpStatus.OK
        );
    }

    @ApiOperation(value = "Get Vendor by Id")
    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id) {
        VendorDTO vendor = vendorService.getVendorById(id);
        return new ResponseEntity<>(vendor,HttpStatus.OK);
    }


    @ApiOperation(value = "Create a new Vendor")
    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {

        VendorDTO newVendor = vendorService.createVendor(vendorDTO);
        return new ResponseEntity<>(newVendor,HttpStatus.CREATED);

    }

    @ApiOperation(value = "Update an existing Vendor")
    @PutMapping({"/{id}"})
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return new ResponseEntity<>(vendorService.updateVendorById(id, vendorDTO),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Update an existing Vendor")
    @PatchMapping({"/{id}"})
    public ResponseEntity<VendorDTO> patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return new ResponseEntity<>(vendorService.patchVendorById(id, vendorDTO),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Vendor")
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id){
        vendorService.deleteVendorById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
