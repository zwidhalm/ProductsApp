package com.zachw.dtoclient;

public class ProductDTOClient {

    private Long id;
    private String description;
    private String productName;

    public ProductDTOClient() {
    }

    public ProductDTOClient(Long id, String description, String productName) {
        this.id = id;
        this.description = description;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", product_name='" + productName + '\'' +
                '}';
    }
}
