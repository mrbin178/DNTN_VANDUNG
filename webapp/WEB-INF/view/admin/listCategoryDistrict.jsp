<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<c:if test="${not empty arrQuan && arrQuan != '' }">
	<option  value=""><c:out value="---------- Tất cả -------------"></c:out>
	<c:forEach items="${arrQuan}" var="item">
		<option value="${item.maCoQuanThue}">
			<c:out value="${item.tenCoQuanThue}"></c:out>
		</option>
	</c:forEach>
</c:if>
