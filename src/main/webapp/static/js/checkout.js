const checkout = {
    init(){
        this.checkConfirmEmail();
    },

    checkConfirmEmail(){
        let confirmInputField = document.querySelector("#email-confirmation");
        confirmInputField.addEventListener("input", this.confirmEmailEventListener)
    },

    confirmEmailEventListener(e){
        let providedEmail = document.querySelector("#email").value;
        let container = document.querySelector(".email-match");
        container.removeAttribute("hidden");
        if (!(providedEmail === e.currentTarget.value)){
            container.innerText = "Emails are not match!!"
        }
        if (providedEmail === e.currentTarget.value){
            container.innerText = "Emails match!"
        }
    }

}

checkout.init();