<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>

<div class="ts24-titlebar">
	<div class="ts24-titlebar-left"></div>
	<div class="ts24-titlebar-right"></div>
	<div class="ts24-titlebar-center">
		<div class="ts24-titlebar-title-panel">
			<h2>Thông tin chi tiết tài khoản</h2>
		</div>
		<div class="ts24-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="ts24-window-content">
	
	<div class="contenttable">
	
	<div class="introText" style="border-bottom: 1px dotted #CCCCCC;">
			<p>Thông tin chi tiết tài khoản</p>
		</div>
		
		<form commandName="userForm" id="userForm" name="userForm"  action="">
			<!-- <table class="formTable" style="width: 100%;"> -->
			<table width="100%" cellspacing="0" cellpadding="3" border="0"	class="txt_1">
				<!-- <tbody class="rowIntro"> -->
				<tbody>											
				 <tr >
						<td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield"></span>Tên tài khoản: </label>
						</td>
						<td width="20%">
							<div>
								${userForm.fullName}
							</div>
							
						</td>
					
					
					</tr>
					
					<tr >
						<td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
						   
						  
							<span class="requiredfield"></span>Tài khoản đăng nhập: </label>
						</td>
						<td width="20%">
							<div>
								${userForm.username}
							</div>
							
						</td>
						
					
					</tr>
					
					<tr >
						<td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield"></span>Email: </label>
						</td>
						<td width="20%">
							<div>
								${userForm.email}
							</div>
							
						</td>
						
					
					</tr>
					
					<tr >
						<td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield"></span>Cấp quản lý: </label>
						</td>
						<td width="20%">
							<div>
														    						            
							        	<c:if test="${userForm.role == 2}"> Quản lý tỉnh</c:if>
							        	<c:if test="${userForm.role == 3}"> Quản lý quận </c:if>
							        	<c:if test="${userForm.role == 4}"> Người dùng</c:if>  			
							        	
							       							   		   	
					
							</div>
							
						</td>
						
					
					</tr>
																		
					<tr >
						<td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield"></span>Tên cơ quan: </label>
						</td>
						<td width="20%">
						
							<div>
								${userForm.tencqt}
							</div>
							
						</td>
						
					
					</tr>
					<tr >
						  <td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield"></span>MST LIMIT: </label>
						</td>
						<td width="20%">
							<div>
								${userForm.limitMST}
							</div>
							
						</td>
						
						
					 
					</tr>
					
						<tr >
						<td width="25%" align="right" class="input_form"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield"></span>Nhóm quyền quản lý: </label>
						</td>
						<td width="20%">
							<div>
							 <c:forEach items="${arrNhomQuyen}" var="item">
							 <input name="chkRule" id="chkRule" disabled="disabled" type="checkbox" value="${item.maNhomQuyen}" <c:forEach items="${arrUserRule}" var="user_rule">
							  	<c:if test="${item.maNhomQuyen eq user_rule.rule}"><c:out value="checked"></c:out> </c:if> 							  	
							  </c:forEach>/>${item.tenNhomQuyen}
							 </c:forEach>
																		
							</div>
							
						</td>
						<td width="20%"></td>
					
					</tr>
					
																						
				</tbody>
			</table>
		</form>
		
	</div>
	
	
	</div>
</div>
