package com.iuh.clientnhom8.request.product;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateProductRequest {
    private String name;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Long price;
    private Float percentDiscount;
    private String typeId;
    private String brandId;
    private String urlImageThumnail;
    private List<String> listImageDetail;

//    private Boolean status;
}
