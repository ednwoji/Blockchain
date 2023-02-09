$(document).ready(
		function() {

			// SUBMIT FORM
		    $("#account_idsss").on('change', function () {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

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

		})










