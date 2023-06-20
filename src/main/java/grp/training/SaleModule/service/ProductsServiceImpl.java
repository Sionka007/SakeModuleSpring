package grp.training.SaleModule.service;

import grp.training.SaleModule.Entity.Products;
import grp.training.SaleModule.ProductsDTO.ProductsDTO;
import grp.training.SaleModule.exception.SaleModuleException;
import grp.training.SaleModule.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "productService")
@Transactional
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductsDTO getDetailsByProductId(Integer id) throws SaleModuleException{
        Optional<Products> optional=productRepository.findById(id);
        Products product= optional.orElseThrow(() -> new SaleModuleException("Service.PRODUCT_NOT_FOUND"));

      ProductsDTO productsDTO=new ProductsDTO(product.getPId(), product.getName(), product.getPrice(),
                product.getDescription(), product.getQuantity());
        return productsDTO;
    }

    @Override
    public List<ProductsDTO> getDetailsByName(String name) throws SaleModuleException{
        List<Products> productsList =productRepository.findByName(name);

        if(productsList.isEmpty()){
            throw new SaleModuleException("Service.PRODUCTS_NOT_FOUND");
        }else{
            List<ProductsDTO> dtoList=new ArrayList<grp.training.SaleModule.ProductsDTO.ProductsDTO>();
            for(Products products: productsList){
                ProductsDTO productsDTO=new ProductsDTO();
                productsDTO.setId(products.getPId());
                productsDTO.setName(products.getName());
                productsDTO.setDescription(products.getDescription());
                productsDTO.setPrice(products.getPrice());
                productsDTO.setQuantity(products.getQuantity());

                dtoList.add(productsDTO);
            }
            return dtoList;
        }
    }

    @Override
    public List<ProductsDTO> getDetailsByPrice(Float price) throws SaleModuleException{
        List<Products> products=productRepository.findByPrice(price);

        if(products.isEmpty()){
            throw new SaleModuleException("Service.PRODUCTS_NOT_FOUND");
        }else{
            List<ProductsDTO> dtoList=new ArrayList<>();
            products.forEach((p)->{
               ProductsDTO productDTO=new ProductsDTO(p.getPId(), p.getName(), p.getPrice(),
                        p.getDescription(), p.getQuantity());
                dtoList.add(productDTO);
            });
            return dtoList;
        }
    }

    @Override
    public Integer addProducts(ProductsDTO productsDTO) {
        Products products=new Products();
        products.setName(productsDTO.getName());
        products.setDescription(productsDTO.getDescription());
        products.setPrice(productsDTO.getPrice());
        products.setQuantity(productsDTO.getQuantity());

        productRepository.save(products);
        return products.getPId();
    }

    @Override
    public void deleteProductById(Integer id) throws SaleModuleException{
        Optional<Products> optional=productRepository.findById(id);
        Products products=optional.orElseThrow(()->new SaleModuleException("Service.PRODUCT_NOT_FOUND"));

        productRepository.deleteById(id);

    }

    @Override
    public void updateProduct(Integer productId, ProductsDTO productsDTO) throws SaleModuleException {
        Optional<Products> optional=productRepository.findById(productId);
        Products product= optional.orElseThrow(()->new SaleModuleException("Service.PRODUCT_NOT_FOUND"));

        product.setQuantity(productsDTO.getQuantity());
        product.setDescription(productsDTO.getDescription());
        product.setName(productsDTO.getName());
        product.setPrice(productsDTO.getPrice());
    }


}
