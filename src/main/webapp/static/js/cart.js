import {dataHandler} from "./dataHandler.js";

main();

async function main() {
    const cart = document.getElementById("cart");
    const cartItemsData = await getCartItemsData();
    const cartItems = createCartItemsDiv(cartItemsData);
    cart.innerHTML = cartItems;
    const cartItemDropdowns = document.querySelectorAll(".cart-item-select");
    cartItemDropdowns.forEach(dropdown => dropdown.addEventListener("change", editCart))
}

async function editCart(event) {
    const input = event.target;
    const productId = input.getAttribute("data-product-id");
    const quantity = input.value;
    await dataHandler.editCart(productId, quantity);
}

async function getCartItemsData() {
    return await dataHandler.getItemsFromCart();
}

function createCartItemsDiv(cartItems) {
    let cartItemsDiv = ``;
    for (let cartItem of cartItems) {
        const product = cartItem["product"];
        const supplier = product["supplier"];
        cartItemsDiv += `
        <div class="cart-item">
            <p>${product["name"]}</p>
            <small>${supplier["name"]}</small><br>
            ${createQuantitySelect(product["id"], cartItem["quantity"])}
    </div>
        `;
    }
    return cartItemsDiv;
}

function createQuantitySelect(cartItemId, quantity) {
    let options = ``;
    for (let number = 1; number < 11; number++) {
        options += `
            <option value="${number}" ${(number === quantity) ? "selected" : ""}>${number}</option>
        `;
    }
    return `
        <select class="cart-item-select" data-product-id="${cartItemId}">
        ${options}
    </select>
    `;
}