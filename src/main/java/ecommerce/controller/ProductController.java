package ecommerce.controller;

import ecommerce.entity.Product;
import ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/api/products/addProduct")
    public Product addProduct(@RequestBody final Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/api/products/getByTitle/{title}")
    public Product findOne(@PathVariable("title") String productTitle){
        return productService.findOneByTitle(productTitle);
    }

    @GetMapping("/api/products/getAll")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("/api/products/updateProduct/{title}")
    public Product updateProduct(@PathVariable("title") String productTitle, @RequestBody Product product){
        return productService.updateProduct(productTitle,product);
    }

    @PostMapping("/api/products/deleteProduct/{title}")
    public String deleteProduct(@PathVariable("title") String productTitle){
        productService.deleteProduct(productTitle);
        return String.format("Product with name : {} has been deleted",productTitle);
    }
}
