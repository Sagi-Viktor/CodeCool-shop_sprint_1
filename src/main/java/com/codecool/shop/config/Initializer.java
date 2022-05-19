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
        Supplier llc = addSupplier("LLC", "Exploding cittens distributor");


        //setting up a new product category
        ProductCategory actionDrafting = addProductCategory("Action Drafting", "Board game", "A drafting action category", hasbroGaming, treeTurtle);
        ProductCategory cooperative = addProductCategory("Cooperative", "Board game", "Cooperative category", hasbroGaming, treeTurtle, llc);
        ProductCategory creative = addProductCategory("Creative", "Board game", "Creative category", hasbroGaming, treeTurtle, llc);
        ProductCategory educational = addProductCategory("Educational", "Board game", "Educational category", hasbroGaming, treeTurtle);
        ProductCategory euroGame = addProductCategory("Eurogame", "Board game", "Eurogame category", hasbroGaming);
        ProductCategory family = addProductCategory("Family", "Board game", "Family category", hasbroGaming, treeTurtle, llc);
        ProductCategory luck = addProductCategory("Luck", "Board game", "Luck category", hasbroGaming, treeTurtle, llc);
        ProductCategory expansion = addProductCategory("Expansion", "Board game expansions", "expansions for other base games", treeTurtle, llc);
        ProductCategory adult = addProductCategory("Adult", "Adult Board Game", "Board games Only for adults!", treeTurtle, llc);
        ProductCategory other = addProductCategory("Other", "Other products", "Some other products", treeTurtle);


        //setting up products and printing it
        {
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
            addProduct(
                    "Unstable Unicorns Rainbow Apocalypse Expansion Pack",
                    "15",
                    "USD",
                    treeTurtle,
                    "Unstable_Unicorns:_Rainbow_Apocalypse_Expansion_Pack.jpg",
                    "Unstable Unicorns Rainbow Apocalypse Expansion Pack is designed to be added to your Unstable Unicorns Card Game (or Unstable Unicorns NSFW Card Game).\n" +
                            "This pack includes cards ranging from super cute unicorns to the terrifyingly destructive Four Unicorns of the Apocalypse.\n" +
                            "Unstable Unicorns is a strategic card game that will destroy your friendships...but in a good way.\n" +
                            "The game was one of Kickstarter’s top 100 most backed projects of all time and has sold over a million copies worldwide.\n" +
                            "Unstable Unicorns won the 2019 People’s Choice Award for Toy of the Year. This highly giftable expansion pack contains 54 cards and is recommended for ages 14+.",
                    luck,
                    cooperative,
                    family,
                    expansion
            );
            addProduct(
                    "Case Compatible for Unstable Unicorns Card Game, for Legends",
                    "15.99",
                    "USD",
                    treeTurtle,
                    "Case_Compatible_for_Unstable_Unicorns_Card_Game_for_Legends.jpg",
                    "Card Game Case: Design for Unstable Unicorns Card Game Dragons/for Christmas Special /for Adventures /for Dragons Expansion Pack/for Llamas Unleashed/for Not Safe For Work/for Rainbow Apocalypse/for Unicorns of Legend All Expansion Pack Cards.(CASE ONLY）\n" +
                            "Comes with 2 removable divider for your convenience and extra pocket to store over 350 cards.\n" +
                            "Made of H-Q hard EVA material, shockproof, skidproof and water resistant, with soft interior layer will hold all cards in one place. And also protect your beloved card games from scratches or dirtiness.\n" +
                            "Also fits other playing card games like for Crabs Adjust Humidity, for Phase 10, for Pokemon, for Magic the gathering, for Poker, for Yugioh, for Apples to Apples Super fight and so on.\n" +
                            "Perfect for an ideal gift, very convenient to carry them with you wherever you go.(We offer Full Refund if there are any quality problems)",
                    expansion,
                    other
            );
            addProduct(
                    "Here to Slay: Vinyl Mini Series Blind Box",
                    "10",
                    "USD",
                    treeTurtle,
                    "Here_to_Slay:_Vinyl_Mini_Series_Blind_Box .jpg",
                    "Are you ready to slay? The cute, cuddly, and utterly destructive creatures from Here to Slay are coming to life as vinyl mini figures!\n" +
                            "Each of these adorably brutal figures comes with a holographic Here to Slay promo card featuring brand new artwork.\n" +
                            "Each of the ten characters represents a different class, and the set includes two mystery characters that have never been seen before.\n" +
                            "Buy one figure at a time for a mystery surprise!\n" +
                            "Blind box includes one random figure and matching promo card to add to your Here to Slay game!",
                    expansion,
                    other
            );
            addProduct(
                    "Exploding Kittens - A Russian Roulette Card Game",
                    "19.99",
                    "USD",
                    llc,
                    "Exploding_Kittens_-_A_Russian_Roulette_Card_Game.jpg",
                    " A Kitty-Powered Russian Roulette Card Game - The hit party game for family and friends who are into kittens and explosions and laser beams and sometimes goats\n" +
                            "Game Of The Year Award Winner - More than 10 million copies sold, breaking records in kids games, adult games and everything in between\n" +
                            "A Simple Card Game for Kids and Adults - Perfect for all occasions, including a family game night, beach trip, road trip or drinking with friends\n" +
                            "Beginner-Friendly, Just 2 Minutes to Learn - If you draw an Exploding Kitten, you lose and you are full of loser sad-sauce. All the other cards in the deck help you avoid or mitigate your risk of drawing an Exploding Kitten. If you don’t explode, YOU WIN",
                    family,
                    luck,
                    cooperative,
                    creative
            );
            addProduct(
                    "Exploding Kittens NSFW - ADULT Russian Roulette Card Game",
                    "19.99",
                    "USD",
                    llc,
                    "Exploding_Kittens_NSFW_-_ADULT_Russian_Roulette_Card_Game.jpg",
                    "A Kitten-Powered Russian Roulette Card Game, For Adults - A Party Game For Friends But With Card Art Much Too Incredible To Include In A Kid-Friendly Version. Do Not Buy For Children, Unless You're Ready To Have Some Weird Conversations\n" +
                            "    Game Of The Year Award Winner - More Than 10 Million Copies Sold, Breaking Records In Kids Games, Adult Games And Everything In Between\n" +
                            "    A Simple Card Game For Adults - Perfect For A Game Night, Beach Trip, Road Trip With Friends\n" +
                            "    Beginner-Friendly, Just 2 Minutes To Learn - If You Draw An Exploding Kitten, You Lose And You Are Full Of Loser Sad-Sauce. All The Other Cards In The Deck Help You Avoid Or Mitigate Your Risk Of Drawing An Exploding Kitten. If You Don’t Explode, You Win\n" +
                            "\n" +
                            "Buy it with",
                    adult,
                    expansion,
                    luck,
                    cooperative,
                    family
            );
            addProduct(
                    "Exploding Kittens Party - A Russian Roulette Card Game",
                    "24.99",
                    "USD",
                    llc,
                    "Exploding_Kittens_Party_-_A_Russian_Roulette_Card_Game.jpg",
                    "A Kitten-Powered Russian Roulette Card Game, For Up to 10 - Purr-fect party game for family and friends who are into kittens and explosions and laser beams and sometimes goats. Now for double the players!\n" +
                            "Game Of The Year Award Winner - More than 10 million copies sold, breaking records in kids games, adult games and everything in between.\n" +
                            "A Simple Card Game for Kids and Adults - Perfect for all occasions, including a family game night, beach trip, road trip or drinking with friends!\n" +
                            "Beginner-Friendly, Just 2 Minutes to Learn - If you draw an Exploding Kitten, you lose and you are full of loser sad-sauce. All the other cards in the deck help you avoid or mitigate your risk of drawing an Exploding Kitten. If you don’t explode, YOU WIN!\n" +
                            "120 Cards, with Box and Instructions - A simple card game for kids, drinking, kittens, and adults, not in that order. ",
                    expansion,
                    luck,
                    cooperative,
                    family
            );
            addProduct(
                    "Barking Kittens Expansion Set - A Russian Roulette Card Game",
                    "14.99",
                    "USD",
                    llc,
                    "Barking_Kittens_Expansion_Set_-_A_Russian_Roulette_Card_Game.jpg",
                    "The third expansion to the record-breaking game, Exploding Kittens. A Kitten-Powered Russian Roulette Card Game.\n" +
                            "Game Of The Year Award Winner - More than 10 million copies sold, breaking records in kids games, adult games and everything in between.\n" +
                            "A Simple Card Game for Kids and Adults - Perfect for all occasions, including a family game night, beach trip, road trip or drinking with friends!\n" +
                            "Beginner-Friendly, Just 2 Minutes to Learn - If you draw an Exploding Kitten, you lose and you are full of loser sad-sauce. All the other cards in the deck help you avoid or mitigate your risk of drawing an Exploding Kitten. If you don’t explode, YOU WIN!\n" +
                            "This is not a standalone game—it requires a copy of Exploding Kittens to play.",
                    expansion,
                    educational,
                    cooperative,
                    family
            );
            addProduct(
                    "Imploding Kittens Expansion Set - A Russian Roulette Card Game",
                    "14.99",
                    "USD",
                    llc,
                    "Imploding_Kittens_Expansion_Set_-_A_Russian_Roulette_Card_Game.jpg",
                    " The First Expansion Of A Kitten-Powered Russian Roulette Card Game - Purr-Fect Add-On To Your Favorite Party Game For Family And Friends. Comes With A Human-Sized \"Cone Of Shame.\"\n" +
                            "Game Of The Year Award Winner - More Than 10 Million Copies Sold, Breaking Records In Kids Games, Adult Games And Everything In Between\n" +
                            "A Simple Card Game For Kids And Adults - Perfect For All Occasions, Including A Family Game Night, Beach Trip, Road Trip Or Drinking With Friends!\n" +
                            "Beginner-Friendly, Just 2 Minutes To Learn - If You Draw An Exploding Kitten, You Lose And You Are Full Of Loser Sad-Sauce. All The Other Cards In The Deck Help You Avoid Or Mitigate Your Risk Of Drawing An Exploding Kitten. If You Don’T Explode, You Win!",
                    expansion,
                    luck,
                    cooperative,
                    family
            );
            addProduct(
                    "Streaking Kittens Expansion Set - Ridiculous Russian Roulette Card Game",
                    "12.68",
                    "USD",
                    llc,
                    "Streaking_Kittens_Expansion_Set_-_Ridiculous_Russian_Roulette_Card_Game.jpg",
                    " The Second Expansion To The Record-Breaking Game, Exploding Kittens.This Deck Of 15 Cards Includes The Streaking Kitten Which Allows You To Secretly Hold 1 Exploding Kitten Without Exploding!\n" +
                            "Game Of The Year Award Winner - More Than 10 Million Copies Sold, Breaking Records In Kids Games, Adult Games And Everything In Between.\n" +
                            "A Simple Card Game For Kids And Adults - Perfect For All Occasions, Including A Family Game Night, Beach Trip, Road Trip Or Drinking With Friends!\n" +
                            "Beginner-Friendly, Just 2 Minutes To Learn - If You Draw An Exploding Kitten, You Lose And You Are Full Of Loser Sad-Sauce. All The Other Cards In The Deck Help You Avoid Or Mitigate Your Risk Of Drawing An Exploding Kitten. If You Don’T Explode, You Win!",
                    expansion,
                    cooperative,
                    luck,
                    family
            );
        }
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
