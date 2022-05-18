import {dataHandler} from "./dataHandler.js";

main();

async function main() {
    const cart = document.getElementById("cart");
    const cartItemsData = await getCartItemsData();
    const cartItems = createCartItemsDiv(cartItemsData);
    cart.innerHTML = cartItems;
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
            <small>${supplier["name"]}</small>
            ${createQuantitySelect(cartItem["quantity"])}
    </div>
        `;
    }
    return cartItemsDiv;
}

function createQuantitySelect(quantity) {
    let options = ``;
    for (let number = 1; number < 11; number++) {
        options += `
            <option value="${number}" ${(number === quantity) ? "selected" : ""}>${number}</option>
        `;
    }
    return `
        <select>
        ${options}
    </select>
    `;
}