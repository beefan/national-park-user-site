<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<hr />
        <footer>
       		<jsp:useBean id="now" class="java.util.Date" />
            <p>A & B Consulting Co. &copy; <fmt:formatDate value="${now}" pattern="yyyy"/></p>
        </footer>
    </div>
</body>
</html>