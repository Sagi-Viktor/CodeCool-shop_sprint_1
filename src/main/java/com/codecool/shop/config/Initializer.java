package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebListener
public class Initializer implements ServletContextListener {
    private ProductDao productDataStore = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //setting up a new supplier
        Supplier hasbroGaming = addSupplier("Hasbro Gaming", "Board games");
        Supplier treeTurtle = addSupplier("TreeTurtle", "Funny and cute");


        Supplier amazon = addSupplier("Amazon", "Digital content and services");
        Supplier lenovo = addSupplier("Lenovo", "Computers");
        Supplier sony = addSupplier("Sony", "Consoles");


        //setting up a new product category
        ProductCategory actionDrafting = addProductCategory("Action Drafting", "Board game", "A drafting action category", hasbroGaming, treeTurtle);


        ProductCategory cooperative = addProductCategory("Cooperative", "Board game", "Cooperative category", hasbroGaming);
        ProductCategory creative = addProductCategory("Creative", "Board game", "Creative category", hasbroGaming);
        ProductCategory educational = addProductCategory("Educational", "Board game", "Educational category", hasbroGaming);
        ProductCategory euroGame = addProductCategory("Eurogame", "Board game", "Eurogame category", hasbroGaming);
        ProductCategory family = addProductCategory("Family", "Board game", "Family category", hasbroGaming);
        ProductCategory luck = addProductCategory("Luck", "Board game", "Luck category", hasbroGaming);


        ProductCategory tablet = addProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.", amazon, lenovo);
        ProductCategory pc = addProductCategory("Pc", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.", lenovo);
        ProductCategory console = addProductCategory("Console", "Hardware", "A big box with lots of fun games.", sony);


        //setting up products and printing it
        productDataStore.add(new Product(
                "Happy Little Dinosaurs Base Game", new BigDecimal("20"),
                "USD", " Lately, it feels like we’re all just dinosaurs trying to avoid the falling meteors.\n" +
                "In this 2-4 player game, you’ll try to dodge all of life’s little disasters while tuning out your incredibly rude inner demons. You might fall into a pit of hot lava or get ghosted by your dino date. But think happy thoughts because the dino who survives it all wins the game!\n" +
                "Happy Little Dinosaurs takes 30-60 minutes to play and is for 2-4 players. It is recommended for ages 8+.\n" +
                "The box contains 97 cards, 4 Dinosaur player boards, 4 Dinosaur meeples, and 1 rule book. ",
                actionDrafting, treeTurtle,
                "happy_little_dinosaurs_base_game.jpg"
        ));






        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "product_1.jpg"));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", pc, lenovo, "product_1.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "product_1.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, lenovo, "product_1.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, lenovo, "product_1.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pc, lenovo, "product_1.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pc, lenovo, "product_1.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pc, lenovo, "product_1.jpg"));
        productDataStore.add(new Product("playStation Fire HD 8", new BigDecimal("89"), "USD", "best console ever", console, sony, "product_1.jpg"));
    }

    private Supplier addSupplier(String name, String description) {
        Supplier supplier = new Supplier(name, description);
        supplierDataStore.add(supplier);
        return supplier;
    }

    private ProductCategory addProductCategory(String name, String department, String description, Supplier... suppliers) {
        ProductCategory productCategory = new ProductCategory(name, department, description, Arrays.stream(suppliers).map(Supplier::getId).collect(Collectors.toList()));
        productCategoryDataStore.add(productCategory);
        for (Supplier supplier : suppliers) {
            supplier.addProductCategory(productCategory.getId());
        }
        return productCategory;
    }
}
