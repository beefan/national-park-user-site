<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="pageTitle" value="Home Page" />
<%@include file="common/header.jsp"%>

<h2>What's your favorite park?</h2>
<p>Please fill out this survey</p>

<form:form action="/submitSurvey" method="POST" modelAttribute="survey">
	<div>
		<label>Favorite National Park </label>

		<form:select path="parkCode" name="park">
			<option>Select a Park:</option>
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


<%@include file="common/footer.jsp"%>