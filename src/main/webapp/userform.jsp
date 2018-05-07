<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<c:import url="head.jsp" />
<body>
<c:import url="header.jsp" />

<div class="container">

    <c:if test="${user != null}">

    <h4><b>Edit User</b></h4>

    <form action="users?action=update" method="post">
        <form id="rendered-form">

            <div class="fb-text form-group field-email">
                <label for="email" class="fb-text-label">Email<span class="fb-required"></span></label>
                <input type="email" class="form-control" name="email" id="email" readonly="readonly" value="${user.email}">
            </div>
            <div class="fb-text form-group field-password">
                <label for="password" class="fb-text-label">New Password<span class="fb-required">*</span></label>
                <input type="password" class="form-control" name="password" minlength="6" maxlength="25" id="password" required="required">
            </div>
            <div class="fb-text form-group field-confirm">
                <label for="confirm" class="fb-text-label">Confirm Password<span class="fb-required">*</span></label>
                <input type="password" class="form-control" name="confirm" minlength="6" maxlength="25" id="confirm" required="required">
            </div>

            <input type="button" name="cancel" value="Cancel" onClick="window.location.href='index.jsp';" />

            <button><span>Submit</span></button>

        </form>
    </c:if>

    <c:if test="${user == null}">

    <h4><b>Register</b></h4>

    <form action="users?action=insert" method="post">
        <form id="rendered-form">

            <div class="fb-text form-group field-email">
                <label for="email" class="fb-text-label">Email<span class="fb-required">*</span></label>
                <input type="email" class="form-control" name="email" maxlength="50" id="email" required="required">
            </div>
            <div class="fb-text form-group field-password">
                <label for="password" class="fb-text-label">Password<span class="fb-required">*</span></label>
                <input type="password" class="form-control" name="password" minlength="6" maxlength="25" id="password" required="required">
            </div>
            <div class="fb-text form-group field-confirm">
                <label for="confirm" class="fb-text-label">Confirm Password<span class="fb-required">*</span></label>
                <input type="password" class="form-control" name="confirm" minlength="6" maxlength="25" id="confirm" required="required">
            </div>

            <input type="button" name="cancel" value="Cancel" onClick="window.location.href='index.jsp';" />

            <button><span>Submit</span></button>

        </form>
    </c:if>
</div>

<c:import url="footer.jsp" />

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> <script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
</body>

</html>