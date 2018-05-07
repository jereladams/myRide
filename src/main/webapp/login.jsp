<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<c:import url="head.jsp" />

<body>

<c:import url="header.jsp" />

<div class="container">

    <h4><b>Login</b></h4>

    <form id="rendered-form" ACTION="j_security_check" METHOD="POST">

        <div class="rendered-form">

            <div class="fb-text form-group field-email">
                <label for="email" class="fb-text-label">Email<span class="fb-required">*</span></label>
                <input type="text" class="form-control" name="j_username" maxlength="50" id="email" required="required" aria-required="true">
            </div>

            <div class="fb-text form-group field-password">
                <label for="password" class="fb-text-label">Password<span class="fb-required">*</span></label>
                <input type="password" class="form-control" name="j_password" minlength="6" maxlength="25" id="password" required="required" aria-required="true">
            </div>

            <input type="button" name="cancel" value="Cancel" onClick="window.location.href='index.jsp';" />

            <button type="submit" name="submit" value="Login">Login</button>
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

