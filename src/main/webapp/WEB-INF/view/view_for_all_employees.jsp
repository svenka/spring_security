<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>

<h3>Information for all employees</h3>
<br><br>
<security:authorize access="hasRole('HR')">
<input type="button" value="salary" onClick="window.location.href='hr_info'">
Only for Hr staff
</security:authorize>
<br>
<security:authorize access="hasRole('Manager')">
<input type="button" value="performance" onClick="window.location.href='manager_info'">
Only for Manager staff
</security:authorize>
</body>
</html>