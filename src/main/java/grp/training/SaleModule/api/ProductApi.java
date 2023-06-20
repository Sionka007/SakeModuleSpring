package grp.training.SaleModule.api;

import grp.training.SaleModule.ProductsDTO.ProductsDTO;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.service.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "products")
@Validated
public class ProductApi {
    @Autowired
    ProductsService productsService;
    @Autowired
    Environment environment;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ProductsDTO> getDetailsByProductId(@PathVariable Integer id) throws SaleModuleException{
        ProductsDTO productDTO=productsService.getDetailsByProductId(id);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<ProductsDTO>> getProductsByName(@PathVariable String name) throws SaleModuleException{
        List<ProductsDTO> dtoList=productsService.getDetailsByName(name);

        return new ResponseEntity<List<ProductsDTO>>(dtoList, HttpStatus.OK);
    }

    @GetMapping(value = "price/{price}")
    public ResponseEntity<List<ProductsDTO>> getDetailsByPrice(@PathVariable String price) throws SaleModuleException{
        Float priceToFloat=Float.parseFloat(price);
        List<ProductsDTO> dtoList=productsService.getDetailsByPrice(priceToFloat);

        return new ResponseEntity<List<ProductsDTO>>(dtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<String> addProducts(@Valid @RequestBody ProductsDTO productsDTO) throws SaleModuleException{
        Integer productId= productsService.addProducts(productsDTO);
        String msg=environment.getProperty("API.PRODUCT.INSERT_SUCCESS")+" "+productId;

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping(value = "/product/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer productId, @Valid @RequestBody ProductsDTO productsDTO) throws SaleModuleException{
        productsService.updateProduct(productId, productsDTO);
        String msg=environment.getProperty("API.PRODUCT.UPDATE_SUCCESS")+" "+productId;
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer productId) throws SaleModuleException{
        productsService.deleteProductById(productId);
        String msg=environment.getProperty("API.PRODUCT.DELETE_SUCCESS")+" "+productId;
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
