package grp.training.SaleModule.service;

import grp.training.SaleModule.Entity.Customers;
import grp.training.SaleModule.ProductsDTO.CustomersDTO;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.repository.CustomersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "customersService")
@Transactional
public class CustomersServiceImpl implements CustomerService{
    @Autowired
    CustomersRepository customersRepository;
    @Override
    public List<CustomersDTO> getDetailsByName(String name) throws SaleModuleException {
        List<Customers> customers=customersRepository.findByName(name);

        if(customers.isEmpty()){
            throw new SaleModuleException("Service.CUSTOMER_NOT_FOUND");
        }else{
            List<CustomersDTO> dtoList=new ArrayList<>();

            customers.forEach(c->{
                CustomersDTO customerDTO=new CustomersDTO(c.getId(), c.getName(), c.getAddress(),
                        c.getEmail(), c.getPhone());
                dtoList.add(customerDTO);
            });
            return dtoList;
        }
    }

    @Override
    public CustomersDTO getDetailsByPhone(Long phoneNo) throws SaleModuleException{
        Customers customer=customersRepository.findByPhone(phoneNo);

        if(customer==null){
            throw new SaleModuleException("Service.CUSTOMER_NOT_FOUND");
        }else{
            CustomersDTO customersDTO=new CustomersDTO(customer.getId(), customer.getName(),
                    customer.getAddress(), customer.getEmail(), customer.getPhone());
            return customersDTO;
        }
    }

    @Override
    public CustomersDTO getDetailsById(Integer custId) throws SaleModuleException {
        Optional<Customers> optional=customersRepository.findById(custId);
        Customers customer=optional.orElseThrow(() -> new SaleModuleException("Service.CUSTOMER_NOT_FOUND"));

        CustomersDTO customerDTO=new CustomersDTO(customer.getId(), customer.getName(), customer.getAddress(),
                customer.getEmail(), customer.getPhone());
        return customerDTO;
    }

    @Override
    public Integer addCustomers(CustomersDTO customersDTO) {
        Customers customers=new Customers();
        customers.setName(customersDTO.getName());
        customers.setAddress(customersDTO.getAddress());
        customers.setEmail(customersDTO.getEmail());
        customers.setPhone(customersDTO.getPhone());

        customersRepository.save(customers);
        return customers.getId();
    }

    @Override
    public void updateCustomer(Integer custId, CustomersDTO customersDTO) throws SaleModuleException {
        Optional<Customers> optional=customersRepository.findById(custId);
        Customers customer=optional.orElseThrow(()->new SaleModuleException("Service.CUSTOMER_NOT_FOUND"));

        customer.setAddress(customersDTO.getAddress());
        customer.setName(customersDTO.getName());
        customer.setEmail(customersDTO.getEmail());
        customer.setPhone(customersDTO.getPhone());
    }

    @Override
    public void deletedCustomers(Integer custId) throws SaleModuleException{
        Optional<Customers> optional=customersRepository.findById(custId);
        Customers customer=optional.orElseThrow(()->new SaleModuleException("Service.CUSTOMER_NOT_FOUND"));

        customersRepository.deleteById(custId);
    }
}
