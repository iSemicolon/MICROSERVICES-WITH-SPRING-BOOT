package org.kol.OrderService.feignClient;

import org.kol.OrderService.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Product-Service", url = "http://localhost:8081/product")
public interface ProductFeign {

        @GetMapping("fetch-product-by-id/{Id}")
        public ProductResponse getProductById (@PathVariable("Id") Long productId);

//    @GetMapping("testy/{Id}")
//    public  String testyProduct (@PathVariable("Id") Long productId);

        @GetMapping("/fetch-all")
        public List<ProductResponse> getAllProduct ();

//    @PostMapping("/add")
//    public addProduct (@RequestBody Product product);

//
//    @PutMapping("/update/{Id}")
//    public ProductResponse updateProduct ( @PathVariable ("Id") Long productId, @RequestBody Product product);

}
