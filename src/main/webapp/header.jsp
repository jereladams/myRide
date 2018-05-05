<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Header -->
<div class="allcontain">

    <div class="header">
         <ul class="logreg">

             <c:if test="${pageContext.request.remoteUser==null}">
                 <li><a href="cars">Login</a></li>
                 <li><a href="userform.jsp">Register</a></li>
            </c:if>

             <c:if test="${pageContext.request.remoteUser!=null}">
                 <li><a href="users?action=logout">Logout</a></li>
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 <li> Logged in as: <a href="users?action=edit">${pageContext.request.remoteUser}</a></li>
             </c:if>

        </ul>
    </div>

    <!-- Navbar Up -->
    <nav class="topnavbar navbar-default topnav">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed toggle-costume" data-toggle="collapse" data-target="#upmenu" aria-expanded="false">
                    <span class="sr-only"> Toggle navigaion</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
        </div>

        <div class="collapse navbar-collapse" id="upmenu">
            <ul class="nav navbar-nav" id="navbarontop">
                <li class="active"><a href="index.jsp">HOME</a> </li>
                <li><a href="cars?action=list">VIEW GARAGE</a></li>

                <c:if test="${pageContext.request.isUserInRole('Admin')}">
                    <li><a href="users?action=list">VIEW USERS</a></li>
                </c:if>

                </li>

            </ul>
        </div>
    </nav>
</div>
