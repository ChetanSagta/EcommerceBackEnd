package ecommerce.controller;

import ecommerce.entity.Product;
import ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products/addProduct")
    public Product addProduct(@RequestBody final Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/products/getByTitle/{title}")
    public Product findOne(@PathVariable("title") String productTitle){
        return productService.findOneByTitle(productTitle);
    }

    @GetMapping("/products/getAll")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("/products/updateProduct/{title}")
    public Product updateProduct(@PathVariable("title") String productTitle, @RequestBody Product product){
        return productService.updateProduct(productTitle,product);
    }

    @PostMapping("/products/deleteProduct/{title}")
    public String deleteProduct(@PathVariable("title") String productTitle){
        productService.deleteProduct(productTitle);
        return String.format("Product with name : %s has been deleted",productTitle);
    }
}