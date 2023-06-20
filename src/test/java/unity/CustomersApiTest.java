package unity;

import grp.training.SaleModule.ProductsDTO.CustomersDTO;
import grp.training.SaleModule.api.CustomersApi;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomersApiTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private Environment environment;

    @InjectMocks
    private CustomersApi customersApi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDetailsByName() throws SaleModuleException{
        String name="John Snow";

        List<CustomersDTO> customersDTOS=new ArrayList<>();

        CustomersDTO customersDTO=new CustomersDTO(name, "Winterfell 1", "john@snow.com", 777777777l);

        for(int i=0; i<4; i++){
            customersDTOS.add(customersDTO);
        }

        when(customerService.getDetailsByName(name)).thenReturn(customersDTOS);

        ResponseEntity<List<CustomersDTO>> response=customersApi.getDetailsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customersDTOS, response.getBody());

    }

    @Test
    public void testGetDetailsByPhone() throws SaleModuleException{
        Long phoneNumber=777777777l;


        CustomersDTO customersDTO=new CustomersDTO("John Snow", "Winterfell 1", "john@snow.com", 777777777l);


        when(customerService.getDetailsByPhone(phoneNumber)).thenReturn(customersDTO);

        ResponseEntity<CustomersDTO> response=customersApi.getDetailsByPhone(phoneNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customersDTO, response.getBody());

    }

    @Test
    public void testGetDetailsById() throws SaleModuleException{
        Integer id=7;


        CustomersDTO customersDTO=new CustomersDTO(id,"John Snow", "Winterfell 1", "john@snow.com", 777777777l);


        when(customerService.getDetailsById(id)).thenReturn(customersDTO);

        ResponseEntity<CustomersDTO> response=customersApi.getDetailsById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customersDTO, response.getBody());

    }

    @Test
    public void testAddCustomers() throws SaleModuleException{
        Integer id=123;


        CustomersDTO customersDTO=new CustomersDTO(id,"John Snow", "Winterfell 1", "john@snow.com", 777777777l);

        when(environment.getProperty("API.CUSTOMER.INSERT_SUCCESS")).thenReturn("Successfully added new customer");
        when(customerService.addCustomers(customersDTO)).thenReturn(id);

        ResponseEntity<String> response=customersApi.addCustomers(customersDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Successfully added new customer "+id, response.getBody());

        verify(customerService).addCustomers(customersDTO);

    }

    @Test
    public void testUpdateCustomer() throws SaleModuleException{
        Integer id=123;


        CustomersDTO customersDTO=new CustomersDTO(id,"John Snow", "Winterfell 1", "john@snow.com", 777777777l);

        when(environment.getProperty("API.CUSTOMER.UPDATE_SUCCESS")).thenReturn("Successfully update customer with id:");
        when(customerService.addCustomers(customersDTO)).thenReturn(id);

        ResponseEntity<String> response=customersApi.updateCustomer(id, customersDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully update customer with id: "+id, response.getBody());

        verify(customerService).updateCustomer(id, customersDTO);

    }

    @Test
    public void testDeletedCustomers() throws SaleModuleException{
        Integer id=123;

        when(environment.getProperty("API.CUSTOMER.DELETE_SUCCESS")).thenReturn("Successfully deleted customer with id:");

        ResponseEntity<String> response=customersApi.deletedCustomers(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully deleted customer with id: "+id, response.getBody());

    }
}
