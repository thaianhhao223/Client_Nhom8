package com.iuh.clientnhom8.entity;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {
	private String id;
    private String name;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Long price;
    private Float percentDiscount;
    private ProductType type;
    private ProductBrand brand;
    private String urlImageThumnail;
    private List<String> listImageDetail;
    private Boolean status;
}
