package org.kol.ProductService.controller;

import org.kol.ProductService.entity.Product;
import org.kol.ProductService.response.ProductResponse;
import org.kol.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct (@RequestBody Product product){

        ProductResponse productResponse=productService.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);

    }


    @PutMapping("/update/{Id}")
    public ResponseEntity<ProductResponse> updateProduct ( @PathVariable ("Id") Long productId, @RequestBody Product product){

        ProductResponse productResponse=productService.updateProducts(productId,product );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productResponse);
    }

    @GetMapping("/fetch-product-by-id/{Id}")
    public ResponseEntity<ProductResponse> getProductById (@PathVariable("Id") Long productId){

        ProductResponse productResponse=productService.findProductById(productId);

        return ResponseEntity.status(HttpStatus.FOUND).body(productResponse);

    }


    @GetMapping("/fetch-all")
    public  ResponseEntity<List<ProductResponse>>getAllProduct (){

      List<ProductResponse> productResponse=  productService.findAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productResponse);

    }


    @DeleteMapping("del/{Id}")
    public String DeleteProductById (@PathVariable ("Id") Long productId){

       String deleteMsg= productService.DeleteOrder(productId);

        return deleteMsg;
    }

}
