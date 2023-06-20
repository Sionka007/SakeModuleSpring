package grp.training.SaleModule.ProductsDTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductsDTO {

    private  Integer id;
    @NotNull(message = "{products.name.absent}")
    @NotBlank(message = "{products.name.absent}")
    private String name;
    @NotNull(message = "{products.price.absent}")
    private Float price;

    @NotNull(message = "{products.description.absent}")
    @NotBlank(message = "{products.description.absent}")
    private String description;
    @NotNull(message = "{products.quantity.absent}")
    @Min(value = 1, message = "{products.quantity.invalid}")
    private Integer quantity;

    public ProductsDTO() {
        super();
    }

    public ProductsDTO(Integer id, String name, Float price, String description, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public ProductsDTO(String name, Float price, String description, Integer quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
