package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class Product extends BaseModel {

    private String imageName;
    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private List<ProductCategory> productCategories;
    private Supplier supplier;


    public Product(
            String name,
            BigDecimal defaultPrice,
            String currencyString,
            String description,
            Supplier supplier,
            String imageName,
            ProductCategory[] productCategories
    ) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        for (ProductCategory category : productCategories) {
            this.setProductCategories(category);
        }
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

    public BigDecimal getPriceToDB(){
        return defaultPrice;
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public List<ProductCategory> getProductCategories() {
        return List.copyOf(productCategories);
    }

    public List<Integer> getProductCategoryIds() {
        return productCategories.stream().map(ProductCategory::getId).collect(Collectors.toList());
    }

    public void setProductCategories(ProductCategory productCategories) {
        if (this.productCategories == null) this.productCategories = new ArrayList<>();
        this.productCategories.add(productCategories);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean hasCategory(int categoryId) {
        return productCategories.stream().anyMatch(productCategory -> productCategory.getId() == categoryId);
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
                this.productCategories.stream().map(ProductCategory::getName),
                this.supplier.getName());
    }

    public boolean hasSupplier(List<Supplier> selectedSuppliers) {
        return selectedSuppliers.stream()
                .anyMatch(supplier -> supplier.id == this.supplier.id);
    }
    public boolean hasSupplier(Supplier supplier) {
        return this.supplier.getId() == supplier.getId();
    }
}
