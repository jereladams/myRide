<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="allcontain">

    <nav class="navbar navbar-default midle-nav">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed textcostume" data-toggle="collapse" data-target="#navbarmidle" aria-expanded="false">
                <h1>SEARCH</h1>
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="container">
            <form action="vin" method="GET">

                <div class="collapse navbar-collapse" id="navbarmidle">
                    <div class="rendered-form">

                        <div class="searchtxt">
                            <h1>SEARCH VEHICLES</h1>
                        </div>

                            <c:if test="${fn:length(errorMessages) gt 0}">

                                 <c:forEach var="error" items="${errorMessages}" >
                                        <div class="alert alert-warning">
                                            <strong>Message: </strong>${error}
                                        </div>
                                 </c:forEach>

                                 <c:remove var="errorMessages" scope="request" />

                            </c:if>

                        <div>
                            <input type="text" name="vin" id="vin" class="form-control searchform" maxlength="17" minlength="17" required placeholder="VIN">
                        </div>
                        <br>
                        <button><span>Submit</span></button>

                     </div>
                </div>

            </form>
        </div>
    </nav>



    <div id="carousel-up" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner " role="listbox">
            <div class="item active">
                <img src="image/1.jpg" alt="bg">
            </div>
        </div>
    </div>
</div>

