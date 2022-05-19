import {dataHandler} from "./dataHandler.js";


const htmlElements = {
    categories: document.querySelector("ul#category-list"),
    suppliers: document.querySelector("ul#supplier-list"),
    productContainer: document.querySelector("div#products"),
}

function main() {
    addEventListeners();
}

function addEventListeners() {
    htmlElements.categories.querySelectorAll("input").forEach(
        (checkbox) => checkbox.addEventListener("click", refreshProducts));
    htmlElements.suppliers.querySelectorAll("input").forEach(
        (checkbox) => checkbox.addEventListener("click", refreshProducts));

}

function getSelectedIds(checkBoxList) {
    let idList = []
    checkBoxList.querySelectorAll("li input")
        .forEach(checkBox => {
            if (checkBox.checked) {
                idList.push(checkBox.getAttribute("value"));
                checkBox.setAttribute("data-checked", "true");
            } else {
                checkBox.setAttribute("data-checked", "false");
            }
        });
    return idList;
}

function changeCheckboxAvailability(numberOfProductsInCategories) {
    disableAllCategoryCheckbox();
    htmlElements.categories.querySelectorAll("label.checkbox-label").forEach(checkboxLabel => {
        changeCheckboxCondition(checkboxLabel, numberOfProductsInCategories);
        // if (checkbox.disabled) {numberOfProducts.innerHTML = '0';}
        changeCheckboxCheckedCondition(checkboxLabel);
    });
}

function changeCheckboxCondition(checkboxLabel, numberOfProductsInCategories) {
    let checkbox = checkboxLabel.children.namedItem("category-filter");
    let numberOfProducts = checkboxLabel.querySelector("em");
    for (let [categoryValue, numberOfAvailableProducts] of Object.entries(numberOfProductsInCategories)) {
        if (checkbox.disabled === true && categoryValue === checkbox.value) {
            checkbox.disabled = false;
            numberOfProducts.innerHTML = numberOfAvailableProducts;
        }
    }
}

function changeCheckboxCheckedCondition(checkboxLabel) {
    let checkbox = checkboxLabel.children.namedItem("category-filter");
    if (checkbox.getAttribute("data-checked") === "true" && checkbox.disabled) {
        checkbox.checked = false;
    }
}

function disableAllCategoryCheckbox() {
    htmlElements.categories.querySelectorAll("input").forEach((inp) => inp.disabled = true);
}

async function refreshProducts() {
    let supplierIdList = getSelectedIds(htmlElements.suppliers);
    let categoryIdList = getSelectedIds(htmlElements.categories);
    const refreshedProducts = await dataHandler.getProducts(supplierIdList, categoryIdList);
    changeCheckboxAvailability(refreshedProducts.numberOfProductsInCategories);
    changeProducts(refreshedProducts.productsByFilter);
}

function changeProducts(products) {
    htmlElements.productContainer.innerHTML = "";
    for (const product of products) {
        const element = createProductElement(product);
        htmlElements.productContainer.insertAdjacentHTML("beforeend", element);
    }
}

function createProductElement(product) {
    return `<div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="" src='/static/img/${product.imageName}' alt=""/>
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-supplier">${product.supplier.name}</p>
                    <p class="card-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success" href="#">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>`;
}

main();
