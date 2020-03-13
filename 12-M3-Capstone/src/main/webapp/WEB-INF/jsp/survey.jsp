<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="pageTitle" value="Survey Page" />
<%@include file="common/header.jsp"%>

<div class="survey">
<c:if test = "${surveyCount == null }">
	<div class="survey-submit">
		<h2>What's your favorite park?</h2>
		<p>Please fill out this survey</p>
		<c:url var = "submitSurvey" value = "/submitSurvey"/>
		<form:form action="${submitSurvey}" method="POST" modelAttribute="survey">
		<div>
			<label>Favorite National Park </label>
			<form:select path="parkCode" name="park">
				<option value = "">Select a Park:</option>
				<c:forEach items="${parks}" var="park">
					<option value="${park.code}">${park.name}</option>
				</c:forEach>
			</form:select>
			<form:errors path="parkCode" cssClass="error" />
		</div>
		<div>
			<label> Your Email </label>
			<form:input path="emailAddress" />
			<form:errors path="emailAddress" cssClass="error" />
		</div>
		<label>State of Residence</label>
		<div>
			<form:select cssClass="dropdown" path="state" name="stateName">
				<option value = "">Select a State:</option>
				<c:forEach items="${states}" var="stateName">
					<option value="${stateName}">${stateName}</option>
				</c:forEach>
			</form:select>
			<form:errors path="state" cssClass="error" />
		</div>
		<label>Activity Level</label>
		<div class="radio-buttons">
			<form:radiobutton path="activityLevel" label="Inactive" value="Inactive" />
			<form:radiobutton path="activityLevel" label="Sedentary" value="Sedentary" />
			<form:radiobutton path="activityLevel" label="Active" value="Active" />
			<form:radiobutton path="activityLevel" label="Extremely Active" value="Extremely Active" />
		</div>
		<form:errors path="activityLevel" cssClass="error" />

		<div class="submit">
			<input class="submit-button" type="submit" value="Submit Survey" />
		</div>
	</form:form>
</div>
</c:if>

<c:if test = "${surveyCount != null}">
<div class="survey-results">
	<h1 id="survey-title">And the Survey Says...</h1>
	<div id="results">
	<hr/>
	<c:forEach var = "entry" items = "${surveyCount}">
		<c:url var="parkDetailURL" value="/parkdetailpage">
			<c:param name="code" value="${entry.key.code}" />
		</c:url>
		<c:if test = "${userSurvey.parkCode == entry.key.code}">
			<c:set var = "sharedParkLovers" value = "${entry.value-1}"/>
		</c:if>
		<c:set var="peopleOrPerson" value="People" />
		<c:if test="${entry.value == 1 }">
			<c:set var="peopleOrPerson" value="Person" />
		</c:if>
		
		<h1>
			<c:out value = "${entry.key.name}"/><span class="italic" >, <c:out value="${entry.key.state}" /></span>
		</h1>
		<p class="italic">The National Favorite of <c:out value = "${entry.value} ${peopleOrPerson}"/>!</p>
		<div class="park">
			<a href="${parkDetailURL}" >
				<img src="<c:url value="/img/parks/${entry.key.code.toLowerCase()}.jpg" />" />
			</a>
			<div>
			<blockquote>"<c:out value="${entry.key.inspirationalQuote}" />"</blockquote>
			<p class="indent" >- <c:out value="${entry.key.quoteAuthor}" /></p>
			</div>
		</div>
	</c:forEach>
	</div>

<div class = "user-results"> 
	<c:url var="userFavParkURL" value="/parkdetailpage">
		<c:param name="code" value="${userSurvey.parkCode}" />
	</c:url>
	<p>Your favorite park, 
	<a href="${userFavParkURL}">
		<c:out value = "${userSurvey.parkName}"/>
	</a><span class="adjust-punct">,</span>
	<c:choose>
		<c:when test="${sharedParkLovers == 0}" >
			is unique. </p>
		</c:when>
		<c:otherwise>
			<c:set var="peopleOrPerson" value="people" />
			<c:if test="${entry.value == 1 }">
				<c:set var="peopleOrPerson" value="person" />
			</c:if>
			is the shared favorite of <c:out value = "${sharedParkLovers} ${peopleOrPerson}" />.</p>
		</c:otherwise>
	</c:choose>
</div>
</div>
</c:if>

</div>

<%@include file="common/footer.jsp"%>