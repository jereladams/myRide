<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<c:import url="head.jsp" />
<html>
<body>
<c:import url="header.jsp" />

<div class="container">

    <h4><b>User List:</b></h4>

        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>DateAdded</th>
                <th>Action</th>
            </tr>

             <c:forEach var="user" items="${users}" >
                  <tr>
                      <td>${user.id}</td>
                      <td>${user.email}</td>
                      <td>${user.createTime}</td>
                      <td>
                          <a href="users?userid=${user.id}&action=delete">
                             Delete
                          </a>
                      </td>
                  </tr>
             </c:forEach>
        </table>
</div>

<c:import url="footer.jsp" />

</body>
</html>

