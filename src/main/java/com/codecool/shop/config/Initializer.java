package com.codecool.shop.config;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.util.Arrays;
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


//        Supplier amazon = addSupplier("Amazon", "Digital content and services");
//        Supplier lenovo = addSupplier("Lenovo", "Computers");
//        Supplier sony = addSupplier("Sony", "Consoles");


        //setting up a new product category
        ProductCategory actionDrafting = addProductCategory("Action Drafting", "Board game", "A drafting action category", hasbroGaming, treeTurtle);


        ProductCategory cooperative = addProductCategory("Cooperative", "Board game", "Cooperative category", hasbroGaming);
        ProductCategory creative = addProductCategory("Creative", "Board game", "Creative category", hasbroGaming, treeTurtle);
        ProductCategory educational = addProductCategory("Educational", "Board game", "Educational category", hasbroGaming);
        ProductCategory euroGame = addProductCategory("Eurogame", "Board game", "Eurogame category", hasbroGaming);
        ProductCategory family = addProductCategory("Family", "Board game", "Family category", hasbroGaming);
        ProductCategory luck = addProductCategory("Luck", "Board game", "Luck category", hasbroGaming);
        ProductCategory expansion = addProductCategory("Expansion", "Board game expansions", "expansions for other base games", treeTurtle);
        ProductCategory adult = addProductCategory("Adult", "Adult Board Game", "Board games Only for adults!", treeTurtle);
        ProductCategory other = addProductCategory("Other", "Other products","Some other products", treeTurtle);


