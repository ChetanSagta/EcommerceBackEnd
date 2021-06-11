package ecommerce.controller;

import ecommerce.entity.ShoppingCart;
import ecommerce.entity.ShoppingCartRequest;
import ecommerce.services.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    CartService cartService;

    @GetMapping("/api/getCart")
    public ShoppingCart getCartDetailsBasedOnUserId(String userName) {
        return cartService.readCart(userName);
    }

    @PostMapping("/api/updateCart")
    public void updateCartContent(long userId, ShoppingCart cart) {
    }

    @PostMapping("/api/deleteProductFromCart")
    public void deleteProductFromCart(long productId, int cartId) {

    }

    @PostMapping("/api/addProductToCart")
    public void addProductToCart(@RequestBody ShoppingCartRequest shoppingCartRequest) {
        cartService.addProductToCart(shoppingCartRequest.getProductId(), shoppingCartRequest.getUserId(), shoppingCartRequest.getQuantity());
    }

    private final Logger logger = LogManager.getLogger(ShoppingCartController.class);
}
