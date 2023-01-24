package com.example.onlineshop.entity.document;


import com.example.onlineshop.entity.enum_s.OrderStatus;
import com.example.onlineshop.entity.pojo.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="order")
public class Order {
    @Id
    private String orderId;
    private LocalDateTime orderDateTime;
    private OrderStatus orderStatus;
    private Double orderPrice;
    private List<CartProduct> orderProducts;
    @DBRef
    private User orderUser;
}
