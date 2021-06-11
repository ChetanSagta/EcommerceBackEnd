package ecommerce.repositories;

import ecommerce.entity.CartItem;
import ecommerce.entity.Product;
import ecommerce.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    CartItem findCartItemByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);

}
