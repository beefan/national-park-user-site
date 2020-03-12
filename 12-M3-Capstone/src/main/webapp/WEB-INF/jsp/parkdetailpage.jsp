<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="common/header.jsp" %>

<h1><c:out value = "${park.name}"/><span class="title-state" >, <c:out value="${park.state}" /></span></h1>
<img src="<c:url value="/img/parks/${park.code.toLowerCase()}.jpg" />" />
<p>Acreage: <c:out value="${park.acreage}" /></p>
<p> Elevation: <c:out value="${park.elevation}" /></p>
<p>Miles Of Trail: <c:out value="${park.milesOfTrail}" /></p>
<p> Number Of Campsites: <c:out value="${park.numberOfCampsites}" /></p>
<p> Climate: <c:out value="${park.climate}" /></p>
<p> Year Founded: <c:out value="${park.yearFounded}" /></p>
<p> Annual Visitor Count: <c:out value="${park.annualVisitorCount}" /></p>
<p> Inspirational Quote: <c:out value="${park.inspirationalQuote}" /></p>
<p> Quote Author: <c:out value="${park.quoteAuthor}" /></p>
<p> Description: <c:out value="${park.description}" /></p>
<p> Entry Fee: $<c:out value="${park.entryFee}" /></p>
<p> Number Of Species: <c:out value="${park.numberOfSpecies}" /></p>

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