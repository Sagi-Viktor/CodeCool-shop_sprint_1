export const dataHandler = {
    addProductToCart: async function (productId) {
        await apiPost("/api/add-to-cart", productId);
    },

    getProducts: async function(supplierId, categoryId) {
        return await apiGet(`api/products${createQueryParams(supplierId, categoryId)}`)
    },
}

function createQueryParams(supplierId, categoryId) {
    let query = "";
    if (supplierId.length > 0) {
        if (categoryId.length > 0) {
            query = `?supplier_id=${supplierId.toString()}&category_id=${categoryId.toString()}`;
        } else {
            query = `?supplier_id=${supplierId.toString()}`;
        }
    } else if (categoryId.length > 0) {
        query = `?category_id=${categoryId.toString()}`;
    }
    return query;
}

async function apiGet(url) {
    const response = await fetch(url,
        {
            method: "GET",
        });
    if (response.status <= 299) {
        return response.json();
    }
}

async function apiPost(url, data, returnValue = false) {
    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });
    if (returnValue) {
        return await response.json();
    }
}


async function apiPut(url, data) {
    await fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });
}

async function apiDelete(url) {
    await fetch(url, {
        method: "DELETE"
    });
}
