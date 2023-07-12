package grp.training.SaleModule.service;

import grp.training.SaleModule.entity.Customers;
import grp.training.SaleModule.entity.OrderItems;
import grp.training.SaleModule.entity.Orders;
import grp.training.SaleModule.entity.Products;
import grp.training.SaleModule.ProductsDTO.*;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.repository.CustomersRepository;
import grp.training.SaleModule.repository.OrderItemsRepository;
import grp.training.SaleModule.repository.OrdersRepository;
import grp.training.SaleModule.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "ordersService")
@Transactional
public class OrdersServiceImpl  implements OrdersService{
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    Environment environment;
    @Override
    public List<OrdersDTO> getDetailsByStatus(OrderStatus status) throws SaleModuleException {
        List<Orders> orders=ordersRepository.findByStatus(status);

        if(orders.isEmpty()){
            throw new SaleModuleException("Service.ORDERS_NOT_FOUND");
        }else{
            List<OrdersDTO> ordersDTOList =new ArrayList<>();
            orders.forEach(o->{
                List<OrderItemsDTO> orderItemsDTOList=new ArrayList<>();
                o.getOrderItems().forEach(i->{
                    ProductsDTO productDTO=new ProductsDTO(i.getProducts().getName(), i.getProducts().getPrice(), i.getProducts().getDescription(), i.getProducts().getQuantity());
                    OrderItemsDTO itemsDTO = new OrderItemsDTO(productDTO, i.getQuantity(), i.getPrice());
                    orderItemsDTOList.add(itemsDTO);
                });
                Customers customer=o.getCustomers();
                CustomersDTO customerDTO=new CustomersDTO(customer.getId(), customer.getName(),
                        customer.getAddress(), customer.getEmail(), customer.getPhone());
                OrdersDTO orderDTO=new OrdersDTO(o.getOrderId(), customerDTO, o.getTotal(), o.getStatus(), orderItemsDTOList, o.getOrderDate());
                ordersDTOList.add(orderDTO);
            });
            return ordersDTOList;
        }
    }

    @Override
    public List<OrdersDTO> getDetailsByCustomerName(String name) throws SaleModuleException {
        List<Orders> orders=ordersRepository.findByCustomersName(name);
        if(orders.isEmpty()){
            throw new SaleModuleException("Service.ORDERS_NOT_FOUND");
        }else{
            List<OrdersDTO> ordersDTOList=new ArrayList<>();
            orders.forEach(o->{
                List<OrderItemsDTO> orderItemsDTOList=new ArrayList<>();
                o.getOrderItems().forEach(i->{
                    ProductsDTO productDTO=new ProductsDTO(i.getProducts().getName(), i.getProducts().getPrice(), i.getProducts().getDescription(), i.getProducts().getQuantity());
                    OrderItemsDTO itemsDTO = new OrderItemsDTO(productDTO, i.getQuantity(), i.getPrice());
                    orderItemsDTOList.add(itemsDTO);
                });
                Customers customer=o.getCustomers();
                CustomersDTO customerDTO=new CustomersDTO(customer.getId(), customer.getName(),
                        customer.getAddress(), customer.getEmail(), customer.getPhone());
                OrdersDTO orderDTO=new OrdersDTO(o.getOrderId(), customerDTO, o.getTotal(), o.getStatus(), orderItemsDTOList, o.getOrderDate());
                ordersDTOList.add(orderDTO);
            });
            return ordersDTOList;
        }
    }

    @Override
    public List<OrdersDTO> getDetailsByOrderDate(LocalDate orderDate) throws SaleModuleException {
        List<Orders> ordersList=ordersRepository.findByOrderDate(orderDate);
        if(ordersList.isEmpty()){
            throw new SaleModuleException("Service.ORDERS_NOT_FOUND");
        }else{
            List<OrdersDTO> ordersDTOList=new ArrayList<>();
            ordersList.forEach(o->{
                List<OrderItemsDTO> orderItemsDTOList=new ArrayList<>();
                o.getOrderItems().forEach(i->{
                    ProductsDTO productDTO=new ProductsDTO(i.getProducts().getName(), i.getProducts().getPrice(), i.getProducts().getDescription(), i.getProducts().getQuantity());
                    OrderItemsDTO itemsDTO = new OrderItemsDTO(productDTO, i.getQuantity(), i.getPrice());
                    orderItemsDTOList.add(itemsDTO);
                });
                Customers customer=o.getCustomers();
                CustomersDTO customerDTO=new CustomersDTO(customer.getId(), customer.getName(),
                        customer.getAddress(), customer.getEmail(), customer.getPhone());
                OrdersDTO orderDTO=new OrdersDTO(o.getOrderId(), customerDTO, o.getTotal(), o.getStatus(), orderItemsDTOList, o.getOrderDate());
                ordersDTOList.add(orderDTO);
            });
            return ordersDTOList;
        }
    }

    @Override
    public OrdersDTO getDetailsById(Integer id) throws SaleModuleException {
        Optional<Orders> optional=ordersRepository.findById(id);
        Orders order=optional.orElseThrow(()->new SaleModuleException("Service.ORDER_NOT_FOUND"));
        CustomersDTO customerDTO=new CustomersDTO(order.getCustomers().getId(), order.getCustomers().getName(),
                order.getCustomers().getAddress(), order.getCustomers().getEmail(), order.getCustomers().getPhone());
        List<OrderItemsDTO> orderItemsDTOList=new ArrayList<>();
        order.getOrderItems().forEach(i->{
            ProductsDTO productDTO=new ProductsDTO(i.getProducts().getName(), i.getProducts().getPrice(), i.getProducts().getDescription(), i.getProducts().getQuantity());
            OrderItemsDTO itemsDTO = new OrderItemsDTO(productDTO, i.getQuantity(), i.getPrice());
            orderItemsDTOList.add(itemsDTO);
        });
        return new OrdersDTO(order.getOrderId(), customerDTO, order.getTotal(), order.getStatus(), orderItemsDTOList, order.getOrderDate());
    }

