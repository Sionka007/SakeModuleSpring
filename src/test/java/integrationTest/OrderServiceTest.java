package integrationTest;

import grp.training.SaleModule.entity.Customers;
import grp.training.SaleModule.entity.OrderItems;
import grp.training.SaleModule.entity.Orders;
import grp.training.SaleModule.entity.Products;
import grp.training.SaleModule.ProductsDTO.OrderStatus;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.repository.CustomersRepository;
import grp.training.SaleModule.repository.OrderItemsRepository;
import grp.training.SaleModule.repository.OrdersRepository;
import grp.training.SaleModule.repository.ProductRepository;
import grp.training.SaleModule.service.OrdersService;
import grp.training.SaleModule.service.OrdersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    OrdersRepository ordersRepository;
    @Mock
    CustomersRepository customersRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderItemsRepository orderItemsRepository;
    @InjectMocks
    OrdersService ordersService=new OrdersServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void invalidStatus(){
        OrderStatus status=OrderStatus.ORDER, invalidStatus=OrderStatus.IN_PROGRESS;

        Customers customers=new Customers();
        customers.setCustId(123);
        customers.setEmail("john@snow.com");
        customers.setAddress("Winterfell 123");
        customers.setPhone(123456789L);
        customers.setName("John Snow");

        Products products=new Products();
        products.setPId(123);
        products.setName("Apple");
        products.setPrice(3.99f);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        OrderItems orderItems=new OrderItems();
        orderItems.setId(123);
        orderItems.setProducts(products);
        orderItems.setQuantity(1);
        orderItems.setPrice(2.99);
        List<OrderItems> orderItemsList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            orderItemsList.add(orderItems);
        }

        Orders order=new Orders();
        order.setOrderId(123);
        order.setStatus(status);
        order.setOrderDate(LocalDate.now());
        order.setTotal(10.0);
        order.setOrderItems(orderItemsList);
        order.setCustomers(customers);

        List<Orders> ordersList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            ordersList.add(order);
        }

        when(ordersRepository.findByStatus(status)).thenReturn(ordersList);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->ordersService.getDetailsByStatus(invalidStatus));

        Assertions.assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());

    }
    @Test
    public void invalidCustomerName(){
        String custName="John Snow", invalidCustName="Joh Sno";

        Customers customers=new Customers();
        customers.setCustId(123);
        customers.setEmail("john@snow.com");
        customers.setAddress("Winterfell 123");
        customers.setPhone(123456789L);
        customers.setName(custName);

        Products products=new Products();
        products.setPId(123);
        products.setName("Apple");
        products.setPrice(3.99f);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        OrderItems orderItems=new OrderItems();
        orderItems.setId(123);
        orderItems.setProducts(products);
        orderItems.setQuantity(1);
        orderItems.setPrice(2.99);
        List<OrderItems> orderItemsList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            orderItemsList.add(orderItems);
        }

        Orders order=new Orders();
        order.setOrderId(123);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDate.now());
        order.setTotal(10.0);
        order.setOrderItems(orderItemsList);
        order.setCustomers(customers);

        List<Orders> ordersList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            ordersList.add(order);
        }

        when(ordersRepository.findByCustomersName(custName)).thenReturn(ordersList);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->ordersService.getDetailsByCustomerName(invalidCustName));

        Assertions.assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());

    }
    @Test
    public void invalidOrderDate(){
        LocalDate date=LocalDate.now(), invalidDate=LocalDate.now().plusDays(5);

        Customers customers=new Customers();
        customers.setCustId(123);
        customers.setEmail("john@snow.com");
        customers.setAddress("Winterfell 123");
        customers.setPhone(123456789L);
        customers.setName("John Snow");

        Products products=new Products();
        products.setPId(123);
        products.setName("Apple");
        products.setPrice(3.99f);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        OrderItems orderItems=new OrderItems();
        orderItems.setId(123);
        orderItems.setProducts(products);
        orderItems.setQuantity(1);
        orderItems.setPrice(2.99);
        List<OrderItems> orderItemsList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            orderItemsList.add(orderItems);
        }

        Orders order=new Orders();
        order.setOrderId(123);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(date);
        order.setTotal(10.0);
        order.setOrderItems(orderItemsList);
        order.setCustomers(customers);

        List<Orders> ordersList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            ordersList.add(order);
        }

        when(ordersRepository.findByOrderDate(date)).thenReturn(ordersList);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->ordersService.getDetailsByOrderDate(invalidDate));

        Assertions.assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());

    }

    @Test
    public void invalidOrderId(){
        Integer id=123, invalidID=12;

        Customers customers=new Customers();
        customers.setCustId(123);
        customers.setEmail("john@snow.com");
        customers.setAddress("Winterfell 123");
        customers.setPhone(123456789L);
        customers.setName("John Snow");

        Products products=new Products();
        products.setPId(123);
        products.setName("Apple");
        products.setPrice(3.99f);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        OrderItems orderItems=new OrderItems();
        orderItems.setId(123);
        orderItems.setProducts(products);
        orderItems.setQuantity(1);
        orderItems.setPrice(2.99);
        List<OrderItems> orderItemsList=new ArrayList<>();
        for(int i=0; i<=5; i++){
            orderItemsList.add(orderItems);
        }

        Orders order=new Orders();
        order.setOrderId(id);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDate.now());
        order.setTotal(10.0);
        order.setOrderItems(orderItemsList);
        order.setCustomers(customers);

        Optional<Orders> optional=Optional.of(order);
        when(ordersRepository.findById(id)).thenReturn(optional);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->ordersService.getDetailsById(invalidID));

        Assertions.assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());

    }
    @Test
    public void invalidOrderItemsByOrder(){
        Integer id=123;

        Customers customers=new Customers();
        customers.setCustId(123);
        customers.setEmail("john@snow.com");
        customers.setAddress("Winterfell 123");
        customers.setPhone(123456789L);
        customers.setName("John Snow");

        Orders order=new Orders();
        order.setOrderId(id);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDate.now());
        order.setTotal(10.0);
        order.setOrderItems(new ArrayList<>());
        order.setCustomers(customers);

        Optional<Orders> optional=Optional.of(order);
        when(ordersRepository.findById(id)).thenReturn(optional);

        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->ordersService.getDetailsOfOrderItemsByOrder(id));

        Assertions.assertEquals("Service.ITEMS_NOT_FOUND", exception.getMessage());

    }



}
