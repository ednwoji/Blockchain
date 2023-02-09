<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap/css/bootstrap.css">
    <link rel = "stylesheet" href = "css/fontawesome/css/all.css">
   <style>


        * {

            box-sizing: border-box;
            font-family: Comfortaa;
        }
       body {

        height: 100vh;
        background-image: url("./images/BankingImage.jpg");
        background-size: cover;
        background-repeat: no-repeat;
       }

       .card .card-text {

        font-size: 16px;
       }
   </style>
    <title>Errors</title>
</head>
<body class="d-flex align-items-center justify-content-center">

    <div class="card col-4 alert alert-danger border border-danger text-danger">

        <h3 class="card-title">

            <i class="fa fa-window-close me-2"></i>Errors:
            <hr>
            <div class="card-body">
                <p class="card-text">

                    <c:if test="${requestScope.error != null}">

                                    <div class="alert alert-danger text-center border border-danger">

                                    <b>${requestScope.error}</b>
                                    </div>

                                    </c:if>

                </p>

                <hr>
                <a href="/login" class="btn btn-sm btn-danger">
                    <i class="fa fa-arrow-alt-circle-left me-1"></i>Back
                </a>
            </div>
        </h3>


    </div>

</body>
</html>