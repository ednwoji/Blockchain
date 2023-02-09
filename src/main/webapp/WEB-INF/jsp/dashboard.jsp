<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap/css/bootstrap.css">
    <link rel = "stylesheet" href = "../css/fontawesome/css/all.css">
    <link rel="stylesheet" href="../css/main.css">
    <script src="../js/bootstrap.bundle.js"></script>

    <script
    	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
    	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script
    	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="../js/post.js"></script>



    <title>Dashboard</title>
</head>
<body>


        <marquee scrolldelay="40">This application converts Dollar at current rate of <b>${dollarRate}</b> naira and is automatically updated every second </marquee>


        <c:import url = "components/incl/header.jsp"/>
        <!--Transact Offcanvas: pulls from the left -->
        <c:import url = "components/transact_offcanvas.jsp"/>

        <!--Add Accounts Offcanvas: pulls from the left -->
        <c:import url = "components/add_account_offcanvas.jsp"/>

           <div class="container">

           <c:if test="${success != null}">

                     <div class="alert alert-info text-center border border-info">

                          <b>${success}</b>
                     </div>

                               </c:if>

            <c:if test="${transAddress != null}">

                                 <div class="alert alert-info text-center border border-info">

                                 Please send ${convertedAmount} BTC to this account:<h5 id = "address">${transAddress}</h5>

                                      <!-- The button used to copy the text -->
                                      <button onclick="myFunction()">Click to copy Address</button> <br/><br/>
                                     Scan Code below to Also Make payment<br/>
                                     <img src="${qrcode}" alt="QR code">
                                 </div>



            </c:if>

            <c:if test="${ethTransAddress != null}">

                                    <div class="alert alert-info text-center border border-info">

                                     Please send ${ethConvertedAmount} ETH to this account:<h5 id = "address">${ethTransAddress}</h5>

                                        <!-- The button used to copy the text -->
                                     <button onclick="myFunction()">Click to copy Address</button> <br/><br/>
                                     Scan Code below to Also Make payment<br/>
                                     <img src="${qrcode}" alt="QR code">

                                     </div>

             </c:if>


                        <c:if test="${error != null}">

                             <div class="alert alert-info text-center border border-info">

                             <b>${error}</b>
                             </div>

                                 </c:if>
           </div>

        <c:choose>
            <c:when test="${fn:length(userAccounts) > 0}">
                  <c:import url = "components/accounts_display.jsp"/>
            </c:when>
            <c:otherwise>
                  <c:import url = "components/no_accounts_display.jsp"/>
            </c:otherwise>
        </c:choose>

<script>

function myFunction() {
  // Get the text field
  const body = document.querySelector('body');
    const paragraph = document.querySelector('#address');
    const area = document.createElement('textarea');
    body.appendChild(area);

    area.value = paragraph.innerText;
    area.select();
    document.execCommand('copy');
    body.removeChild(area);

    alert("Copied the text: " + copyText.value);
}

</script>


<script src="../js/main.js"></script>
<script src="../js/get.js"></script>

<script>
document.getElementById('nightModeButton').addEventListener('click', function() {
  document.body.classList.toggle('night-mode');
});
</script>

</body


</html>



