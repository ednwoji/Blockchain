<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="card transfer-card ">
            <div class="card-body">


            <div class="alert alert-warning" role="alert" id="alert">
            Do you agree to additional # ${charges} deduction from your account? <br/>
              <button type="button" class="btn btn-secondary btn-sm" id="yesButton">Yes</button>
              <button type="button" class="btn btn-secondary btn-sm" id="noButton">No</button>
            </div>


             <form action="/transact/transfer" method = "POST" id="transferFundsForm" class="deposit-form">

        <div class="form-group mb-2">
                    <label for="">Enter Transfer Amount</label>
                    <input type="text" name="deposit_amount" id="deposit_amount" class="form-control"  placeholder="Enter Deposit Amount" required/>
                    </div>

        <div class="form-group mb-2">
                    <label for="deposit_account">Enter Account Number to be Credited</label>
                    <input type="text" name="deposit_account" id = "deposit_account" class="form-control" placeholder="Enter Account Number" onchange="makeAjaxPostCall()" required/>
                    </div>



       <div class="form-group">
           <label for="">Select Account</label>
           <select name="account_id" id="account_id"  class="form-control">
           <option value="">Select Account to be Debited</option>
           <c:if test = "${userAccounts != null}">
              <c:forEach items="${userAccounts}" var="selectAccount">
              <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
              </c:forEach>
           </c:if>
           </select>

       </div>

        <div class="form-group mb-2">
                           <label for="deposit_account">Account Name</label>
                           <input type="text" name="beneficiary" id = "beneficiary" class="form-control" placeholder="Beneficiary Name" required/>
                           </div>



        </form>

        <div class="form-group my-2">
                   <button id="transactbtn" type="button" class="btn btn-md transact-btn">Transfer</button>
        </div>


        <script>


        $('#alert').hide(); // hide the alert

        $('#transactbtn').click(function () {

        $('#alert').show();

        });

        $('#yesButton').click(function() {
          $('#transferFundsForm').submit(); // submit the form
        });

        $('#noButton').click(function() {
          $('#alert').hide(); // hide the alert
        });





	    function makeAjaxPostCall() {

				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
		};

		function ajaxPost() {

                var depositValue = $("#deposit_account").val();
				// PREPARE FORM DATA
				var formData = {
                	deposit_account : depositValue
                }

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "getCustomer",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
                        	$('#beneficiary').val(result.data);

                        }
                        else if (result.status == "failed") {
							$('#beneficiary').val(result.data);
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

    </script>

            </div>
        </div>


