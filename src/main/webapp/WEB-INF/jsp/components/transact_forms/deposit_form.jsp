<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="card deposit-card ">
            <div class="card-body">



             <form action="/transact/deposit" method = "POST" class="deposit-form">

                <div class="form-group mb-2">
                    <label for="">Enter Deposit Amount in Naira</label>
                    <input type="number" name="deposit_amount" class="form-control" placeholder="Amount to be deposited in Naira" min="5000" required/>
                    </div>

                <div class="form-group mb-2">
                          <label for="">Select Account</label>
                          <select name="account_id" class="form-control">
                          <option value="">Select Account to be Credited</option>
                          <c:if test = "${userAccounts != null}">
                             <c:forEach items="${userAccounts}" var="selectAccount">
                             <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                             </c:forEach>
                          </c:if>
                          </select>

                      </div>


       <div class="form-group mb-2">
                    <label for="">Select Topup Channel</label>
                    <select name="wallet-type" class="form-control" id="wallet-type">
                     <option value="">Select Crypto Wallet</option>
                     <option value="btc">BTC</option>
                     <option value="eth">Ethereum</option>
                     <option value="bnb">Binance Token(BNB)</option>
                     <option value="bank">Bank Transfer</option>
                 </select>

       </div>



               <div class="form-group crypto-details2 my-2">
                   <button id="transact-btn" class="btn btn-md transact-btn">Send</button>
               </div>

             </form>



            </div>
        </div>
