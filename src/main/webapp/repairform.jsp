<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<c:import url="head.jsp" />
<body>

<c:import url="header.jsp" />

<div class="container">

    <c:if test="${action == 'edit'}">
        <h4><b>Edit Repair</b></h4>
        <form action="repairs?action=update" method="post">
    </c:if>

    <c:if test="${action == 'insert'}">
        <h4><b>Add Repair</b></h4>
        <form action="repairs?action=insert" method="post">
    </c:if>

    <form id="rendered-form">
        <div class="rendered-form">

            <c:if test="${fn:length(errorMessages) gt 0}">

                <c:forEach var="error" items="${errorMessages}" >
                    <div class="alert alert-warning">
                        <strong>Message: </strong>${error}
                    </div>
                </c:forEach>

                <c:remove var="errorMessages" scope="session" />

            </c:if>

            <input type="hidden" name="carid" value="${carid}">
            <input type="hidden" name="repairid" value="${repairid}">

            <div class="fb-text form-group field-date">
                <label for="servicedate" class="fb-text-label">ServiceDate</label>
                <input type="date" class="form-control" name="servicedate" maxlength="50" id="servicedate" value="${servicedate}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="invoicenumber" class="fb-text-label">Invoice#</label>
                <input type="text" class="form-control" name="invoicenumber" maxlength="15" id="invoicenumber" value="${invoicenumber}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="serviceprovider" class="fb-text-label">ServiceProvider</label>
                <input type="text" class="form-control" name="serviceprovider" maxlength="50" id="serviceprovider" value="${serviceprovider}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="laborcost" class="fb-text-label">Cost</label>
                <input type="text" class="form-control" name="laborcost" maxlength="8" id="laborcost" value="${laborcost}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="currentmileage" class="fb-text-label">CurrentMileage</label>
                <input type="text" class="form-control" name="currentmileage" maxlength="10" id="currentmileage" value="${currentmileage}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="warranty" class="fb-text-label">Warranty</label>
                <input type="text" class="form-control" name="warranty" maxlength="100" id="warranty" value="${warranty}">
            </div>
            <div class="fb-text form-group field-text">
            <label for="serviceperformed" class="fb-text-label">ServicePerformed</label>
            <input type="text" class="form-control" name="serviceperformed" maxlength="100" id="serviceperformed" value="${serviceperformed}">
            </div>
            <div class="fb-text form-group field-text">
                <label for="description" class="fb-text-label">Description</label>
                <input type="text" class="form-control" name="description" maxlength="100" id="description" value="${description}">
            </div>

            <input type="button" name="cancel" value="Cancel" onClick="window.location.href='repairs?action=list&carid=${carid}';"/>

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
