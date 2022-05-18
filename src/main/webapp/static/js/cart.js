import {dataHandler} from "./dataHandler.js";

main();

async function main() {
    const cartItems = await getCartItems();
    console.log(cartItems);
}

async function getCartItems() {
    return await dataHandler.getItemsFromCart();
}