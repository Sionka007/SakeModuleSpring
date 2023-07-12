package grp.training.SaleModule.ProductsDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class OrderItemsDTO {
    private Integer id;
    @NotNull(message = "{orderItem.product.absent}")
    @Valid
    private ProductsDTO productsDTO;
    @NotNull(message = "{orderItem.quantity.absent}")
    @Min(value = 1, message = "{orderItem.quantity.invalid}")
    private Integer quantity;
    @NotNull(message = "{orderItem.price.absent}")
    private Double price;

    public OrderItemsDTO() {
    }

    public OrderItemsDTO(Integer id, ProductsDTO productsDTO, Integer quantity, Double price) {
        this.id = id;
        this.productsDTO = productsDTO;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemsDTO(ProductsDTO productsDTO, Integer quantity, Double price) {
        this.productsDTO = productsDTO;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductsDTO getProductsDTO() {
        return productsDTO;
    }

    public void setProductsDTO(ProductsDTO productsDTO) {
        this.productsDTO = productsDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItemsDTO{" +
                "id=" + id +
                ", productsDTO=" + productsDTO +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
