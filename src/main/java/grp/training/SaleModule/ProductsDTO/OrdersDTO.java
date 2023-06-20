package grp.training.SaleModule.ProductsDTO;

import grp.training.SaleModule.Entity.Customers;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public class OrdersDTO {
    private Integer id;
    @NotNull(message = "{order.customer.absent}")
    @Valid
    private CustomersDTO customers;
    @NotNull(message = "{order.total.absent}")
    private Double total;

    @NotNull(message = "{order.status.absent}")
    private OrderStatus status;

    private List<OrderItemsDTO> orderItemsDTO;

    private LocalDate orderDate;

    public OrdersDTO() {
    }

    public OrdersDTO(Integer id, CustomersDTO customers, Double total, OrderStatus status, List<OrderItemsDTO> orderItemsDTO,
                     LocalDate orderDate) {
        this.id = id;
        this.customers = customers;
        this.total = total;
        this.status = status;
        this.orderItemsDTO=orderItemsDTO;
        this.orderDate=orderDate;
    }

    public OrdersDTO(CustomersDTO customers, Double total, OrderStatus status, List<OrderItemsDTO> orderItemsDTO) {
        this.customers = customers;
        this.total = total;
        this.status = status;
        this.orderItemsDTO=orderItemsDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomersDTO getCustomers() {
        return customers;
    }

    public void setCustomers(CustomersDTO customers) {
        this.customers = customers;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemsDTO> getOrderItemsDTO() {
        return orderItemsDTO;
    }

    public void setOrderItemsDTO(List<OrderItemsDTO> orderItemsDTO) {
        this.orderItemsDTO = orderItemsDTO;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrdersDTO" +
                "id=" + id +
                ", customers=" + customers.getId() +
                ", total=" + total +
                ", status=" + status;
    }
}
