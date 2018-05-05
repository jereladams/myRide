<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<c:import url="head.jsp" />

<html>

<title>Parts</title>

<body>
<c:import url="header.jsp" />

<div class="container">

    <h4><b>Repair:</b></h4>

    <table class="table table-striped">
        <tr>
            <th>ServiceDate</th>
            <th>ServiceProvider</th>
            <th>ServicePerformed</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>

        <tr>
            <td>${repair.serviceDate}</td>
            <td>${repair.serviceProvider}</td>
            <td>${repair.servicePerformed}</td>
            <td>${repair.description}</td>
            <td>

                <c:url var="indexUrl" value="repairs" />
                <a href="${indexUrl}">
                    Back to Repairs
                </a>

            </td>
        </tr>
    </table>


    <h4><b>Parts:</b></h4>

    <table class="table table-striped">
        <tr>
            <th>ID</th>
            <th>Part</th>
            <th>Manufacturer#</th>
            <th>Part#</th>
            <th>Warranty</th>
            <th>Supplier</th>
            <th>Price</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="part" items="${parts}" >
            <tr>
                <td>${part.id}</td>
                <td>${part.partName}</td>
                <td>${part.manufacturer}</td>
                <td>${part.partNumber}</td>
                <td>${part.warranty}</td>
                <td>${part.supplier}</td>
                <td>${part.price}</td>
                <td>${part.description}</td>

                <td>
                    <c:url var="editUrl" value="/parts" />
                    <a href="${editUrl}?partid=${part.id}">
                        Edit
                    </a>

                    &nbsp;&nbsp;&nbsp;&nbsp;

                        <c:url var="deleteUrl" value="/parts" />
                    <a href="${deleteUrl}?partid=${part.id}&action=delete">
                        Delete
                    </a>
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

