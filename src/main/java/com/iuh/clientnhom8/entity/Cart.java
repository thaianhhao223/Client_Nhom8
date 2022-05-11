package com.iuh.clientnhom8.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Cart {
	private String id;
    private Customer customer;
    private List<ProductSale> productList = new ArrayList<>();

    private ProductSale findProductLineByProductId(String id) {
        for (ProductSale line : this.productList) {
            if (line.getProduct().getId().equals(id)) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(Product productInfo, int quantity) {
        ProductSale line = this.findProductLineByProductId(productInfo.getId());

        if (line == null) {
            line = new ProductSale();
            line.setQuantity(0);
            line.setProduct(productInfo);
            this.productList.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.productList.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void updateProduct(String id, int quantity) {
        ProductSale line = this.findProductLineByProductId(id);

        if (line != null) {
            if (quantity <= 0) {
                this.productList.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(Product productInfo) {
        ProductSale line = this.findProductLineByProductId(productInfo.getId());
        if (line != null) {
            this.productList.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.productList.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customer != null;
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (ProductSale line : this.productList) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public String getAmountTotal() {
        double total = 0;
        for (ProductSale line : this.productList) {
            total += line.getTotalAmount();
        }
        return String.format("%.0f", total);
    }

    public void updateQuantity(Cart cartForm) {
        if (cartForm != null) {
            List<ProductSale> lines = cartForm.getProductList();
            for (ProductSale line : lines) {
                this.updateProduct(line.getProduct().getId(), line.getQuantity());
            }
        }
    }
}
