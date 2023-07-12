package grp.training.SaleModule.repository;

import grp.training.SaleModule.entity.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Products, Integer> {
 List<Products> findByName(String name);
 List<Products> findByPrice(Float price);
}
