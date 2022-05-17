import {dataHandler} from "./dataHandler.js";

main();

function main() {
    const addProductButtons = document.querySelectorAll(".add-product");
    addProductButtons.forEach((button) => button.addEventListener("click", addProductToCart))
    console.log("indexCart.js");
}

async function addProductToCart(event) {
    const productId = event.currentTarget.getAttribute("data-id");
    await dataHandler.addProductToCart(productId);
}
