<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<c:import url="head.jsp" />
<body>

<c:import url="header.jsp" />

<div class="container">
    <form id="rendered-form">
        <div class="rendered-form">
            <div class="fb-text form-group field-date">
                <label for="servicedate" class="fb-text-label">ServiceDate</label>
                <input type="date" class="form-control" name="servicedate" maxlength="50" id="servicedate">
            </div>
            <div class="fb-text form-group field-text">
                <label for="invoicenumber" class="fb-text-label">Invoice#</label>
                <input type="text" class="form-control" name="invoicenumber" maxlength="15" id="invoicenumber">
            </div>
            <div class="fb-text form-group field-text">
                <label for="serviceprovider" class="fb-text-label">ServiceProvider</label>
                <input type="text" class="form-control" name="serviceprovider" maxlength="50" id="serviceprovider">
            </div>
            <div class="fb-text form-group field-text">
                <label for="laborcost" class="fb-text-label">LaborCost</label>
                <input type="text" class="form-control" name="laborcost" maxlength="8" id="laborcost">
            </div>
            <div class="fb-text form-group field-text">
                <label for="currentmileage" class="fb-text-label">CurrentMileage</label>
                <input type="text" class="form-control" name="currentmileage" maxlength="10" id="currentmileage">
            </div>
            <div class="fb-text form-group field-text">
                <label for="warranty" class="fb-text-label">Warranty</label>
                <input type="text" class="form-control" name="warranty" maxlength="2" id="warranty">
            </div>
            <div class="fb-text form-group field-text">
                <label for="serviceperformed" class="fb-text-label">ServicePerformed</label>
                <input type="text" class="form-control" name="serviceperformed" maxlength="100" id="serviceperformed">
            </div>
            <div class="fb-text form-group field-text">
                <label for="description" class="fb-text-label">Description</label>
                <input type="text" class="form-control" name="description" maxlength="100" id="description">
            </div>

            <button><span>Cancel</span></button> <button><span>Submit</span></button>
            </ul>
        </div>
    </form>
</div>

<c:import url="footer.jsp" />

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>

</body>
</html>

