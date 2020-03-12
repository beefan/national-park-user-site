<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="common/header.jsp" %>

<h1>${park.name}</h1>

<c:forEach var="forecast" items="${park.fiveDayForecast}">
	<p>Day <c:out value="${forecast.day}" />: <c:out value="${forecast.forecast}" /> </p>
	<p>Trip Tips: 
	<c:set var="totalRecs" value="${ weatherRecs.get(forecast.day).size() }" />
	<c:choose>
		<c:when test="${ totalRecs < 1 }" >
			No recommendations for today. Enjoy your trip! </p>
		</c:when>
		<c:otherwise>
			<c:set var="recNumber" value="1" />
			<c:forEach var="rec" items="${weatherRecs.get(forecast.day)}" >
				<c:choose>
					<c:when test="${recNumber == 1}" >
						<c:out value="${ rec.substring(0,1).toUpperCase() }${ rec.substring(1) }" />
					</c:when>
					<c:otherwise>
						<span class="adjust-punct">,</span> <c:out value="${rec}" />
					</c:otherwise>
				</c:choose> 
				<c:set var="recNumber" value="${ recNumber + 1 }" />
			</c:forEach><span class="adjust-punct">.</span></p>
	</c:otherwise>
	</c:choose>
	<br />
</c:forEach>

<%@include file="common/footer.jsp" %>