package grp.training.SaleModule.service;

import grp.training.SaleModule.ProductsDTO.ProductsDTO;
import grp.training.SaleModule.exception.SaleModuleException;

import java.util.List;

public interface ProductsService {
    public ProductsDTO getDetailsByProductId(Integer id) throws SaleModuleException;
    public List<ProductsDTO> getDetailsByName(String name) throws SaleModuleException;
    public List<ProductsDTO> getDetailsByPrice(Float price) throws SaleModuleException;
    public Integer addProducts(ProductsDTO productsDTO);
    public void deleteProductById(Integer id) throws SaleModuleException;
    public void updateProduct(Integer productId, ProductsDTO productsDTO) throws SaleModuleException;
}
