<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="common/header.jsp" %>

<c:forEach var="park" items="${parks}">
<c:url var="parkDetailURL" value="/parkdetailpage">
	<c:param name="code" value="${park.code}" />
</c:url>
<div class="park" >
	<h3><c:out value="${park.name}" /><span class="title-state" >, <c:out value="${park.state}" /></span></h3>
	<a href="${parkDetailURL}" >
		<img src="<c:url value="/img/parks/${park.code.toLowerCase()}.jpg" />" />
	</a>
	<p><c:out value="${park.description}" /></p>
</div>
</c:forEach>

<%@include file="common/footer.jsp" %>