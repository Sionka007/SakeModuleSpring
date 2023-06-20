package grp.training.SaleModule.repository;

import grp.training.SaleModule.Entity.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {
    List<Customers> findByName(String name);
    Customers findByPhone(Long phoneNo);
}
