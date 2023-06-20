package integrationTest;


import grp.training.SaleModule.Entity.Products;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.repository.ProductRepository;
import grp.training.SaleModule.service.ProductsService;
import grp.training.SaleModule.service.ProductsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductsService productsService=new ProductsServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void invalidProductId() {
        Integer prodId=123, invalidProdId=12;

        Products products=new Products();
        products.setPId(prodId);
        products.setName("Apple");
        products.setPrice(3.99f);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        Optional<Products> optional=Optional.of(products);

        when(productRepository.findById(prodId)).thenReturn(optional);
        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->productsService.getDetailsByProductId(invalidProdId));

        Assertions.assertEquals("Service.PRODUCT_NOT_FOUND", exception.getMessage());

    }

    @Test
    public void invalidProductName() {
        String prodName="Apple", invalidProdName="Appl";

        Products products=new Products();
        products.setPId(123);
        products.setName(prodName);
        products.setPrice(3.99f);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        List<Products> list=new ArrayList<>();
        for(int i=0; i<=5; i++){
            list.add(products);
        }

        when(productRepository.findByName(prodName)).thenReturn(list);
        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->productsService.getDetailsByName(invalidProdName));

        Assertions.assertEquals("Service.PRODUCTS_NOT_FOUND", exception.getMessage());

    }
    @Test
    public void invalidProductPrice() {
        Float price=3.99f, invalidPrice=3.0f;

        Products products=new Products();
        products.setPId(123);
        products.setName("Apple");
        products.setPrice(price);
        products.setQuantity(100);
        products.setDescription("Tasty apple");

        List<Products> list=new ArrayList<>();
        for(int i=0; i<=5; i++){
            list.add(products);
        }

        when(productRepository.findByPrice(price)).thenReturn(list);
        SaleModuleException exception= Assertions.assertThrows(SaleModuleException.class,
                ()->productsService.getDetailsByPrice(invalidPrice));

        Assertions.assertEquals("Service.PRODUCTS_NOT_FOUND", exception.getMessage());

    }


}
