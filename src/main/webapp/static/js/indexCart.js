import {dataHandler} from "./dataHandler.js";

main();

function main() {
    const addProductButtons = document.querySelectorAll(".add-product");
    addProductButtons.forEach((button) => button.addEventListener("click", addProductToCart))
    console.log("indexCart.js");
}

function addProductToCart(event) {
    console.log(event);
}
