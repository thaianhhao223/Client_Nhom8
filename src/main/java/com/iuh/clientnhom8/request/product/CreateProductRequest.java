package com.iuh.clientnhom8.request.product;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CreateProductRequest {
    private String name;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Double price;
    private String description;
    private Float percentDiscount;
    private String typeId;
    private String brandId;
    private String urlImageThumnail;
    private List<String> listImageDetail;

//    private Boolean status;
}
