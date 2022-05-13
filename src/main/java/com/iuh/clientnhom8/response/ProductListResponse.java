package com.iuh.clientnhom8.response;

import com.iuh.clientnhom8.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductListResponse {
    private List<Product> productList;
    private int number;
    private int totalPages;
}
