<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="common/header.jsp" %>
<div class="parks">
<c:forEach var="park" items="${parks}">
<c:url var="parkDetailURL" value="/parkdetailpage">
	<c:param name="code" value="${park.code}" />
</c:url>
<p id="homeSeleniumHelper" >home</p>
<h1><c:out value="${park.name}" /><span class="italic" >, <c:out value="${park.state}" /></span></h1>
<div class="park" >
	<a href="${parkDetailURL}" >
		<img src="<c:url value="/img/parks/${park.code.toLowerCase()}.jpg" />" />
	</a>
	<div>
	<p><c:out value="${park.description}" /></p>
	</div>
</div>
</c:forEach>
</div>

<%@include file="common/footer.jsp" %>