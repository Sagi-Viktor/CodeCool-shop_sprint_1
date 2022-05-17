export const dataHandler = {
    addProductToCart: async function (product) {
        await apiPost("/api/add-to-cart", product);
    }

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
