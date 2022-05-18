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
    htmlElements.categories.querySelectorAll("input").forEach(
        (inp) => inp.addEventListener("click", refreshProducts));
    htmlElements.suppliers.addEventListener("click", refreshProducts);

}

function getSelectedIds(checkBoxList) {
    let idList = []
    checkBoxList.querySelectorAll("li input")
        .forEach(checkBox => {
            if (checkBox.checked) idList.push(checkBox.getAttribute(attributes.id))
        });
    return idList;
}

function changeCheckboxAvailability(availableProductCategories) {
    disableAllCategoryCheckbox();
    htmlElements.categories.querySelectorAll("input").forEach(inp => {
        availableProductCategories.forEach(availableCategoryValue => {
            if (inp.disabled === true && availableCategoryValue === +inp.value) {
                inp.disabled = false;
            }
        })
    });
}

function disableAllCategoryCheckbox() {
    htmlElements.categories.querySelectorAll("input").forEach((inp) => inp.disabled = true);
}

async function refreshProducts() {
    let supplierIdList = getSelectedIds(htmlElements.suppliers);
    let categoryIdList = getSelectedIds(htmlElements.categories);
    const refreshedProducts = await dataHandler.getProducts(supplierIdList, categoryIdList);
    changeCheckboxAvailability(refreshedProducts.availableProductCategories);
    changeProducts(refreshedProducts.productsByFilter);
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

function changeProducts(products) {
    htmlElements.productContainer.innerHTML = "";
    for (const product of products) {
        const element = createProductElement(product)
        htmlElements.productContainer.insertAdjacentHTML("beforeend", element)
    }
}


addEventListeners();