//        ProductCategory tablet = addProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.", amazon, lenovo);
//        ProductCategory pc = addProductCategory("Pc", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.", lenovo);
//        ProductCategory console = addProductCategory("Console", "Hardware", "A big box with lots of fun games.", sony);


        //setting up products and printing it

        addProduct(
                "Happy Little Dinosaurs Base Game", "20",
                "USD",
                treeTurtle,
                "happy_little_dinosaurs_base_game.jpg",
                "Lately, it feels like we’re all just dinosaurs trying to avoid the falling meteors.\n" +
                        "In this 2-4 player game, you’ll try to dodge all of life’s little disasters while tuning out your incredibly rude inner demons. You might fall into a pit of hot lava or get ghosted by your dino date. But think happy thoughts because the dino who survives it all wins the game!\n" +
                        "Happy Little Dinosaurs takes 30-60 minutes to play and is for 2-4 players. It is recommended for ages 8+.\n" +
                        "The box contains 97 cards, 4 Dinosaur player boards, 4 Dinosaur meeples, and 1 rule book. ",
                actionDrafting
        );
        addProduct(
                "Happy Little Dinosaurs: Perils of Puberty Expansion",
                "12.99",
                "USD",
                treeTurtle,
                "happy_little_dinosaurs:_perils_of_puberty_expansion .jpg",
                "The Happy Little Dinosaurs Perils of Puberty Expansion is designed to be added to your Happy Little Dinosaurs Card Game.\n" +
                        "It’s time to talk about your changing body!\n" +
                        "This 54-card expansion pack will take you back to the days of terrible acne, raging hormones, and carnivorous classmates...or maybe those are just a dinosaur thing.\n" +
                        "This game was designed for 2-4 players, has a 30-60 minute playtime, and is recommended for ages 8+. ",
                actionDrafting,
                expansion
        );
        addProduct(
                "Happy Little Dinosaurs: 5-6 Player Expansion Pack ",
                "15",
                "USD",
                treeTurtle,
                "happy_little_dinosaurs:_5-6_player-expansion_pack .jpg",
                " The Happy Little Dinosaurs 5-6 Player Expansion is designed to be added to your Happy Little Dinosaurs Card Game.\n" +
                        "Get ready to face new disasters and meet more unlucky Dinosaurs! You’ll dodge unimaginable horrors like killer chickens, deadly tornadoes, and terrible movies.\n" +
                        "This 50-card expansion pack introduces 2 new Dinosaur characters, making the game playable for 2-6 players.\n" +
                        "The expansion contains 50 cards, 2 Dinosaur player boards, 2 Dinosaur meeples, and 1 rule book.",
                actionDrafting,
                expansion
        );
        addProduct(
                "Happy Little Dinosaurs: Dating Disasters Expansion",
                "69",
                "USD",
                treeTurtle,
                "Happy_Little_Dinosaurs:_Dating_Disasters_Expansion .jpg",
                "The Happy Little Dinosaurs Dating Disasters Expansion is designed to be added to your Happy Little Dinosaurs Card Game.\n" +
                        "The only thing worse than a falling meteor? A first date.\n" +
                        "In this 54-card expansion pack, you might dodge the dreaded friendzone, encounter a giant catfish, or go out with a dino who says “I love you” way too early. Awkward...\n" +
                        "This game was designed for 2-4 players, has a 30-60 minute playtime, and is recommended for ages 8+.",
                actionDrafting,
                expansion
        );
        addProduct(
                "Llamas Unleashed",
                "17",
                "USD",
                treeTurtle,
                "Llamas_Unleashed.jpg",
                "Farmageddon is here. Prepare for a barnyard battle of epic proportions.\n" +
                        "Step aside, Unicorns...Llamas will take it from here! Llamas Unleashed includes 135 brand new cards starring agriculture’s lankiest and most lovable livestock.\n" +
                        "But Llamas aren’t the only new kid on the block. Goats, Rams, and Alpacas also run rampant in this witty and whimsical barnyard-themed card game based on the Unstable Unicorns mechanics you already know and love!\n" +
                        "The goal of Llamas Unleashed is to be the first person to collect seven Llamas, Goats, Alpacas, or Rams in your Farm. ",
                actionDrafting,
                creative
        );
        addProduct(
                "Llamas Unleashed: Farmed & Dangerous Expansion",
                "15",
                "USD",
                treeTurtle,
                "Llamas_Unleashed:_Farmed_&_Dangerous_Expansion .jpg",
                "The llamas are back and they’re armed with a new round of killer puns!\n" +
                        "Some might even say they’re Farmed & Dangerous.\n" +
                        "It’s time to herd your friends together and unPaca your skills to see if you can be the G.O.A.T.\n" +
                        "Includes 54 brand new puntastic cards!",
                luck,
                creative,
                expansion
        );
        addProduct(
                "Wrong Party Base Game",
                "25",
                "USD",
                treeTurtle,
                "Wrong_Party_Base_Game.jpg",
                " What happens when you invite a Baby, your Dentist, a Drug-Sniffing Dog, and a Mall Santa to Slay a Dragon? And what Murder Mystery Party would be complete without a Proud Mom of an Honor Roll Student and a Cult Leader butting heads? If the front row of your Royal Wedding doesn’t feature a Killer Clown, are you really doing it right?\n" +
                        "In this draft-style game, you try to have the coolest guest list. Sabotage your friends’ parties and make your own legendary!\n" +
                        "From the creators of Unstable Unicorns, winner of the Toy of the Year Awards’ 2019 People’s Choice Award.\n" +
                        "Wrong Party is for 2-5 players, ages 12+, and game play time is 30-60 minutes. The dimensions of the box are 6.89\"(h) x 6.89\"(w) x 1.69\"(d), and the item weight is TBD. The box contains: 152 Party Guest and Party Theme cards, 1 Score Tracker, and 5 Party Hat player pieces.\n" +
                        "Perfect for Friday nights with your closest friends! Perfect for awkward occasions with estranged family members! Perfect for passing time with your ex who still hasn’t moved out yet! Perfect to get your teenagers to leave their bedrooms during daylight hours!\n" +
                        "If you like Wrong Party, make sure you also check out Unstable Unicorns, Here to Slay, and Daring Contest! ",
                creative,
                luck,
                cooperative
        );
        addProduct(
                "Unstable Unicorns Card Game",
                "19.99",
                "USD",
                treeTurtle,
                "Unstable_Unicorns_Card_Game.jpg",
                "Build a Unicorn Army. Betray Your Friends. Unicorns are Your Friends Now.\n" +
                        "Unstable Unicorns is a strategic card game that will destroy your friendships but in a good way.\n" +
                        "The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide.\n" +
                        "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year.",
                creative,
                luck,
                cooperative,
                family
        );
        addProduct(
                "Unstable Unicorns Dragons Expansion Pack",
                "15",
                "USD",
                treeTurtle,
                "Unstable_Unicorns_Dragons_Expansion_Pack.jpg",
                " Unstable Unicorns Dragons Expansion Pack is designed to be added to your Unstable Unicorns Card Game (or Unstable Unicorns NSFW Card Game)\n" +
                        "This pack includes dragon-themed characters, magic cards, upgrades, and downgrades\n" +
                        "Unstable Unicorns is a strategic card game that will destroy your friendships...but in a good way\n" +
                        "The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide. Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year\n" +
                        "This highly giftable expansion pack contains 54 cards and is recommended for ages 14+",
                creative,
                luck,
                cooperative,
                family,
                expansion
        );
        addProduct(
                "TeeTurtle Unstable Unicorns Adventures Expansion Pack",
                "15",
                "USD",
                treeTurtle,
                "TeeTurtle_Unstable:_Adventures_Expansion_Pack.jpg",
                "     Unstable Unicorns Adventures Expansion Pack is designed to be added to your Unstable Unicorns Card Game (or Unstable Unicorns NSFW Card Game)\n" +
                        "    New adventures await in this expansion, featuring new characters, magic cards, upgrades, and downgrades\n" +
                        "    Unstable Unicorns is a strategic card game that will destroy your friendships but in a good way\n" +
                        "    The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide. Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year\n" +
                        "    This highly giftable expansion pack contains 54 cards and is recommended for ages 14+\n" +
                        "    Number of players: 2-8",
                expansion,
                luck,
                cooperative,
                family
        );
        addProduct(
                "Unstable Unicorns: Nightmares Expansion",
                "15",
                "USD",
                treeTurtle,
                "Unstable_Unicorns:_Nightmares_Expansion.jpg",
                " Don't be a Halloweenie! This expansion is dripping with your favorite horror tropes and spooks (and of course, lots of unicorns!)\n" +
                        "Unstable Unicorns Nightmares Expansion Pack is designed to be added to your Unstable Unicorns Card Game (or Unstable Unicorns NSFW Card Game)\n" +
                        "This pack includes new Characters cards, Magic cards, Upgrade cards, and Nightmare Downgrade cards.\n" +
                        "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year, was one of Kickstarter’s top 100 most backed projects of all time, and has sold over a million copies worldwide!\n" +
                        "This highly giftable expansion pack contains 54 cards and is recommended for ages 14+\n" +
                        "Number of players: 2-8",
                expansion,
                luck,
                cooperative,
                family
        );
        addProduct(
                "Unstable Unicorns: Vinyl Mini Blind Box Collection",
                "200",
                "USD",
                treeTurtle,
                "Unstable_Unicorns:_Vinyl_Mini_Blind_Box_Collection.jpg",
                " Your favorite Unstable Unicorns have been turned into vinyl mini figures!\n" +
                        "Each blind box contains a surprise character and an Unstable Unicorns promo card featuring new artwork\n" +
                        "Find 8 characters you know and love and 2 brand new mystery characters\n" +
                        "Collect them one by one, or get the pack of 18 and you’re guaranteed to find all 10 figures!",
                other,
                expansion
        );
        addProduct(
                "Unstable Unicorns Christmas Special Expansion Pack ",
                "15",
                "USD",
                treeTurtle,
                "Unstable_Unicorns_Christmas_Special_Expansion_Pack .jpg",
                "Unstable Unicorns Christmas Special Expansion Pack is designed to be added to your Unstable Unicorns Card Game (or Unstable Unicorns NSFW Card Game).\n" +
                        "This pack includes holiday-themed characters, magic cards, upgrades, and downgrades.\n" +
                        "Unstable Unicorns is a strategic card game that will destroy your friendships...but in a good way.\n" +
                        "The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide. Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year.\n" +
                        "This highly giftable expansion pack contains 36 cards and is recommended for ages 8+",
                expansion,
                luck,
                cooperative,
                family);
        addProduct(
                "Unstable Unicorns Not Safe For Work (NSFW) Expansion Pack",
                "14.33",
                "USD",
                treeTurtle,
                "Unstable_Unicorns_Not_Safe_For_Work_(NSFW)_Expansion_Pack.jpg",
                "Unstable Unicorns NSFW Expansion Pack is designed to be added to your Unstable Unicorns Card Game.\n" +
                        "This pack includes NSFW characters, magic cards, upgrades, and downgrades for people with dirty minds.\n" +
                        "Unstable Unicorns is a strategic card game that will destroy your friendships...but in a good way.\n" +
                        "The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide.\n" +
                        "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year. This highly giftable expansion pack contains 54 cards and is for ages 21+.",
                expansion,
                luck,
                cooperative,
                adult
        );
        addProduct(
                "Unstable Unicorns NSFW Card Game - A strategic card game and party game for adults with drinking rules available (for ages 21+)",
                "69.69",
                "USD",
                treeTurtle,
                "Unstable_Unicorns_NSFW.jpg",
                "The NSFW edition of Unstable Unicorns is still a strategic game that will destroy your friendships in a hilariously offensive way. Horrify your friends. Abandon them. Unicorns don’t judge.\n" +
                        "The game features the same style of gameplay as the beloved Unstable Unicorns, just a bit...dirtier.\n" +
                        "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year.\n" +
                        "This highly giftable game takes 30–45 minutes to play and is for 2–8 players (ages 21+).\n" +
                        "The box contains 135 NSFW cards and a rule book (plus optional drinking and stripping rules) and features a magnetic closure. This game is fully playable on its own, and any expansion pack can be added to it.",
                expansion,
                adult,
                luck,
                cooperative
        );
        addProduct(
                "Unstable Unicorns Unicorns of Legend Expansion Pack",
                "15",
                "USD",
                treeTurtle,
                "Unstable_Unicorns:_Unicorns_of_Legend_Expansion_Pack.jpg",
                "Unstable Unicorns Unicorns of Legend Expansion Pack is designed to be added to your Unstable Unicorns Card Game (or Unstable Unicorns NSFW Card Game).\n" +
                        "This pack includes fantasy-themed characters, magic cards, upgrades, and downgrades for explorers and adventurers.\n" +
                        "Unstable Unicorns is a strategic card game that will destroy your friendships...but in a good way.\n" +
                        "The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide.\n" +
                        "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year. This highly giftable expansion pack contains 54 cards and is recommended for ages 14+",
                expansion,
                family,
                cooperative,
                luck
        );
        addProduct(
                "Unstable Unicorns Kids Edition",
                "15",
                "USD",
                treeTurtle,
                "Unstable_Unicorns:_Kids_Edition .jpg",
                "Introduce younger players to a magical world of unicorns and strategy games with Unstable Unicorns for Kids!\n" +
                        "Take turns collecting Unicorns and using Magic. The first person to collect 7 Unicorns wins the game!\n" +
                        "Unstable Unicorns for Kids is a 2-6 player game for ages 6+ that features brand new characters and adorable artwork that kids will love! The duration of one game is 15-45 minutes.\n" +
                        "The charming game box includes 32 Colorful Unicorn cards, 8 Baby Unicorn cards, 32 Magic cards, and 8 Neigh cards.\n" +
                        "This highly giftable game contains 80 cards and 1 rule book.\n" +
                        "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year, was one of Kickstarter’s top 100 most backed projects of all time, and has sold over a million copies worldwide!",
                educational,
                luck,
                family,
                cooperative
        );


//        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "product_1.jpg"));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", pc, lenovo, "product_1.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "product_1.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, lenovo, "product_1.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, lenovo, "product_1.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pc, lenovo, "product_1.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pc, lenovo, "product_1.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", pc, lenovo, "product_1.jpg"));
//        productDataStore.add(new Product("playStation Fire HD 8", new BigDecimal("89"), "USD", "best console ever", console, sony, "product_1.jpg"));
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

    private void addProduct(String name, String price, String currency, Supplier supplier, String imageName, String description, ProductCategory... productCategories) {
        productDataStore.add(new Product(name, new BigDecimal(price), currency, description, supplier, imageName, productCategories));
    }
}
