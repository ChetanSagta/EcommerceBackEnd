package ecommerce.entity;

public class ShoppingCartRequest {
    String userName;
    int productId;
    int quantity;

    public ShoppingCartRequest(String userName, int productId, int quantity) {
        this.userName = userName;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getUserId() {
        return userName;
    }

    public void setUserId(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
