package ecommerce.repositories;

import ecommerce.entity.CartItem;
import ecommerce.entity.Product;
import ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    CartItem findCartItemByProductAndUser(Product product,User user);
    List<CartItem> findAllByUser(User user);

}
