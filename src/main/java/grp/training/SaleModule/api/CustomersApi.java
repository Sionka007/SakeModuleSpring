package grp.training.SaleModule.api;

import grp.training.SaleModule.ProductsDTO.CustomersDTO;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@Validated
public class CustomersApi {
    @Autowired
    CustomerService customerService;
    @Autowired
    Environment environment;

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<CustomersDTO>> getDetailsByName(@PathVariable String name) throws SaleModuleException{
        return new ResponseEntity<>(customerService.getDetailsByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/phone/{phoneNo}")
    public ResponseEntity<CustomersDTO> getDetailsByPhone(@PathVariable  Long phoneNo) throws SaleModuleException{
        return new ResponseEntity<>(customerService.getDetailsByPhone(phoneNo), HttpStatus.OK);
    }
    @GetMapping(value = "/id/{customerId}")
    public ResponseEntity<CustomersDTO> getDetailsById(@PathVariable Integer customerId) throws SaleModuleException{
        return new ResponseEntity<>(customerService.getDetailsById(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<String> addCustomers(@Valid @RequestBody  CustomersDTO customersDTO){
        String msg= environment.getProperty("API.CUSTOMER.INSERT_SUCCESS") + " "+ customerService.addCustomers(customersDTO);
        return  new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping(value="/customer/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody CustomersDTO customersDTO) throws SaleModuleException{
        customerService.updateCustomer(customerId, customersDTO);
        String msg= environment.getProperty("API.CUSTOMER.UPDATE_SUCCESS") + " "+ customerId;
        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{customerId}")
    public ResponseEntity<String> deletedCustomers(@PathVariable Integer customerId) throws SaleModuleException{
        customerService.deletedCustomers(customerId);
        String msg= environment.getProperty("API.CUSTOMER.DELETE_SUCCESS") + " "+ customerId;
        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
