package ecommerce.services;

import ecommerce.entity.CartItem;
import ecommerce.entity.Product;
import ecommerce.entity.ShoppingCart;
import ecommerce.entity.User;
import ecommerce.repositories.CartItemRepo;
import ecommerce.repositories.CartRepo;
import ecommerce.repositories.ProductRepo;
import ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    public void addProductToCart(long productId, String userName, int quantity) {
        Product product = productRepo.findById(productId).orElse(null);
        User user = userRepo.findByUsername(userName).orElse(null);
        ShoppingCart shoppingCart = cartRepo.findShoppingCartBasedByUser(user);
        if (shoppingCart == null) {
            createCart(user.getUsername());
        }
        CartItem cartItem = cartItemRepo.findCartItemByShoppingCartAndProduct(shoppingCart, product);
        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
            cartItemRepo.save(cartItem);
            return;
        }

        cartItem = new CartItem(product,shoppingCart,quantity);
        cartItemRepo.save(cartItem);
    }

    public void removeProductFromCart(Long ProductId, User user) {

    }

    public void updateProductQuantity(int quantity, Long ProductId, User user) {

    }

    public ShoppingCart readCart(String userName) {
        User user = userRepo.findByUsername(userName).orElse(null);
        return cartRepo.findShoppingCartBasedByUser(user);
    }

    public void createCart(String userName) {
        ShoppingCart cart = new ShoppingCart(null, userRepo.findByUsername(userName).orElse(null));
        cartRepo.save(cart);
    }

    public ShoppingCart isProductAlreadyAdded(User user, Product product) {
        return cartRepo.findShoppingCartBasedByUser(user);
    }


}
