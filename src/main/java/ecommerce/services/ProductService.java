package ecommerce.services;

import ecommerce.entity.Product;
import ecommerce.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  ProductRepo productRepo;


  public Product addProduct(Product product) {
    return productRepo.save(product);
  }

  public Product findOneByTitle(String productTitle) {
    return productRepo.findByTitle(productTitle);
  }

  public List<Product> findAll() {
    return productRepo.findAll();
  }


  public Product updateProduct(String productTitle, Product product) {
    Product tempProduct = productRepo.findByTitle(productTitle);
    tempProduct.setCategory(product.getCategory());
    tempProduct.setDescription(product.getDescription());
    tempProduct.setPrice(product.getPrice());
    tempProduct.setTitle(product.getTitle());
    return productRepo.save(tempProduct);

  }

  public void deleteProduct(String productTitle) {
    Product product = productRepo.findByTitle(productTitle);
    productRepo.deleteById(product.getId());
  }
}
