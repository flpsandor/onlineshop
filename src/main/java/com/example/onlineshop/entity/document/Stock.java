package com.example.onlineshop.entity.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="stock")
public class Stock{
    @Id
    private String stockId;
    private Product stockProduct;
    private Integer stockNumber;
}
