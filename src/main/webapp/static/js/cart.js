import {dataHandler} from "./dataHandler.js";

const cart = document.getElementById("cart");

main();


async function main() {
    await refreshCartItems();
}

async function refreshCartItems() {
    const cartItemsData = await dataHandler.getItemsFromCart();
    cart.innerHTML = createCartItemsDiv(cartItemsData);
    addCartEventListeners();
}

function createCartItemsDiv(cartItems) {
    if (!cartItems.length) return '';
    const currency = cartItems[0]["product"]["defaultCurrency"];
    let totalPrice = 0;
    let cartItemsDiv = ``;
    for (let cartItem of cartItems) {
        const product = cartItem["product"];
        const supplier = product["supplier"];
        cartItemsDiv += `
        <div class="cart-item">
            <p>${product["name"]}</p>
            <small>${supplier["name"]}</small><br>
            <p>${product["defaultPrice"]} ${product["defaultCurrency"]}</p>
            ${createQuantitySelect(product["id"], cartItem["quantity"])}
            <small class="cart-item-remove" data-product-id="${product["id"]}"><a>Remove</a></small>
    </div>
        `;
        totalPrice += parseFloat(product["defaultPrice"]) * parseFloat(cartItem["quantity"]);
    }
    return `${cartItemsDiv}
    <p>Total price: ${totalPrice} ${currency}</p>
    `;
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

function addCartEventListeners() {
    const cartItemDropdowns = document.querySelectorAll(".cart-item-select");
    cartItemDropdowns.forEach(dropdown => dropdown.addEventListener("change", editCart));

    const cartItemsRemove = document.querySelectorAll(".cart-item-remove");
    cartItemsRemove.forEach(item => item.addEventListener("click", removeItemFromCart));
}

async function editCart(event) {
    const input = event.target;
    const productId = input.getAttribute("data-product-id");
    const quantity = input.value;
    await dataHandler.editCart(productId, quantity);
    await refreshCartItems();
}

async function removeItemFromCart(event) {
    const item = event.currentTarget;
    const productId = item.getAttribute("data-product-id");
    await dataHandler.removeItemFromCart(productId);
    await refreshCartItems();
}