    @Override
    public Integer addOrder(OrdersDTO ordersDTO){
        Orders order=new Orders();
        Customers customers=customersRepository.findByPhone(ordersDTO.getCustomers().getPhone());
        if(customers==null){
            Customers newCustomers=new Customers();
            newCustomers.setPhone(ordersDTO.getCustomers().getPhone());
            newCustomers.setEmail(ordersDTO.getCustomers().getEmail());
            newCustomers.setName(ordersDTO.getCustomers().getName());
            newCustomers.setAddress(ordersDTO.getCustomers().getAddress());
            customersRepository.save(newCustomers);
            customers= newCustomers;
        }

        order.setOrderDate(LocalDate.now());
        order.setCustomers(customers);
        order.setStatus(ordersDTO.getStatus());
        order.setTotal(ordersDTO.getTotal());

        ordersRepository.save(order);
        return order.getOrderId();
    }

    @Override
    public void updateOrder(Integer orderId, OrdersDTO ordersDTO) throws SaleModuleException {
        Optional<Orders> optional=ordersRepository.findById(orderId);
        Orders order=optional.orElseThrow(()->new SaleModuleException("Service.ORDER_NOT_FOUND"));

        order.setTotal(ordersDTO.getTotal());
        order.setStatus(ordersDTO.getStatus());
        order.setOrderDate(ordersDTO.getOrderDate());
    }

    @Override
    public String deletedOrder(Integer id) throws SaleModuleException{
    Optional<Orders> optional=ordersRepository.findById(id);
    Orders order=optional.orElseThrow(() -> new SaleModuleException("Service.ORDER_NOT_FOUND"));

    ordersRepository.delete(order);


    return "Successfully deleted";
    }

    @Override
    public List<OrderItemsDTO> getDetailsOfOrderItemsByOrder(Integer orderId) throws SaleModuleException {
        Optional<Orders> optional=ordersRepository.findById(orderId);
        Orders order=optional.orElseThrow(()->new SaleModuleException("Service.ORDER_NOT_FOUND"));

        List<OrderItems> orderItemsList=order.getOrderItems();

        if(orderItemsList.isEmpty()){
            throw new SaleModuleException("Service.ITEMS_NOT_FOUND");
        }else{
            List<OrderItemsDTO> dtoList=new ArrayList<>();
            orderItemsList.forEach(o->{
                ProductsDTO productsDTO=new ProductsDTO(o.getProducts().getPId(), o.getProducts().getName(), o.getProducts().getPrice(),
                        o.getProducts().getDescription(), o.getProducts().getQuantity());
                OrderItemsDTO itemDTO=new OrderItemsDTO(o.getId(), productsDTO, o.getQuantity(), o.getPrice());
                dtoList.add(itemDTO);
             });
            return dtoList;
        }
    }

    @Override
    public Integer addItem(OrderItemsDTO orderItemsDTO, Integer orderId) throws SaleModuleException {
        Optional<Orders> optional1=ordersRepository.findById(orderId);
        Orders order=optional1.orElseThrow(()->new SaleModuleException("Service.ORDER_NOT_FOUND"));

        Optional<Products> optional2=productRepository.findById(orderItemsDTO.getProductsDTO().getId());
        Products products=optional2.orElseThrow(()->new SaleModuleException("Service.PRODUCT_NOT_FOUND"));

        OrderItems orderItems=new OrderItems();
        orderItems.setPrice(orderItemsDTO.getPrice());
        orderItems.setProducts(products);
        orderItems.setQuantity(orderItemsDTO.getQuantity());

        if(products.getQuantity()>=orderItems.getQuantity()){
            products.setQuantity(products.getQuantity()-orderItems.getQuantity());
            productRepository.save(products);

        }else{
            throw new SaleModuleException("Not enough products in magazine. Product quantity: "+products.getQuantity());
        }

        order.setTotal(order.getTotal()+(orderItems.getPrice()*orderItems.getQuantity()));


        List<OrderItems> itemsList=order.getOrderItems();
        itemsList.add(orderItems);
        ordersRepository.save(order);

        return orderItems.getId();
    }

    @Override
    public String delateItem(Integer itemId,Integer orderId) throws SaleModuleException {
        Optional<OrderItems> optional1=orderItemsRepository.findById(itemId);
        OrderItems orderItems= optional1.orElseThrow(()->new SaleModuleException("Service.ITEM_NOT_FOUND"));
        Optional<Orders> optional2=ordersRepository.findById(orderId);
        Orders order=optional2.orElseThrow(()->new SaleModuleException("Service.ORDER_NOT_FOUND"));

        List<OrderItems> itemsList=order.getOrderItems();


        List<OrderItems> newList=itemsList.stream().filter(item->item.getId()!=itemId).collect(Collectors.toList());

        newList.forEach(i->{
            order.setTotal(order.getTotal()+(i.getPrice()*i.getQuantity()));
        });
        order.setOrderItems(newList);
        order.setTotal(order.getTotal()-(orderItems.getQuantity()*orderItems.getPrice()));
        ordersRepository.save(order);


        Products products=orderItems.getProducts();
        products.setQuantity(products.getQuantity()+orderItems.getQuantity());
        productRepository.save(products);
        orderItems.setProducts(null);
        orderItemsRepository.deleteById(itemId);

        return "Service.DELETED";
    }
}