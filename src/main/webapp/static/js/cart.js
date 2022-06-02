import {dataHandler} from "./dataHandler.js";

const cart = document.getElementById("cart");

main();


async function main() {
    await refreshCartItems();
}

async function refreshCartItems() {
    const cartData = await dataHandler.getCart();
    cart.innerHTML = createCartTable(cartData);
    addCartEventListeners();
}

function getTotalPrice(cartItems) {
    let totalPrice = 0;
    for (let cartItem of cartItems) {
        totalPrice += +cartItem["product"]["defaultPrice"] * cartItem["quantity"];
    }
    return totalPrice.toFixed(2);
}

function createCartTable(cart) {
    const cartItems = cart["cartItems"];
    const totalPrice = getTotalPrice(cartItems);
    if (!cartItems.length) return 'No items in cart';
    const currency = cartItems[0]["product"]["defaultCurrency"];
    let tableBody = createCartTableBody(cartItems);
    return `
    <table>
        <thead>
            <tr>
                <th>Image</th>
                <th>Product Name</th>
                <th>Unit price</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            ${tableBody}
        </tbody>
    </table>
    <p id="total-price">Total price: ${totalPrice} ${currency}</p>
    `;
}

function createCartTableBody(cartItems) {
    let tableBody = ``;
    for (let cartItem of cartItems) {
        const product = cartItem["product"];
        tableBody += `
            <tr class="cart-item">
                <td><img src="${"/static/img/" + product["imageName"]}" alt=""></td>
                <td>
                    <h3>${product["name"]}</h3>
                    <small class="cart-item-remove" data-product-id="${product["id"]}"><a>Remove</a></small>
                </td>
                <td><p>${product["defaultPrice"]} ${product["defaultCurrency"]}</p></td>
                <td>${createQuantitySelect(product["id"], cartItem["quantity"])}</td>
                <td>${(product["defaultPrice"] * cartItem["quantity"]).toFixed(2)} ${product["defaultCurrency"]}</td>
            </tr>
        `;
    }
    return tableBody;
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
