package unity;

import grp.training.SaleModule.ProductsDTO.ProductsDTO;
import grp.training.SaleModule.api.ProductApi;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductApiTest {
    @Mock
    ProductsService productsService;

    @Mock
    Environment environment;

    @InjectMocks
    ProductApi productApi;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetDetailsByProductId() throws SaleModuleException {
        Integer id=123;

        ProductsDTO productsDTO=new ProductsDTO(id, "Apple", 3.99f, "Sweet, green and tasty apple", 100);

        when(productsService.getDetailsByProductId(id)).thenReturn(productsDTO);

        ResponseEntity<ProductsDTO> response=productApi.getDetailsByProductId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsDTO, response.getBody());
    }

    @Test
    public void getProductsByName() throws SaleModuleException {
        String name="Apple";

        ProductsDTO productsDTO=new ProductsDTO(123, name, 3.99f, "Sweet, green and tasty apple", 100);
        List<ProductsDTO> dtoList=new ArrayList<>();

        for(int i=0; i<=5; i++){
            dtoList.add(productsDTO);
        }

        when(productsService.getDetailsByName(name)).thenReturn(dtoList);

        ResponseEntity<List<ProductsDTO>> response=productApi.getProductsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtoList, response.getBody());
    }

    @Test
    public void testGetDetailsByPrice() throws SaleModuleException {
        String price="3.99";

        ProductsDTO productsDTO=new ProductsDTO(123, "Apple", Float.parseFloat(price), "Sweet, green and tasty apple", 100);
        List<ProductsDTO> dtoList=new ArrayList<>();

        for(int i=0; i<=5; i++){
            dtoList.add(productsDTO);
        }

        when(productsService.getDetailsByPrice(Float.parseFloat(price))).thenReturn(dtoList);

        ResponseEntity<List<ProductsDTO>> response=productApi.getDetailsByPrice(price);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtoList, response.getBody());
    }

    @Test
    public void testAddProducts() throws SaleModuleException {
       Integer id=123;

        ProductsDTO productsDTO=new ProductsDTO(id, "Apple", 3.99f, "Sweet, green and tasty apple", 100);


        when(environment.getProperty("API.PRODUCT.INSERT_SUCCESS")).thenReturn("Successfully added new product");
        when(productsService.addProducts(productsDTO)).thenReturn(id);

        ResponseEntity<String> response=productApi.addProducts(productsDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Successfully added new product "+id, response.getBody());

        verify(productsService).addProducts(productsDTO);
    }
    @Test
    public void testUpdateProduct() throws SaleModuleException {
        Integer id=123;

        ProductsDTO productsDTO=new ProductsDTO(id, "Apple", 3.99f, "Sweet, green and tasty apple", 100);


        when(environment.getProperty("API.PRODUCT.UPDATE_SUCCESS")).thenReturn("Successfully update product with id:");

        ResponseEntity<String> response=productApi.updateProduct(id, productsDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully update product with id: "+id, response.getBody());

        verify(productsService).updateProduct(id, productsDTO);
    }

    @Test
    public void testDeleteProductById() throws SaleModuleException {
        Integer id=123;

        when(environment.getProperty("API.PRODUCT.DELETE_SUCCESS")).thenReturn("Successfully deleted product with id:");

        ResponseEntity<String> response=productApi.deleteProductById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully deleted product with id: "+id, response.getBody());

    }



}
