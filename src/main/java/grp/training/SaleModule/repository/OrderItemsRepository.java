package grp.training.SaleModule.repository;

import grp.training.SaleModule.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Integer> {
}
