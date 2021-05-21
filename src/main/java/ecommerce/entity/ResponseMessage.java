package ecommerce.entity;

public class ResponseMessage {
    String message;
    int statusCode;

    public ResponseMessage(String message, int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }
}
