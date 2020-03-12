<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="pageTitle" value="Survey Page" />
<%@include file="common/header.jsp"%>
<c:if test = "${surveyCount == null }">
<c:set var = "states" value = "${states}"/>
<h2>What's your favorite park?</h2>
<p>Please fill out this survey</p>
<c:url var = "submitSurvey" value = "/submitSurvey"/>
<form:form action="${submitSurvey}" method="POST" modelAttribute="survey">
	<div>
		<label>Favorite National Park </label>

		<form:select path="parkCode" name="park">
			<option value = "">Select a Park:</option>
			<c:forEach items="${parks}" var="park">
				<c:set var="selected" value="" />
				<c:if test="${park.code == param.park}">
					<c:set var="selected" value="SELECTED" />
				</c:if>

				<option value="${park.code}" ${selected}>${park.name}</option>
			</c:forEach>

		</form:select>
		<form:errors path="parkCode" cssClass="error" />
	</div>
	<div>
		<label> Your Email </label>
		<form:input path="emailAddress" />
		<form:errors path="emailAddress" cssClass="error" />
	</div>
	<div>
		<label>State of Residence</label>
		<form:select path="state" name="stateName">
			<option value = "">Select a State:</option>
			<c:forEach items="${states}" var="stateName">
				<c:set var="selected" value="" />
				<c:if test="${stateName == param.stateName}">
					<c:set var="selected" value="SELECTED" />
				</c:if>

				<option value="${stateName}" ${selected}>${stateName}</option>
			</c:forEach>

		</form:select>
		<form:errors path="state" cssClass="error" />
	</div>
	<div>
		<label>Activity Level</label>
		<form:radiobutton path="activityLevel" label="Inactive" value="Inactive" />
		<form:radiobutton path="activityLevel" label="Sedentary" value="Sedentary" />
		<form:radiobutton path="activityLevel" label="Active" value="Active" />
		<form:radiobutton path="activityLevel" label="Extremely Active" value="Extremely Active" />
		<form:errors path="activityLevel" cssClass="error" />
	</div>

	<div>
		<input type="submit" value="Submit Survey" />
	</div>

</form:form>
</c:if>
<c:if test = "${surveyCount != null}">
<h1>Survey Results</h1>
<div class = "survey-results">
<c:forEach var = "entry" items = "${surveyCount}">
<h3><c:out value = "${entry.key.name}"/></h3>
<img src="<c:url value="/img/parks/${entry.key.code.toLowerCase()}.jpg" />" />
<p> Description: <c:out value="${entry.key.description}" /></p>
<p><c:out value = "${entry.value}"/> people of Love this Park.  </p>
<c:if test = "${userSurvey.parkCode == entry.key.code}">
<c:set var = "sharedParkLovers" value = "${entry.value }"/>
</c:if>
</c:forEach>
</div>
<div class = "user-results"> 
<p>Your Favorite Park: <c:out value = "${userSurvey.parkName}"/></p>
<p><c:out value = "${sharedParkLovers - 1}" /> people love this park too!</p>
</div>

</c:if>

<%@include file="common/footer.jsp"%>