<%-- <%@page import="ts24.com.vn.dal.model.LoginAdmin"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/webstyles.css"/>" />
<div class="navigation">
   	<div class="mainpage">
   		<div class="f_left">
         <ul class="navigation" id ="navigation">
           <li id="tracuuhoadon"><a href="<c:url value="/tracuu/searchInvoice.bv" />" class="active">Tra cứu hóa đơn</a></li>
           <li id="coquanthue"><a href="<c:url value="/home.bv"/>">Cơ quan Thuế</a></li>
           <li id="huongdan"><a href="<c:url value="/useGuide.bv"/>" class="last">Hướng dẫn sử dụng</a></li>
           <!-- <li id="tracuuphieuthu"><a href="<c:url value="/receipt/searchReceipt.bv"/>" class="last">Tra cứu phiếu thu</a></li> -->
         </ul>
        </div>
	        <div class="f_right" id="logints24id">	
       			<div class="navigation_login">
       				<div class="f_left">
			        	<img alt="Login" src="<c:url value="/images/user24.png"/>">
			        </div>
			        <div class="f_right">
			        	<c:choose>
					    	<c:when test="${not empty webSession && not empty webSession.userInfoViewID }">
									<div class="f_left">
					    			<c:choose>
					    				<c:when test="${not empty webSession.userInfoViewID.fullname}">
											<a href="<c:url value="/admincallservices/profileTS24ID.bv"/>"><c:out value="${webSession.userInfoViewID.fullname }"></c:out></a> |
					    				</c:when>
					    				<c:when test="${not empty webSession.userInfoViewID.username && empty webSession.userInfoViewID.fullname}">
											<a href="<c:url value="/admincallservices/profileTS24ID.bv"/>"><c:out value="${webSession.userInfoViewID.username }"></c:out></a> |
					    				</c:when>
					    				<c:otherwise>
					    				</c:otherwise>
					    			</c:choose>
								    </div>
								    <div class="f_right"> 
										<img class="f_left" alt="Logout" src="<c:url value="/images/key24.png"/>">				
										<a class="f_right" href="<c:url value="/admincallservices/logoutTS24ID.bv"/>">Thoát</a>
									</div>
						    </c:when>
						    <c:otherwise>
					        	<a href="<c:url value="/admincallservices/loginTS24ID.bv"/>">Đăng nhập</a>
						    </c:otherwise>
						</c:choose>
					</div>
				</div> 
	        </div>
      </div>
   </div>
<div class="mainpage">
	<img src="<c:url value="/images/banner02.jpg"/>" />
</div>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function () {	
        if(window.location.href.indexOf("tracuu/") > 0)
        {
//              $("#navigation li#tracuuhoadon a").addClass("active");
//              $("#navigation li#coquanthue a").removeClass("active")
//              $("#navigation li#huongdan a").removeClass("active")

			$("#navigation li a").removeClass("active")
			$("#navigation li#huongdan a.last").removeClass("active");
			$("#navigation li#tracuuhoadon a").addClass("active");
        }
        else if(window.location.href.indexOf("home") > 0 
        		|| window.location.href.indexOf("admin/") > 0 
        		|| window.location.href.indexOf("hoadon_view") > 0 
        		//|| window.location.href.indexOf("login") > 0
        		|| window.location.href.indexOf("searchInvoiceFromExcel") > 0
        		|| window.location.href.indexOf("report") > 0)
        {
        
//              $("#navigation li#coquanthue a").addClass("active");
//              $("#navigation li#tracuuhoadon a").removeClass("active")
//              $("#navigation li#huongdan a").removeClass("active")
        	$("#navigation li a").removeClass("active")
			$("#navigation li#huongdan a.last").removeClass("active");
			$("#navigation li#coquanthue a").addClass("active");
        }else if(window.location.href.indexOf("admincallservices") > 0){
			$("#navigation li a").removeClass("active")
			$(".navigation_login").addClass("navigation_login_active");       	
			//$("#logints24id a").addClass("active");
			//$("#logints24id_icon_user .f_right").addClass("bg");
        }else if(window.location.href.indexOf("useGuide") > 0){
        	$("#navigation li a").removeClass("active")
			$("#navigation li#huongdan a.last").addClass("active");
        }else if(window.location.href.indexOf("receipt/") > 0){
        	$("#navigation li a").removeClass("active")
			$("#navigation li#tracuuphieuthu a.last").addClass("active");
        }
    });
			
</script>
 