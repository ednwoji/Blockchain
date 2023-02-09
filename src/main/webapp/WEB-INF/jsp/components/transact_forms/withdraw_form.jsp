<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <div class="card withdraw-card ">
            <div class="card-body">


             <form action="/transact/send" method = "POST" class="withdraw-form">

                <div class="form-group mb-2">
                    <label for="">Enter Amount</label>
                    <input type="text" name="withdraw_amount" class="form-control" placeholder="Enter Transfer Amount"/>
                    </div>


                <div class="form-group mb-2">
                          <label for="">Select Account</label>
                          <select name="account_id" class="form-control">
                          <option value="">Select Account to transfer from</option>
                          <c:if test = "${userAccounts != null}">
                             <c:forEach items="${userAccounts}" var="selectAccount">
                             <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                             </c:forEach>
                          </c:if>
                          </select>

                      </div>

              <div class="form-group mb-2">
                                  <label for="">Account number</label>
                                  <input type="text" name="transferAccount" id="transferAccountNumber" class="form-control" placeholder="Enter Account number"/>
                                  </div>

              <div class="form-group mb-2">
                                       <label for="">Bank Name</label>
                                       <select name="bankList" id="bankList" class="form-control">
                                       <option value="">Select Bank Name</option>
                                       <c:if test = "${bankList != null}">
                                          <c:forEach items="${bankList}" var="selectBank">
                                          <option value="${selectBank.code}" id="${selectBank.code}">${selectBank.name}</option>
                                          </c:forEach>
                                       </c:if>
                                       </select>
                                   </div>

                 <div class="form-group mb-2">
                  <label for="">Account Name</label>
                 <input type="text" name="transferName" id="transferName"  class="form-control" placeholder="Enter Account Name" readonly/>
                </div>


               <div class="form-group my-2">
                   <button id="transact-btn" class="btn btn-md transact-btn">Transfer</button>
               </div>

             </form>



            </div>
        </div>

<script>

$(document).ready(
		function() {

			$("#bankList").on('change', function() {

				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

                var selectedValue = $("#bankList").val();
                var account = $("#transferAccountNumber").val();
				// PREPARE FORM DATA
				var formData = {
                	accountNumber : account,
                	bankCode : selectedValue
                }

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "getRecipient",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.data.account_name != null )
						{
                         $('#transferName').val(result.data.account_name);
                        }
                        else {
							$("#postsResultDiv").html("Wrong Account Number");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})


</script>
