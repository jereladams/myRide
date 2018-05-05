<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<c:import url="head.jsp" />

<html>

<title>Repairs</title>

<body>
<c:import url="header.jsp" />

<div class="container">

    <h4><b>Car:</b></h4>

<table class="table table-striped">
    <tr>
        <th>Year</th>
        <th>Make</th>
        <th>Model</th>
        <th>VIN</th>
        <th>Actions</th>
    </tr>

    <tr>
        <td>${year}</td>
        <td>${make}</td>
        <td>${model}</td>
        <td>${vin}</td>
        <td>
        <a href="repairs?carid=${carid}&action=new">Add Repair</a>
        </td>
   </tr>
</table>


    <h4><b>Repairs:</b></h4>

    <table class="table table-striped">
        <tr>
            <th>ServiceDate</th>
            <th>Invoice#</th>
            <th>ServiceProvider</th>
            <th>Cost</th>
            <th>CurrentMileage</th>
            <th>Warranty</th>
            <th>ServicePerformed</th>
            <th>Description</th>
            <th>Actions</th>
         </tr>

        <c:forEach var="repair" items="${repairs}" >
            <tr>
                <td>${repair.serviceDate}</td>
                <td>${repair.invoiceNumber}</td>
                <td>${repair.serviceProvider}</td>
                <td>${repair.laborCost}</td>
                <td>${repair.currentMileage}</td>
                <td>${repair.warranty}</td>
                <td>${repair.servicePerformed}</td>
                <td>${repair.description}</td>
                <td>

                <a href="repairs?carid=${carid}&repairid=${repair.id}&action=delete">Delete</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="repairs?carid=${carid}&repairid=${repair.id}&action=edit">Edit</a>

                <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <%--<a href="parts?carid=${carid}&repairid=${repair.id}&action=list">Parts</a>--%>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<c:import url="footer.jsp" />

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
</body>
</html>
