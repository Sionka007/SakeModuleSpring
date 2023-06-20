package grp.training.SaleModule.repository;

import grp.training.SaleModule.Entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Integer> {
}
