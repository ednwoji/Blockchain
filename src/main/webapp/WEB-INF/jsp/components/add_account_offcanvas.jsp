<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
      <h5 id="offcanvasRightLabel" class="text-white">Create Account</h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">

        <div class="card">

            <div class="card-body">

            <form action="/account/create_account" method = "POST" class="add-account-form">
                <div class="form-group">
                    <label for="">Account Name</label>
                    <input type="text" name="account_name" class="form-control" placeholder="Enter preferred Account Name" required>
                </div>

                <div class="form-group">
                    <label for="">Account Type</label>
                   <select name="account_type" class="form-control" id="">
                       <option value="">---Select Account Type---</option>
                       <option value="savings">Savings</option>
                       <option value="current">Current</option>
                   </select>
                </div>

                 <div class="form-group my-2">
                    <button id="transact-btn" class="btn btn-md transact-btn">Open Account</button>
                </div>
            </form>
        </div>
        </div>

    </div>
  </div>
