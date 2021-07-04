package ecommerce.services;

import ecommerce.entity.CartItem;
import ecommerce.entity.Product;
import ecommerce.entity.User;
import ecommerce.repositories.CartItemRepo;
import ecommerce.repositories.ProductRepo;
import ecommerce.repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartItemRepo cartItemRepo;



    public void addProductToCart(long productId, String userName, int quantity) {
        Product product = productRepo.findById(productId).orElse(null);
        User user = userRepo.findByUsername(userName).orElse(null);
        CartItem cartItem = cartItemRepo.findCartItemByProductAndUser(product,user);

        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
            cartItemRepo.save(cartItem);
            return;
        }
        cartItem = new CartItem(product,user,quantity);
        cartItemRepo.save(cartItem);
    }

    public void removeProductFromCart(Long productId, String  userName) {
        Product product = productRepo.findById(productId).orElse(null);
        User user = userRepo.findByUsername(userName).orElse(null);
        CartItem cartItem = cartItemRepo.findCartItemByProductAndUser(product,user);
        cartItemRepo.delete(cartItem);
    }

    public void updateProductQuantity(int quantity, Long productId, User user) {
        throw new UnsupportedOperationException();

    }

    public List<CartItem> readCart(String userName) {
        User user = userRepo.findByUsername(userName).orElse(null);
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user);
        logger.info("Cart: {}", cartItems);
        return cartItems;
    }

    private final Logger logger = LogManager.getLogger(CartService.class);
}
