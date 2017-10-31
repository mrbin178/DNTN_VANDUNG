<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2 class="search">Khóa mã số thuế</h2>
		<form id="searchKhoaMSTForm" name="searchKhoaMSTForm">
			<table width="100%" cellspacing="0" cellpadding="3" border="0"
				class="txt_1">
				<tbody>
				<c:if test="${not empty errorMessage && errorMessage !=''}">
					<tr>
						<td align="center" class="error" colspan="2" style="border-bottom: 1px dotted #CCCCCC;">
								${errorMessage}
						</td>
					</tr>
					</c:if>
				<c:if test="${not empty successMessage && successMessage !=''}">
					<tr>
						<td align="center" class="error" colspan="2" style="border-bottom: 1px dotted #CCCCCC; color: blue;">
								${successMessage}
						</td>
					</tr>
					</c:if>
				<tr>
					<td width="40%" valign="middle" align="right" class="input_form">Mã số thuế: </td>
					<td valign="middle">
					<input id="mst" type="text" name="mst" style="width: 200px" maxlength="255">
					</td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Tình trạng: </td>
					<td valign="middle">
					<select name="tinhTrang" id="tinhTrang">
						<option value="" selected="selected">----- Tất cả -----</option>
						<option value="0">Khóa</option>
						<option value="1">Hoạt động</option>					
					</select>
					</td>
				</tr>
				<tr>
					<td class="input_form">&nbsp;</td>
					<td valign="middle" align="left">
						<input class='bntInput' value='Tìm kiếm' type='button' onclick="btnSearch();">
						<input class='bntInput' value='Thêm mới' type="button" onclick="goCreate();">
					</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="output"></div>
	</div>
</div>
<div id="errorDialog"></div>
<div align="center" class="error">${errorMessage}</div>
<div id="HDDTTable" class="jtable-main-container"></div>

<script type="text/javascript">
var fieldSorting = "";
	function btnSearch() {
		$("#loading").show();
		$("#loading-b").show();
		var idTable = "#HDDTTable";		
		setupTable(idTable);
		$(idTable).jtable('reload');
		$("#loading").hide();
		$("#loading-b").hide();
	}
	function setupTable(idTable) {
		$(idTable).jtable({
			title : '',
			paging : true,
			pageSize : 50,
			sorting : true,
			multiSorting : true,
			defaultSorting : 'NgayTao DESC',
			actions : {
				listAction : function(postData, jtParams) {
					
					console.log("Loading from custom function...");
					return $.Deferred(function($dfd) {
						$.ajax({
								url : '${ADMIN_CONTEXT_PATH}/lock_mst.bv?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
							type : 'POST',
							dataType : 'json',
							data : $('#searchKhoaMSTForm').serialize(),
							success : function(data) {
								$dfd.resolve(data);
							},
							error : function() {
								$dfd.reject();
							}
						});
					});
				}
			},
			fields : {
				MST : {
					edit : false,
					width: '20%',
					title : 'Mã số thuế',
					display : function(data) {
						return data.record.MST;
					},
					sorting : false
				},
				NgayBD : {
					edit : false,
					title : 'Ngày bắt đầu',
					width: '10%',
					display : function(data) {
						return data.record.NgayBD;
					},
					sorting : false
				},
				NgayKT : {
					edit : false,
					title : 'Ngày kết thúc',
					width: '10%',
					display : function(data) {
						return  data.record.NgayKT;
					},
					sorting : false
				},
				NgayTao : {
					edit : false,
					title : 'Ngày tạo',
					width: '10%',
					display : function(data) {
						return  data.record.NgayTao;
					},
					sorting : true
				},
				TinhTrang : {
					edit : false,
					title : 'Tình trạng',
					width: '10%',
					display : function(data) {
						if(data.record.TinhTrang == 1){
							return "Hoạt động";
						}else{
							return "Khóa";
            			} 
					},
					sorting : false
				},
				CustomAction: {
                    title: 'Sửa',
                    width: '1%',
                    display: function(data) {                  	
		                    	var $link = $('<img src="<c:url value="/images/edit.png"/>" title="Sửa" class="myUpdate">');
		                    	$link.click(function(){
		                    		DEMOMVC.redirect("${ADMIN_CONTEXT_PATH}/editLockMst.bv?id="+data.record.ID);
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
	                    		DEMOMVC.showWarning('Bạn có muốn xóa thông tin này không?');
	                    		DEMOMVC.setDeleteDialog("Xác nhận", "${ADMIN_CONTEXT_PATH}/deleteLockMst.bv?id",data.record.ID,"${ADMIN_CONTEXT_PATH}/lock_mst.bv");	                    		
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
	
	function goCreate() {
		DEMOMVC.redirect('${ADMIN_CONTEXT_PATH}/create_lock_mst.bv');
	}

</script>