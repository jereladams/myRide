<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<c:import url="head.jsp" />

<body>

<c:import url="header.jsp" />

<div class="container">

    <h4><b>Whoops. Something went wrong.</b></h4>

    ${response.getStatus()}

    <form class="rendered-form" action="index.jsp">
        <br>
        <input type="submit" value="Back" name="Back" id="back" />
        <br>
        <br>
    </form>

</div>

<c:import url="footer.jsp" />

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>

</body>
</html>





