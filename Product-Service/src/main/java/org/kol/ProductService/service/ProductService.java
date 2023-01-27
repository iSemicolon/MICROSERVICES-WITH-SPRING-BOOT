package org.kol.ProductService.service;

import org.kol.ProductService.entity.Product;
import org.kol.ProductService.repository.ProductRepository;
import org.kol.ProductService.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public ProductResponse saveProduct(Product product) {
        Product saveProduct=  productRepository.save(product);

        ProductResponse saveProductResponse=modelMapper.map(saveProduct,ProductResponse.class);
      return saveProductResponse;
    }

    public ProductResponse updateProducts(Long productId, Product product) {

        Product savedProduct=productRepository.findById(productId).get();

       // savedProduct.setId(product.getId());
        savedProduct.setName(product.getName());
        savedProduct.setCategory(product.getCategory());


        Product updateProduct=productRepository.save(savedProduct);

        ProductResponse updateProductResponse=modelMapper.map(updateProduct,ProductResponse.class);

        return updateProductResponse;

    }

    public ProductResponse findProductById(Long productId) {

        Product findOrderById=productRepository.findById(productId).get();

        ProductResponse findProductResponse=modelMapper.map(findOrderById,ProductResponse.class);

        return findProductResponse;

    }

    public List<ProductResponse> findAllProducts() {

        List<Product> fetchAllProduct=productRepository.findAll();

       List<ProductResponse> productResponse = fetchAllProduct
               .stream()
               .map(productResponses -> modelMapper.map(productResponses, ProductResponse.class))
               .collect(Collectors.toList());

        return productResponse;

    }


    public String DeleteOrder(Long orderId) {


        productRepository. deleteById(orderId);

        return "Record Deleted";
    }
}
