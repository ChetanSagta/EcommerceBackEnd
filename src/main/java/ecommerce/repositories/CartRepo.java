package ecommerce.repositories;

import ecommerce.entity.ShoppingCart;
import ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<ShoppingCart, Integer> {

    ShoppingCart findShoppingCartBasedByUser(User user);

}
