package com.codecool.shop.service;

public class Services {
    private static ProductService productService = null;
    private static CartService cartService = null;
    private static OrderService orderService = null;

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

    public static void initOrderService(OrderService orderService) {
        if (Services.orderService == null) {
            Services.orderService = orderService;
        }
    }


    public static ProductService ProductService() {
        return productService;
    }

    public static CartService CartService() {
        return cartService;
    }

    public static OrderService OrderService() {return orderService;}
}
