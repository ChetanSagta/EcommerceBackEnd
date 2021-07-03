package ecommerce.controller;

import ecommerce.entity.CartItem;
import ecommerce.entity.ResponseMessage;
import ecommerce.entity.ShoppingCartRequest;
import ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class ShoppingCartController {

    @Autowired
    CartService cartService;

    @PostMapping("/getCart")
    public ResponseEntity<List<CartItem>> getCartDetailsBasedOnUserId(@RequestBody String userName) {
        List<CartItem> cartItemList = cartService.readCart(userName);
        cartItemList.forEach(cartItem -> cartItem.setUser(null));
        return ResponseEntity.ok(cartItemList);
    }

    @PostMapping("/updateCart")
    public void updateCartContent(long userId) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/deleteProductFromCart")
    public void deleteProductFromCart(@RequestBody Map<String, String> result) {
        cartService.removeProductFromCart(Long.valueOf(result.get("productId")), result.get("userName"));
    }

    @PostMapping("/addProductToCart")
    public ResponseMessage addProductToCart(@RequestBody ShoppingCartRequest shoppingCartRequest) {
        cartService.addProductToCart(shoppingCartRequest.getProductId(), shoppingCartRequest.getUserId(), shoppingCartRequest.getQuantity());
        return new ResponseMessage("Item added to the cart", HttpStatus.OK);
    }

}
