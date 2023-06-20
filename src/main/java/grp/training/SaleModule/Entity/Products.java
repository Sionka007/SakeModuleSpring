package grp.training.SaleModule.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer pId;
    private String name;
    private Float price;
    private String description;
    private Integer quantity;


    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
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

    public void setPrice(Float prize) {
        this.price = prize;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products products)) return false;
        return Objects.equals(pId, products.pId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId);
    }

    @Override
    public String toString() {
        return "Products{" +
                "Id=" + pId +
                ", Name='" + name + '\'' +
                ", Prize=" + price +
                ", Description='" + description + '\'' +
                ", Quantity=" + quantity +
                '}';
    }
}
