const checkout = {
    init(){
        this.confirmEmail();
        this.checkBoxForSameAddress();
    },

    confirmEmail(){
        let confirmInputField = document.querySelector("#email-confirmation");
        confirmInputField.addEventListener("input", confirmEmailEventListener);

    function confirmEmailEventListener(e){
            let providedEmail = document.querySelector("#email").value;
            let container = document.querySelector(".email-match");
            let submitButton = document.querySelector("#submit-button");
            container.removeAttribute("hidden");

            if (!(providedEmail === e.currentTarget.value)){
                container.innerText = "Emails are not match!!";
                submitButton.setAttribute("disabled", "");
            }
            if (providedEmail === e.currentTarget.value){
                container.innerText = "Emails match!";
                submitButton.removeAttribute("disabled");
            }
        }
    },

    checkBoxForSameAddress(){
        let checkBox = document.querySelector("#same-address");
        checkBox.addEventListener("change", checkBoxEventListener);

        function checkBoxEventListener(){
            let billingContainer = document.querySelector("#billing-info");

            if (billingContainer.getAttribute("hidden") === null){
                billingContainer.setAttribute("hidden", "");
            } else {
                billingContainer.removeAttribute("hidden");
            }

        }
    }
}

checkout.init();