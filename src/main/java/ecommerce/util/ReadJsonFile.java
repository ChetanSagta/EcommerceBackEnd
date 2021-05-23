package ecommerce.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.entity.Product;
import ecommerce.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Class Created just to enter data from the jsonFile to the database..
 * Will not be used in any other place other than here
 * maybe in the future some generic version..but maybe never
 */

public class ReadJsonFile {
  @Autowired
  ProductRepo productRepo;

  public void read(String file) throws IOException, SQLException {

    BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + file)));
    String line="";
    StringBuilder finalLine= new StringBuilder();
    ObjectMapper objectMapper = new ObjectMapper();
//      Product products = objectMapper.readValue(new InputStreamReader(this.getClass().getResourceAsStream(file)), Product.class);
    while((line = br.readLine())!=null) finalLine.append(line.trim());

//    JsonParser jsonParser = objectMapper.createParser(finalLine.toString());
//    TreeNode tree = objectMapper.readTree(jsonParser);
//    System.out.println(tree.get("id"));

    List<Product> products = objectMapper.readValue(finalLine.toString(), new TypeReference<List<Product>>(){});
    //System.out.println(products.get(0).getClass());
    //productRepo.saveAll(map);
    String query = "insert into product(title,`description`, category, imageURL, price) values(?,?,?,?,?)";
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ecommerce","root","root");
    PreparedStatement pstmt = connection.prepareStatement(query);
    for(Product product: products){
      pstmt.setString(1,product.getTitle());
      pstmt.setString(2,product.getDescription());
      pstmt.setString(3,product.getCategory());
      pstmt.setString(4,product.getImage());
      pstmt.setInt(5,product.getPrice());

      pstmt.executeQuery();
    }
  }

    public static void main(String[] args) throws IOException, SQLException {
        ReadJsonFile jsonFile = new ReadJsonFile();
        jsonFile.read("jsonFile/ecommerce.json");
    }
}
