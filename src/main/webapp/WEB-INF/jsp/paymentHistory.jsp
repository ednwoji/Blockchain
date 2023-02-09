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



        <c:import url = "components/incl/header.jsp"/>

<div class ="container">

<c:if test="${requestScope.payment_history != null}">
        <table class="table text-center table-striped">
            <tr>
                <th>Record Number</th>
                <th>Beneficiary</th>
                <th>Beneficiary Account Number</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Reference</th>
                <th>Reason Code</th>
                <th>Created at</th>
            </tr>


            <c:forEach items="${requestScope.payment_history}" var="payments">
                <tr>
                    <td>${payments.payment_id}</td>
                    <td>${payments.beneficiary}</td>
                    <td>${payments.beneficiary_acc_no}</td>
                    <td>${payments.amount}</td>
                    <td>${payments.status}</td>
                    <td>${payments.reference_no}</td>
                    <td>${payments.reason_code}</td>
                    <td>${payments.created_at}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


</div>











        <script src="../js/main.js"></script>
        <script src="../js/get.js"></script>