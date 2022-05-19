import {dataHandler} from "./dataHandler.js";

const attributes = {
    id: "value",
}
const htmlElements = {
    categories: document.querySelector("ul#category-list"),
    suppliers: document.querySelector("ul#supplier-list"),
    productContainer: document.querySelector("div#products"),
}

function addEventListeners() {
    htmlElements.categories.addEventListener("click", refreshProducts);
    htmlElements.suppliers.addEventListener("click", refreshProducts);

}

function getSelectedIds(checkBoxList) {
    let ids = []
    checkBoxList.querySelectorAll("li input")
        .forEach(checkBox => {
            if (checkBox.checked) ids.push(checkBox.getAttribute(attributes.id))
        });
    return ids;
}

async function refreshProducts() {
    let supplierId = [];
    let categoryId = [];
    supplierId = getSelectedIds(htmlElements.suppliers);
    categoryId = getSelectedIds(htmlElements.categories);
    const refreshedProducts = await dataHandler.getProducts(supplierId, categoryId);
    console.log(refreshedProducts);
}

addEventListeners();
