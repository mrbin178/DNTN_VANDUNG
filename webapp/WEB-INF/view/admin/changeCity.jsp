<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div>
<spring:form commandName="userForm" id="userForm" name="userForm" action="${ADMIN_CONTEXT_PATH}/createNewUser.bv">
	<label>matinh :</label><select name="matinh" class="matinh">
		<option selected="selected" value="0">Select matinh</option>

		<c:forEach items="${arrTinhTP}" var="item">
			<option value="${item.maTinhThue}">
				<c:out value="${item.tenCoQuanThue}"></c:out>
			</option>
		</c:forEach>
	</select><br /> <br /> <label>macqt :</label>
	<select name="macqt" class="macqt">
	<option selected="selected">Select District</option>
	</select>
	
</spring:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$(".matinh").change(function() {
			var id = $(this).val();
			var id_String = 'maTinh=' + id;
			$.ajax({
				type : "POST",
				url : "${ADMIN_CONTEXT_PATH}/changeCity.bv",
				data : id_String,
				cache : false,
				success : function(macqtdata) {
					$(".macqt").html(macqtdata);
				}
			});

		});
	});
</script>
