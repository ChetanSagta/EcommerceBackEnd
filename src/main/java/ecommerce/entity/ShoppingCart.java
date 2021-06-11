package ecommerce.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartId;
    @OneToMany(mappedBy = "cartItemId")
    List<CartItem> cartItems;
    @OneToOne
    @JoinColumn(name = "userId")
    User user;

    public ShoppingCart(List<CartItem> cartItems, User user) {
        this.cartItems = cartItems;
        this.user = user;
    }

    ShoppingCart(){}

    public boolean addToCart(CartItem cartItem){
        return this.cartItems.add(cartItem);
    }

}
