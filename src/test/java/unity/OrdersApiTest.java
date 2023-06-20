package unity;

import grp.training.SaleModule.ProductsDTO.*;
import grp.training.SaleModule.api.OrdersApi;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.service.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrdersApiTest {
    @Mock
    private OrdersService ordersService;
    @Mock
    private Environment environment;

    @InjectMocks
    private OrdersApi ordersApi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDetailsByStatus() throws SaleModuleException {
        OrderStatus orderStatus=OrderStatus.IN_PROGRESS;

        CustomersDTO customersDTO=new CustomersDTO(123, "Jon Snow", "Winterfell 123", "jon@snow", 123456789L);
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();

        for(int i=0; i<=5; i++){
            OrderItemsDTO orderItemsDTO=new OrderItemsDTO(123, new ProductsDTO(123, "Apple", 2.99f, "tasty apple", 100),
                    1, 2.99);
            orderItemsDTOList.add(orderItemsDTO);
        }

        OrdersDTO ordersDTO=new OrdersDTO(123, customersDTO, 1000.0, orderStatus, orderItemsDTOList, LocalDate.now());

        List<OrdersDTO> ordersist=new ArrayList<>();
        for(int i=0; i<=5; i++){
            ordersist.add(ordersDTO);
        }

        when(ordersService.getDetailsByStatus(orderStatus)).thenReturn(ordersist);

        ResponseEntity<List<OrdersDTO>> response=ordersApi.getDetailsByStatus(orderStatus.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersist, response.getBody());

    }

    @Test
    public void testGetDetailsByOrderDate() throws SaleModuleException {
        LocalDate date=LocalDate.now();

        CustomersDTO customersDTO=new CustomersDTO(123, "Jon Snow", "Winterfell 123", "jon@snow", 123456789L);
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();

        for(int i=0; i<=5; i++){
            OrderItemsDTO orderItemsDTO=new OrderItemsDTO(123, new ProductsDTO(123, "Apple", 2.99f, "tasty apple", 100),
                    1, 2.99);
            orderItemsDTOList.add(orderItemsDTO);
        }

        OrdersDTO ordersDTO=new OrdersDTO(123, customersDTO, 1000.0, OrderStatus.ORDER, orderItemsDTOList, date);

        List<OrdersDTO> ordersist=new ArrayList<>();
        for(int i=0; i<=5; i++){
            ordersist.add(ordersDTO);
        }

        when(ordersService.getDetailsByOrderDate(date)).thenReturn(ordersist);

        ResponseEntity<List<OrdersDTO>> response=ordersApi.getDetailsByOrderDate(date.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersist, response.getBody());

    }

    @Test
    public void testGetDetailsById() throws SaleModuleException {
        Integer id=123;

        CustomersDTO customersDTO=new CustomersDTO(123, "Jon Snow", "Winterfell 123", "jon@snow", 123456789L);
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();

        for(int i=0; i<=5; i++){
            OrderItemsDTO orderItemsDTO=new OrderItemsDTO(123, new ProductsDTO(123, "Apple", 2.99f, "tasty apple", 100),
                    1, 2.99);
            orderItemsDTOList.add(orderItemsDTO);
        }

        OrdersDTO ordersDTO=new OrdersDTO(id, customersDTO, 1000.0, OrderStatus.ORDER, orderItemsDTOList, LocalDate.now());

        when(ordersService.getDetailsById(id)).thenReturn(ordersDTO);

        ResponseEntity<OrdersDTO> response=ordersApi.getDetailsById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersDTO, response.getBody());

    }

    @Test
    public void testAddOrder() throws SaleModuleException {
        Integer id=123;

        CustomersDTO customersDTO=new CustomersDTO(123, "Jon Snow", "Winterfell 123", "jon@snow", 123456789L);
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();

        for(int i=0; i<=5; i++){
            OrderItemsDTO orderItemsDTO=new OrderItemsDTO(123, new ProductsDTO(123, "Apple", 2.99f, "tasty apple", 100),
                    1, 2.99);
            orderItemsDTOList.add(orderItemsDTO);
        }

        OrdersDTO ordersDTO=new OrdersDTO(id, customersDTO, 1000.0, OrderStatus.ORDER, orderItemsDTOList, LocalDate.now());

        when(environment.getProperty("API.ORDER.INSERT_SUCCESS")).thenReturn("Successfully added new order with id:");
        when(ordersService.addOrder(ordersDTO)).thenReturn(id);

        ResponseEntity<String> response=ordersApi.addOrder(ordersDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Successfully added new order with id: "+id, response.getBody());
        verify(ordersService).addOrder(ordersDTO);
    }

    @Test
    public void testUpdateOrder() throws SaleModuleException {
        Integer id=123;

        CustomersDTO customersDTO=new CustomersDTO(123, "Jon Snow", "Winterfell 123", "jon@snow", 123456789L);
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();

        for(int i=0; i<=5; i++){
            OrderItemsDTO orderItemsDTO=new OrderItemsDTO(123, new ProductsDTO(123, "Apple", 2.99f, "tasty apple", 100),
                    1, 2.99);
            orderItemsDTOList.add(orderItemsDTO);
        }

        OrdersDTO ordersDTO=new OrdersDTO(id, customersDTO, 1000.0, OrderStatus.ORDER, orderItemsDTOList, LocalDate.now());

        when(environment.getProperty("API.ORDER.UPDATE_SUCCESS")).thenReturn("Successfully update order with id:");

        ResponseEntity<String> response=ordersApi.updateOrder(id, ordersDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully update order with id: " +id, response.getBody());
    }

    @Test
    public void testDeleteOrder() throws SaleModuleException {
        Integer id=123;

        when(environment.getProperty("API.ORDER.DELETE_SUCCESS")).thenReturn("Successfully deleted order with id:");

        ResponseEntity<String> response=ordersApi.deleteOrder(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully deleted order with id: " +id, response.getBody());
    }
}
