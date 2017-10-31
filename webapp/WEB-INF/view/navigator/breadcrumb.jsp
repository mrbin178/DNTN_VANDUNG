<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
  <c:when test="${ not empty sessionScope.currentBreadCrumb}">
		<c:set var="navigateLinks" value="${ sessionScope.currentBreadCrumb }"/>
  </c:when>
  <c:otherwise>
    <c:set var="navigateLinks" value="${mainFlowBreadCrumb}"/>
  </c:otherwise>
</c:choose> 
 <div class="mainpage ">
	<div class="maincontent">
   		<div class="f_left">	
		<c:forEach var="entry" items="${navigateLinks}">
			<c:choose>
				<c:when test="${entry.currentPage == true}">
				    <c:choose>
				    	<c:when test="${entry.label eq 'webSession.currentProject.projectName'}">
				    		${ webSession.currentProject.projectName }
				    	</c:when>
				    	<c:otherwise>
				    		${entry.label}
				    	</c:otherwise>
				    </c:choose>
				</c:when>
				<c:when test="${entry.label eq 'webSession.currentProject.projectName'}">
				     <a href="${entry.url}"> ${ webSession.currentProject.projectName }</a> >
				</c:when>
				<c:otherwise>
						<a href="${entry.url}">${entry.label}</a> >
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<br/>
</div>
</div>