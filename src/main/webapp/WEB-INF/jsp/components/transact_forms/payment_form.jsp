<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <div class="card payment-card ">
            <div class="card-body">


               <div id="postResultsDiv">
                </div>

            <form action="/transact/payment" method = "POST" class="deposit-form">
                <div class="form-group mb-2">
                    <label for="">Mobile/Meter Number</label>
                    <input type="text" name="number" id="number" class="form-control" placeholder="Enter Mobile/Meter Number"/>
                </div>



                <div class="form-group mb-2">
                                    <label for="">Select Bills to pay</label>
                                    <select name="billType" class="form-control" id="wallet-type">
                                     <option value="">Select Bill to Pay</option>
                                     <option value="airtime">Airtime Purchase</option>
                                     <option value="data">Data Purchase</option>

                                 </select>

                       </div>

                       <div class="form-group mb-2">
                                    <label for="">Select Service Provider</label>
                                    <select name="serviceProvider" class="form-control" id="wallet-type">
                                    <option value="">Service Providers</option>
                                    <option value="mtn">MTN</option>
                                    <option value="airtel">Airtel</option>
                                    <option value="glo">GLO</option>
                                     <option value="etisalat">Etisalat</option>

                                    </select>

                       </div>

                <div class="form-group mb-2">
                                          <label for="">Select Account</label>
                                          <select name="account_id" class="form-control">
                                          <option value="">Select Account to be Debited</option>
                                          <c:if test = "${userAccounts != null}">
                                             <c:forEach items="${userAccounts}" var="selectAccount">
                                             <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                                             </c:forEach>
                                          </c:if>
                                          </select>

                 </div>

                            <div class="form-group mb-2">
                                <label for="">Enter Amount</label>
                                <input type="text" name="amount" class="form-control" placeholder="Enter Payment Amount"/>
                                </div>

               <div class="form-group mb-2">
                   <button id="transact-btn" class="btn btn-md transact-btn">Pay</button>
               </div>

               </form>



            </div>
        </div>
