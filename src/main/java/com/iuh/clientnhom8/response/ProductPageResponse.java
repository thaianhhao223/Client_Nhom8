package com.iuh.clientnhom8.response;

import com.iuh.clientnhom8.entity.Product;
import lombok.*;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductPageResponse {
    private Page<Product> productPage;
}
