<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="pageTitle" value="Home Page" />
<%@include file="common/header.jsp"%>
<div class = "detail-page">

<h1 class = dp-detail-title>
	<c:out value="${park.name}" />
	<span class="dp-title-state">, <c:out value="${park.state}" /></span>
</h1>

<img class = "dp-park-detail-img" src="<c:url value="/img/parks/${park.code.toLowerCase()}.jpg" />" />
<div class = "dp-details">
<p id = "dp-description">
	<h4>Description:</h4>
	<c:out value="${park.description}" />
</p>
<p id = "dp-acerage">
	<h4>Acreage:</h4>
	<c:out value="${park.acreage}" />
</p>
<p id = "dp-elevation">
	<h4>Elevation:</h4>
	<c:out value="${park.elevation}" />
</p>
<p id = "dp-miles">
	<h4>Miles Of Trail:</h4>
	<c:out value="${park.milesOfTrail}" />
</p>
<p id = "dp-number-of-campsites" >
	<h4>Number Of Campsites:</h4>
	<c:out value="${park.numberOfCampsites}" />
</p>
<p id = "dp-climate">
	<h4>Climate:</h4>
	<c:out value="${park.climate}" />
</p>
<p id = "dp-year-founded">
	<h4>Year Founded:</h4>
	<c:out value="${park.yearFounded}" />
</p>
<p id = "dp-visitor-count">
	<h4>Annual Visitor Count:</h4>
	<c:out value="${park.annualVisitorCount}" />
</p>
<p id = "dp-quote">
	<h4>Inspirational Quote:</h4>
	<c:out value="${park.inspirationalQuote}" />
</p>
<p id = "dp-quote-author">
	<h4>Quote Author:</h4>
	<c:out value="${park.quoteAuthor}" />
</p>
<p id = "dp-entry-fee">
	<h4>Entry Fee: $</h4>
	<c:out value="${park.entryFee}" />
</p>
<p id = "dp-species">
	<h4>Number Of Species:</h4>
	<c:out value="${park.numberOfSpecies}" />
</p>
</div>
<div class="five-day-forecast">
	<c:forEach var="forecast" items="${park.fiveDayForecast}">
		<div class="forcast">
			<c:choose>
				<c:when test="${forecast.day == 1 }">
					<p id = "date">Today</p>
				</c:when>
				<c:otherwise>
					<p id = "date">
						<fmt:parseDate value="${ dates.get(forecast.day -1) }" pattern="yyyy-MM-dd" var="parsedDateTime" type="date" />
						<fmt:formatDate pattern="MMM dd" value="${ parsedDateTime }" />
					</p>
				</c:otherwise>
			</c:choose>
			<img class="weather-logo"
				src=" <c:url value = "/img/weather/${forecast.forecast}.png"/>" />
			<div class="temps">
				<c:set var="high" value="${forecast.dailyHigh}" />
				<c:set var="low" value="${forecast.dailyLow}" />
				<c:url var="changeTempURL" value="/changeTemp" >
					<c:param name="unit" value="F" />
					<c:param name="code" value="${param.code}" />
				</c:url>
				
				<div class="unit">
					<c:choose>
						<c:when test="${weatherUnit.equals(\"C\")}" >
							<c:set var="high" value="${ (forecast.dailyHigh - 32) * 5/9.}" />
							<c:set var="low" value="${ (forecast.dailyLow -32) * 5/9.}" />
							<p> <span class="big-temp">C</span> / <a href="${changeTempURL}"><span class="small-temp">F</span></a></p>
						</c:when>
						<c:otherwise>
							<c:url var="changeTempURL" value="/changeTemp" >
								<c:param name="unit" value="C" />
								<c:param name="code" value="${param.code}" />
							</c:url>
							<p> <span class="big-temp">F</span> / <a href="${changeTempURL}"><span class="small-temp">C</span></a></p>
						</c:otherwise>
					</c:choose>
				</div>
				<p id = "high">
					High
					<fmt:formatNumber value="${ high }" maxFractionDigits="2" />
				</p>
				<p id = "low">
					Low
					<fmt:formatNumber value="${ low }" maxFractionDigits="2" />
				</p>
			
			</div>
			<p class = "tips">
				Trip Tips:
				<c:set var="totalRecs"
					value="${ weatherRecs.get(forecast.day).size() }" />
				<c:choose>

					<c:when test="${ totalRecs < 1 }">
			No recommendations for today. Enjoy your trip! 
			</p>
			</c:when>
			<c:otherwise>
				<c:set var="recNumber" value="1" />
				<c:forEach var="rec" items="${weatherRecs.get(forecast.day)}">
					<c:choose>
						<c:when test="${recNumber == 1}">
							<c:out
								value="${ rec.substring(0,1).toUpperCase() }${ rec.substring(1) }" />
						</c:when>
						<c:otherwise>
							<span class="adjust-punct">,</span>
							<c:out value="${rec}" />
						</c:otherwise>
					</c:choose>
					<c:set var="recNumber" value="${ recNumber + 1 }" />
				</c:forEach>
				<span class="adjust-punct">.</span>
				</p>
			</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
</div>
</div>

<%@include file="common/footer.jsp"%>