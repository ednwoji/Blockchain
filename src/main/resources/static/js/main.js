const transactType = document.querySelector("#transact-type");

const paymentCard = document.querySelector(".payment-card");
const transferCard = document.querySelector(".transfer-card");
const depositCard = document.querySelector(".deposit-card");
const withdrawCard = document.querySelector(".withdraw-card");


const walletType = document.querySelector('#wallet-type');
const cryptoAmount = document.querySelector('.crypto-details');

transactType.addEventListener("change", function(){

    switch(transactType.value){
        case "payment":
            paymentCard.style.display = "block";
            paymentCard.nextElementSibling.style.display = "none";
            depositCard.style.display = "none";
            withdrawCard.style.display = "none";
        break;

        case "transfer":
            transferCard.previousElementSibling.style.display = "none";
            transferCard.style.display = "block";
            transferCard.nextElementSibling.style.display = "none";
            depositCard.style.display = "none";
            withdrawCard.style.display = "none";
       
        break;

        case "deposit":
            depositCard.previousElementSibling.style.display = "none";
            depositCard.style.display = "block";
            depositCard.nextElementSibling.style.display = "none";
            paymentCard.style.display = "none";
            transferCard.style.display = "none";
        break;

        case "withdraw":
            transferCard.style.display = "none";
            withdrawCard.previousElementSibling.style.display = "none";
            withdrawCard.style.display = "block";
            withdrawCard.nextElementSibling.style.display = "none";
            
        break;
    }

   
});




walletType.addEventListener("change", function(){

    switch(walletType.value){
        case "btc":
            cryptoAmount.style.display = "block";
//            cryptoAmount.value = 1000;
        break;

         case "eth":
             cryptoAmount.style.display = "block";
        //   cryptoAmount.value = 1000;
         break;

          case "bnb":
              cryptoAmount.style.display = "block";
         //   cryptoAmount.value = 1000;
          break;
    }


});




