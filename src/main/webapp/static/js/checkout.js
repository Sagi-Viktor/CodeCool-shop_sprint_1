const checkout = {
    init(){
        this.confirmEmail();
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

}

checkout.init();