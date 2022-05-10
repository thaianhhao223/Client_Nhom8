package com.iuh.clientnhom8.request.product;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateProductRequest {
    private String name;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Double price;
    private Float percentDiscount;
    private String typeId;
    private String brandId;
    private String urlImageThumnail;
    private List<String> listImageDetail;

//    private Boolean status;
}
