package com.iuh.clientnhom8.model.product;

import com.iuh.clientnhom8.entity.ProductBrand;
import com.iuh.clientnhom8.entity.ProductType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateProduct {
    private String name;
    private String description;
    private Integer size;
    private Integer stock; //Số lượng tồn
    private Double price;
    private Float percentDiscount;
    private String typeId;
    private String brandId;
    private MultipartFile urlImageThumnail;
    private List<MultipartFile> listImageDetail;
    private Boolean status;
}
