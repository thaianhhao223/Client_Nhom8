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
    private String description;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Double price;
    private ProductType type;
    private ProductBrand brand;
    private String urlImageThumnail;
    private List<String> listImageDetail;
    private Boolean status;

    public double getAmount() {
        return this.price;
    }
}
