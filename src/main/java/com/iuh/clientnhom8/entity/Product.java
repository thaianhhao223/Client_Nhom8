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
    private Float percentDiscount;
    private ProductType type;
    private ProductBrand brand;
    private String urlImageThumnail;
    private List<String> listImageDetail;
    private Boolean status;

    public String getTypeName(){
        return this.type.getName();
    }

    public String getBrandName(){
        return this.brand.getName();
    }

    public String getAmountToString() {
        return String.format("%.0f", getAmount());
    }

    public String getPriceToString() {
        return String.format("%.0f", this.price);
    }

    public double getAmount() {
        return this.price * (1 - this.percentDiscount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", stock=" + stock +
                ", price=" + price +
                ", percentDiscount=" + percentDiscount +
                ", type=" + type +
                ", brand=" + brand +
                ", urlImageThumnail='" + urlImageThumnail + '\'' +
                ", listImageDetail=" + listImageDetail +
                ", status=" + status +
                '}';
    }
}
