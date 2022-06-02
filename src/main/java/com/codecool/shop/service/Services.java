package com.codecool.shop.service;

public class Services {
    private static ProductService productService = null;
    private static CartService cartService = null;

    public static void initProductService(ProductService productService) {
        if (Services.productService == null) {
            Services.productService = productService;
        }
    }

    public static void initCartService(CartService cartService) {
        if (Services.cartService == null) {
            Services.cartService = cartService;
        }
    }

    public static ProductService ProductService() {
        return productService;
    }

    public static CartService CartService() {
        return cartService;
    }
}
