<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class="ts24-window-content">
	<div class="contenttable">
	<h2 class="search">Tìm kiếm thông tin tài khoản</h2>
		<form id="userSearchForm" name="userSearchForm">
									
			<table width="100%" cellspacing="0" cellpadding="3" border="0"
			class="txt_1">
			<tbody>
				<tr>
					<td width="40%" valign="middle" align="right" class="input_form">Tên người dùng:</td>
					<td valign="middle">
					<input id="fullName" type="text" name="fullName" style="width: 200px" maxlength="255">
					</td>
									
				</tr>
				
							
				<tr>
					<td valign="middle" align="right" class="input_form">Tài khoản người dùng:</td>
					<td valign="middle"><input id="username" type="text" name="username" style="width: 200px" maxlength="256"></td>
					
					</tr>
				
				<tr>
					<td valign="middle" align="right" class="input_form">Cấp quản lý:</td>
					<td valign="middle">
					
					<select name="role" id="role">
						<option value="" selected="selected">------ Tất cả ------</option>						
					   	<c:if test="${role < 3}"><option value="2" >Quản lý tỉnh</option></c:if>
						<c:if test="${role < 4}"><option value="3">Quản lý quận</option></c:if>
						<c:if test="${role < 5}"><option value="4" >Người dùng</option></c:if>
					</select>
					</td>
					
				</tr>
				<tr>
					<td class="input_form">&nbsp;</td>
					<td valign="middle" align="left">
						<div class="bnt f_left">
							<a href="#" onclick="btnSearchADD();">Tìm kiếm</a>&nbsp;
						</div>
						<div class="bnt f_left">
							<a href="${ADMIN_CONTEXT_PATH}/createNewUser.bv">Thêm mới</a>&nbsp;
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</div>

<c:if test="${not empty errorMessage && errorMessage != ''}">
	<div style="border-top: 1px dotted #CCCCCC; margin: 10px 0px 10px 0px;">
		<font color="red"> ${errorMessage} </font>
	</div>
</c:if>
<div id="errorDialog"></div>
<div id="userTable" class="jtable-main-container"></div>

<script type="text/javascript" charset="utf-8">
	function btnSearchADD(){
		$("#loading").show();
		$("#loading-b").show();
		var searchUserId ="#userTable";
		
		setupUserTable(searchUserId);
		$(searchUserId).jtable('reload');
		$("#loading").hide();
		$("#loading-b").hide();
	}
	function setupUserTable(searchUserId)
    {
		$(searchUserId).jtable({
			title: '',
		    paging : true,
			pageSize : 500,
			sorting: true,
            multiSorting: true,
            defaultSorting: 'Username ASC',
		    actions: {
		    	listAction: function (postData, jtParams) {
                    console.log("Loading from custom function...");
                    return $.Deferred(function ($dfd) {
                        $.ajax({
                            url: '${ADMIN_CONTEXT_PATH}/danhsachnguoidung.bv?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
                            type: 'POST',
                            dataType: 'json',
                            data: $('#userSearchForm').serialize(),
                            success: function (data) {
                                $dfd.resolve(data);
                            },
                            error: function () {
                                $dfd.reject();
                            }
                        });
                    });
		    	}
		    },
		    fields: {	        		           
		    	Username: {
	                key: true,
	                edit: false,
	                title: 'Tên đăng nhập',
	                display: function (data) {
	                	//return '<a href="${ADMIN_CONTEXT_PATH}/viewUser.bv?id=' + data.record.ID + '">' + data.record.Username + '</a>';
	                	var $linkUser =$('<a href="#" title="Xem chi tiết">' + data.record.Username + '</a>');
	                	$linkUser.click(function(){
							window.open("${ADMIN_CONTEXT_PATH}/viewUser.bv?id=" + data.record.ID, '_blank', 'height=450,width=850,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
						});
				    return $linkUser;
	                	
	                }, 
	                sorting: true
	            },
	            FullName: {
	                title: 'Họ tên',
	                width: '11%',
	                display: function (data) {
	                	return data.record.FullName;
	                },
	                sorting: true
	            },
	            Role: {
	                title: 'Quyền quản lý',
	                width: '8%',
	                display: function (data) {
	                	if(data.record.Role == 1){
	                		return "Quản lý super admin";
	                	}else if(data.record.Role == 2){
	                		return "Quản lý tỉnh";
	                	}else if(data.record.Role == 3){
	                		return "Quản lý quận" ;
	                	}else if (data.record.Role == 4){
	                		return "Người dùng" ;
	                	}
	                	//return data.record.Role;
	                },
	                sorting: true
	            },
	            Email: {
	                title: 'Email',
	                width: '8%',
	                display: function (data) {
	                	return data.record.Email;
	                },
	                sorting: true
	            },
	            Status: {
	                key: true,
	                edit: false,
	                title: 'Trạng thái',
	                display: function (data) {
	                	
	                	var $statusTT = ""
	                	 if(data.record.Status == 1){
	                		$statusTT = "Hoạt động";
            			}else{
            				$statusTT = "Khóa";
            			} 
	                	return '<a href="${ADMIN_CONTEXT_PATH}/updateStatus.bv?id=' + data.record.ID + '" title="Cập nhật trạng thái">' + $statusTT + '</a>'; ;
	                           
	                }, 
	                sorting: false
	            },
                
	           	  CustomAction: {
                    title: 'Sửa',
                    width: '1%',
                    display: function(data) {                  	
		                    	var $link = $('<img src="<c:url value="/images/edit.png"/>" title="Sửa" class="myUpdate">');
		                    	$link.click(function(){
		                    		DEMOMVC.redirect("${ADMIN_CONTEXT_PATH}/editUser.bv?id="+data.record.ID);
		                    	});
		                    	return $link;	                    
                    },
                    sorting: false,
                    create: false,
                    edit:false,
                },
                deleteCustomAction: {
                    title: 'Xóa',
                    width: '1%',
                    display: function(data) {
                    	var $link = $('<img src="<c:url value="/images/delete.png"/>" title="Xóa" class="myUpdate">');                    	
	                    	$link.click(function(){	                    		
	                    		DEMOMVC.showWarning('Bạn có chắc chắn muốn xóa thông tin này không?');
	                    		DEMOMVC.setDeleteDialog("Xác nhận?", "${ADMIN_CONTEXT_PATH}/deleteUser.bv?id",data.record.ID,"${ADMIN_CONTEXT_PATH}/danhsachnguoidung.bv");	                    		
	                    	});
	                    	return $link;
                    },
                    sorting: false,
                    create: false,
                    edit:false,
                },
               
			}
	    });
	    
    }
	function goBack() {
		SEVENJ.redirect("${ADMIN_CONTEXT_PATH}/danhsachnguoidung.bv");
	} 
	
	 $(document).ready(function () {
		 var searchUserId ="#userTable";
		 setupUserTable(searchUserId)
	     //   $('#userTable').jtable(tableArgs);
	        $('#userTable').jtable('load');
	      	
	        function refreshTable(){
	       	   $(projectTableId).jtable('reload');
	        }
	        	       	        
	    });
	 
	 function viewCreateNewUser(url){
			$("#loading").show();
			$("#loading-b").show();
			$.ajax({
	            url: "${ADMIN_CONTEXT_PATH}/createNewUser.bv",
	            type: 'GET',
	    	}).done(function (response) {
	    		DEMOMVC.redirect(url);
	    	});
	    }
	</script>