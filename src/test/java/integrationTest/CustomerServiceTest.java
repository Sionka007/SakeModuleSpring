package integrationTest;

import grp.training.SaleModule.entity.Customers;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.repository.CustomersRepository;
import grp.training.SaleModule.service.CustomerService;
import grp.training.SaleModule.service.CustomersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Mock
    CustomersRepository customersRepository;


    @InjectMocks
    CustomerService customerService=new CustomersServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void invalidNameTest(){
        String name="Mike Luke";

        String invalidName="Mike Lu";

        Customers customers=new Customers();
        customers.setName(name);
        customers.setAddress("Milky way 123");
        customers.setEmail("mike@luke.com");
        customers.setPhone(123456789L);
        customers.setCustId(123);

        List<Customers> customersList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            customersList.add(customers);
        }

        when(customersRepository.findByName(name)).thenReturn(customersList);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->customerService.getDetailsByName(invalidName));
        Assertions.assertEquals("Service.CUSTOMER_NOT_FOUND", exception.getMessage());

    }
    @Test
    public void invalidPhoneNumber(){
        Long phoneNo=123456789L;

        Long invalidPhoneNo=12345L;

        Customers customer=new Customers();
        customer.setName("Mike Luke");
        customer.setAddress("Milky way 123");
        customer.setEmail("mike@luke.com");
        customer.setPhone(phoneNo);
        customer.setCustId(123);

        when(customersRepository.findByPhone(phoneNo)).thenReturn(customer);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->customerService.getDetailsByPhone(invalidPhoneNo));
        Assertions.assertEquals("Service.CUSTOMER_NOT_FOUND", exception.getMessage());

    }
    @Test
    public void invalidCustomerId(){
        Integer id=123;

        Integer invalidId=12;

        Customers customer=new Customers();
        customer.setName("Mike Luke");
        customer.setAddress("Milky way 123");
        customer.setEmail("mike@luke.com");
        customer.setPhone(123456789L);
        customer.setCustId(id);

        Optional<Customers> optional=Optional.of(customer);
        when(customersRepository.findById(id)).thenReturn(optional);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->customerService.getDetailsById(invalidId));
        Assertions.assertEquals("Service.CUSTOMER_NOT_FOUND", exception.getMessage());

    }
}
