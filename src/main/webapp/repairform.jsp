<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<c:import url="head.jsp" />
<body>

<c:import url="header.jsp" />

<div class="container">

    <c:if test="${repair!= null}">
    <form action="repairs?action=update" method="post">
    </c:if>

    <c:if test="${repair == null}">
    <form action="repairs?action=insert" method="post">
    </c:if>

    <form id="rendered-form">
        <div class="rendered-form">

            <c:if test="${repair != null}">
                <input type="hidden" name="carid" value="${repair.car.id}">
            </c:if>

            <c:if test="${repair == null}">
                <input type="hidden" name="carid" value="${carid}">
            </c:if>

            <input type="hidden" name="repairid" value="${repair.id}">

            <div class="fb-text form-group field-date">
                <label for="servicedate" class="fb-text-label">ServiceDate</label>
                <input type="date" class="form-control" name="servicedate" maxlength="50" id="servicedate" value="${repair.serviceDate}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="invoicenumber" class="fb-text-label">Invoice#</label>
                <input type="text" class="form-control" name="invoicenumber" maxlength="15" id="invoicenumber" value="${repair.invoiceNumber}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="serviceprovider" class="fb-text-label">ServiceProvider</label>
                <input type="text" class="form-control" name="serviceprovider" maxlength="50" id="serviceprovider" value="${repair.serviceProvider}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="laborcost" class="fb-text-label">Cost</label>
                <input type="text" class="form-control" name="laborcost" maxlength="8" id="laborcost" value="${repair.laborCost}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="currentmileage" class="fb-text-label">CurrentMileage</label>
                <input type="text" class="form-control" name="currentmileage" maxlength="10" id="currentmileage" value="${repair.currentMileage}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="warranty" class="fb-text-label">Warranty</label>
                <input type="text" class="form-control" name="warranty" maxlength="100" id="warranty" value="${repair.warranty}">
            </div>
            <div class="fb-text form-group field-text">
            <label for="serviceperformed" class="fb-text-label">ServicePerformed</label>
            <input type="text" class="form-control" name="serviceperformed" maxlength="100" id="serviceperformed" value="${repair.servicePerformed}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="description" class="fb-text-label">Description</label>
                <input type="text" class="form-control" name="description" maxlength="100" id="description" value="${repair.description}">
            </div>

            <button><span>Submit</span></button>

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
