<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<c:import url="head.jsp" />
<html>
<body>
<c:import url="header.jsp" />

<div class="container">

    <h4><b>Cars:</b></h4>

    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Year</th>
            <th>Make</th>
            <th>Model</th>
            <th>VIN</th>
            <th>ACTIONS</th>
        </tr>

        <c:forEach var="car" items="${cars}" >
            <tr>
                <td>${car.id}</td>
                <td>${car.year}</td>
                <td>${car.make}</td>
                <td>${car.model}</td>
                <td>${car.vin}</td>

                <td>
                <a href="cars?carid=${car.id}&action=delete">Delete</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="vin?vin=${car.vin}&carid=${car.id}">View Recalls</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="repairs?carid=${car.id}&action=list">View Repairs</a>
                </td>
             </tr>
        </c:forEach>

     </table>
</div>

<c:import url="footer.jsp" />

</body>
</html>
