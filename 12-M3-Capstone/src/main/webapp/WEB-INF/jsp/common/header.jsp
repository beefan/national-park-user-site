<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${pageTitle}"/></title>
    <link href="<c:url value="/css/nationalpark.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="header">
		<img src="<c:url value="img/logo.png" />" />
		<nav>
			<a href="<c:url value="/" />" >Home</a>
			<a href="<c:url value="/survey" />" >Survey</a>
		</nav>
    </div>

    <div class="main-section">