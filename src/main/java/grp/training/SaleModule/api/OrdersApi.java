package grp.training.SaleModule.api;

import grp.training.SaleModule.ProductsDTO.OrderStatus;
import grp.training.SaleModule.ProductsDTO.OrdersDTO;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Validated
public class OrdersApi {
    @Autowired
    OrdersService ordersService;
    @Autowired
    Environment environment;

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<OrdersDTO>> getDetailsByStatus(@PathVariable String status) throws SaleModuleException{
        OrderStatus orderStatus=Enum.valueOf(OrderStatus.class, status);
        return new ResponseEntity<>(ordersService.getDetailsByStatus(orderStatus), HttpStatus.OK);
    }

    @GetMapping(value = "/customer/name/{name}")
    public ResponseEntity<List<OrdersDTO>> getDetailsByCustomerName(@PathVariable  String name) throws SaleModuleException{
        return new ResponseEntity<>(ordersService.getDetailsByCustomerName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/date/{orderDate}")
    public ResponseEntity<List<OrdersDTO>> getDetailsByOrderDate(@PathVariable String orderDate) throws SaleModuleException{
        LocalDate date=LocalDate.parse(orderDate);
        return new ResponseEntity<>(ordersService.getDetailsByOrderDate(date), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{orderId}")
    public ResponseEntity<OrdersDTO> getDetailsById(@PathVariable Integer orderId) throws SaleModuleException{
        return new ResponseEntity<>(ordersService.getDetailsById(orderId), HttpStatus.OK);
    }

    @PostMapping(value = "/order")
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrdersDTO ordersDTO) throws SaleModuleException{
        String msg=environment.getProperty("API.ORDER.INSERT_SUCCESS")+" "+ordersService.addOrder(ordersDTO);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping(value = "/order/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Integer orderId, @Valid @RequestBody OrdersDTO ordersDTO) throws SaleModuleException{
        ordersService.updateOrder(orderId, ordersDTO);
        String msg=environment.getProperty("API.ORDER.UPDATE_SUCCESS") +" "+ orderId;

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId) throws SaleModuleException{
        ordersService.deletedOrder(orderId);
        String msg=environment.getProperty("API.ORDER.DELETE_SUCCESS") +" "+ orderId;

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
