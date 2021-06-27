package ecommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.entity.CartItem;
import ecommerce.entity.ResponseMessage;
import ecommerce.entity.ShoppingCartRequest;
import ecommerce.services.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    CartService cartService;

    @PostMapping("/api/getCart")
    public ResponseEntity<List<CartItem>> getCartDetailsBasedOnUserId(@RequestBody String userName) {
        List<CartItem> cartItemList = cartService.readCart(userName);
        cartItemList.stream().forEach(cartItem -> cartItem.setUser(null));
        return ResponseEntity.ok(cartItemList);
    }

    @PostMapping("/api/updateCart")
    public void updateCartContent(long userId) {
    }

    @PostMapping("/api/deleteProductFromCart")
    public void deleteProductFromCart(@RequestBody String content) throws JsonProcessingException {
        Map<String,Object> result = new ObjectMapper().readValue(content, HashMap.class);
        cartService.removeProductFromCart(Long.valueOf(result.get("productId").toString()),result.get("userName").toString());
    }

    @PostMapping("/api/addProductToCart")
    public ResponseMessage addProductToCart(@RequestBody ShoppingCartRequest shoppingCartRequest) {
        cartService.addProductToCart(shoppingCartRequest.getProductId(), shoppingCartRequest.getUserId(), shoppingCartRequest.getQuantity());
        return new ResponseMessage("Item added to the cart", 200);
    }

    private final Logger logger = LogManager.getLogger(ShoppingCartController.class);
}
