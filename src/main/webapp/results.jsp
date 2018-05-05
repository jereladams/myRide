<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<c:import url="head.jsp" />
<html>
<body>
<c:import url="header.jsp" />

<div class="container">

    <h4><b>Search Results:</b> ${recalls.size()} recall(s) found.</h4>

    <table class="table table-striped">
        <tr>
            <th>VIN</th>
            <th>Year</th>
            <th>Make</th>
            <th>Model</th>
            <th>Trim</th>
            <th>Actions</th>
        </tr>
        <tr>
            <td>${vin}</td>
            <td>${year}</td>
            <td>${make}</td>
            <td>${model}</td>
            <td>${trim}</td>

            <td>

            <c:if test="${carid==null}">
                <a href="cars?vin=${vin}&year=${year}&make=${make}&model=${model}&action=insert">Add to Garage</a>
            </c:if>

            <c:if test="${carid!=null}">
                <a href="cars?action=list">Back to Garage</a>
            </c:if>

            &nbsp;&nbsp;&nbsp;&nbsp;
            <c:url var="indexUrl" value="/" />
            <a href="${indexUrl}">
                 Back to Search
            </a>
            </td>

        </tr>
      </table>

    <br>

        <c:forEach var="recallItem" items="${recalls}">
            <table class="table table-striped">
                <tr>
                    <th>ReportDate</th>
                    <th>NHTSA Campaign Number</th>
                    <th>Component</th>
                </tr>

                <tr>
                    <td>${recallItem.reportReceivedDate}</td>
                    <td>${recallItem.NHTSACampaignNumber}</td>
                    <td>${recallItem.component}</td>
                </tr>

            </table>

            <p>
            <b>Problem:</b>
            ${recallItem.summary}
            </p>

            <p><b>Consequence:</b>
            ${recallItem.conequence}
            </p>

            <p>
            <b>Remedy:</b>
            ${recallItem.remedy}
            </p>

            <p>
            <b>Notes:</b>
            ${recallItem.notes}
            </p>

            <br>
            <br>

    </c:forEach>


</div>

<c:import url="footer.jsp" />

</body>
</html>

