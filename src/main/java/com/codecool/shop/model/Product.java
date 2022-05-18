package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class Product extends BaseModel {

    private String imageName;
    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(
            String name,
            BigDecimal defaultPrice,
            String currencyString,
            String description,
            ProductCategory productCategory,
            Supplier supplier,
            String imageName
    ) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setProductCategory(productCategory);
        this.setSupplier(supplier);
        this.setImageName(imageName);
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s" +
                        "defaultPrice: %4$f, " +
                        "defaultCurrency: %5$s, " +
                        "productCategory: %6$s, " +
                        "supplier: %7$s",
                this.id,
                this.name,
                this.description,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }

    public boolean hasSupplier(List<Supplier> selectedSuppliers) {
        return selectedSuppliers.stream()
                .anyMatch(supplier -> supplier.id == this.supplier.id);
    }
}
