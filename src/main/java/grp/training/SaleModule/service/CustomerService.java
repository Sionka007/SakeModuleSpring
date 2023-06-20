package grp.training.SaleModule.service;

import grp.training.SaleModule.Entity.Customers;
import grp.training.SaleModule.ProductsDTO.CustomersDTO;
import grp.training.SaleModule.exception.SaleModuleException;

import java.util.List;

public interface CustomerService {
    public List<CustomersDTO> getDetailsByName(String name) throws SaleModuleException;
    public CustomersDTO getDetailsByPhone(Long phoneNo)throws SaleModuleException;
    public CustomersDTO getDetailsById(Integer custId) throws SaleModuleException;
    public Integer addCustomers(CustomersDTO customersDTO);
    public void updateCustomer(Integer custId, CustomersDTO customersDTO) throws SaleModuleException;
    public void deletedCustomers(Integer custId) throws SaleModuleException;


}
