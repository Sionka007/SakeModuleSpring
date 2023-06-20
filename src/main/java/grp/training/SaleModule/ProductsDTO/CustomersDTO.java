package grp.training.SaleModule.ProductsDTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomersDTO {

    private Integer id;
    @NotNull(message = "{customer.name.absent}")
    @Pattern(regexp = "[A-Za-z0-9]+( [A-Za-z0-9]+)*", message ="{customer.name.invalid}")
    private String name;
    @NotNull(message = "{customer.address.absent}")
    private String address;
    @NotNull(message = "{customer.email.absent}")
    @Email(message = "{customer.email.invalid}")
    private String email;
    @NotNull(message = "{customer.phoneNumber.absent}")
    @Digits(integer = 9, fraction = 0, message = "{customer.phoneNumber.invalid}")
    private Long phone;
    public CustomersDTO() {
    }

    public CustomersDTO(Integer id, String name, String address, String email, Long phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public CustomersDTO(String name, String address, String email, Long phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
