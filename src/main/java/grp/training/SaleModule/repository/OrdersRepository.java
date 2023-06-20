package grp.training.SaleModule.repository;

import grp.training.SaleModule.Entity.OrderItems;
import grp.training.SaleModule.Entity.Orders;
import grp.training.SaleModule.ProductsDTO.OrderStatus;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    List<Orders> findByStatus(OrderStatus status);

    @Query("select o from orders o where o.customer_id in (Select c.cust_id from Customers c where c.name in ?1);")
    List<Orders> findByCustomersName(String name);

    List<Orders> findByOrderDate(LocalDate date);



}
