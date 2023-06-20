package grp.training.SaleModule.service;

import grp.training.SaleModule.ProductsDTO.OrderItemsDTO;
import grp.training.SaleModule.ProductsDTO.OrderStatus;
import grp.training.SaleModule.ProductsDTO.OrdersDTO;
import grp.training.SaleModule.exception.SaleModuleException;

import java.time.LocalDate;
import java.util.List;

public interface OrdersService {
    public List<OrdersDTO> getDetailsByStatus(OrderStatus status) throws SaleModuleException;
    public List<OrdersDTO> getDetailsByCustomerName(String name) throws SaleModuleException;
    public List<OrdersDTO> getDetailsByOrderDate(LocalDate orderDate) throws SaleModuleException;
    public OrdersDTO getDetailsById(Integer id) throws SaleModuleException;
    public Integer addOrder(OrdersDTO ordersDTO);
    public void updateOrder(Integer orderId, OrdersDTO ordersDTO) throws SaleModuleException;
    public String deletedOrder(Integer id) throws SaleModuleException;
    public List<OrderItemsDTO> getDetailsOfOrderItemsByOrder(Integer orderId) throws SaleModuleException;
    public Integer addItem(OrderItemsDTO orderItemsDTO, Integer orderId) throws SaleModuleException;
    public String delateItem(Integer itemId, Integer orderId) throws SaleModuleException;

}
