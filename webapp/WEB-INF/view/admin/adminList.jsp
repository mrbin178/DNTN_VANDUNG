<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<script>var isEditable = false;</script>

<div class="ts24-titlebar">
	<div class="ts24-titlebar-left"></div>
	<div class="ts24-titlebar-right"></div>
	<div class="ts24-titlebar-center">
		<div class="ts24-titlebar-title-panel">
			<h2>Administration</h2>
		</div>
		<div class="ts24-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="ts24-window-content">
	<div class="contenttable">
		<form action="#" method="post">
			<div class="introText">
				<p>This page shows all Administration</p>
			</div>
			<div class="tabletitle">Administration</div>
			<div class="tablectrl">
				<table class="tablebuttonbar">
					<tbody>
						<tr>
							<td class="tablecontrols">
								<div>
									<script>var isEditable = true;</script>
									<div id="toolbar" class="Clearfix">
										<ul>
											<li><a href="${ADMIN_CONTEXT_PATH}/createNewProject.bv" id="createNewProject">Create a New User</a></li>
										</ul>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="ProjectTableContainer"></div>
			</div>
		</form>
	</div>
</div>
<div id="popupNewProject"></div>
<div id="popupEditProject"></div>
<script type="text/javascript">
	var projectTableId = '#ProjectTableContainer';
	var newProjectPopupId = "#popupNewProject";
	var editProjectPopupId = '#popupEditProject';
	var createNewProjectFormId = "#createNewProjectForm";
    var editProjectFormId = "#editProjectForm";
    var action = { listAction: '${ADMIN_CONTEXT_PATH}/adminListForJTable.bv',
    		deleteAction: '${ADMIN_CONTEXT_PATH}/deleteProject.bv'
    }
    
	function showDeleteAction(){
		return "deleteAction: '${ADMIN_CONTEXT_PATH}/deleteProject.bv'";
	}
    var tableArgs = {
            title: '',
            paging : true,
			pageSize : 500,
			sorting : true,
			defaultSorting : 'createdDate DESC',
			messages: {
				addNewRecord: 'Create a New User',
			    editRecord: 'Edit Project',
			    deleteConfirmation: 'This project will be deleted. Are you sure?',
			    save:'Ok',
			},
            actions: {
                listAction: '${ADMIN_CONTEXT_PATH}/adminListForJTable.bv',               	
                //deleteAction:'${PROJECT_CONTEXT_PATH}/deleteProject.bv'
            },
            fields: {
            	/*statusName:{
                	title: 'Status',
                    width: '8%',
                    create: false,
                    edit: false,    
                    sorting: false,
                },*/
            	id: {
                    key: true,
                    list: false,
                    create:false,
                    edit: false
                },
                UserName: {
                    title: 'Tai khoan',
                    display: function(data){
                    	return '<a href="${ADMIN_CONTEXT_PATH}/viewUser.bv?projectId=' + data.record.UserName + '">' + data.record.UserName + '</a>';
                    },
                    width: '40%',
                    inputClass:'validate[required],maxSize[225]'
                },
                FullName: {
                    title: 'Full name',
                    width: '15%',
                    inputClass:'validate[required],maxSize[15]'
                },
                
                Email: {
                    title: 'Email',
                    width: '15%',
                    create: false,
                    edit: false,  
                    
                },
                CustomAction: {
                    title: '',
                    width: '1%',
                    display: function(data) {
                    	if(data.record.status == 0){
	                    	if(data.record.pricePlanStatus == 0){
		                    	var $link = $('<img src="<c:url value="/images/edit.png"/>" title="Edit" class="myUpdate">');
		                    	$link.click(function(){
		                    		DEMOMVC.redirect("${PROJECT_CONTEXT_PATH}/editProject.bv?projectCode="+data.record.projectId);
		                    	});
		                    	return $link;
	                    	}
                    	}
                    },
                    sorting: false,
                    create: false,
                    edit:false,
                },
                deleteCustomAction: {
                    title: '',
                    width: '1%',
                    display: function(data) {
                    	var $link = $('<img src="<c:url value="/images/delete.png"/>" title="Delete" class="myUpdate">');
                    	if(data.record.pricePlanStatus == 0 || data.record.status == 2){
	                    	$link.click(function(){
	                    		DEMOMVC.showWarning('This project will be deleted. Are you sure?');
	                    		DEMOMVC.setDeleteDialog("Are you sure?", "${PROJECT_CONTEXT_PATH}/deleteProject.bv?projectId",data.record.projectId,"${PROJECT_CONTEXT_PATH}/projectList.bv");
	                    	});
	                    	return $link;
                    	}
                    },
                    sorting: false,
                    create: false,
                    edit:false,
                }
               
                
                
            },
            formCreated: function (event, data) {
            	//format layout 
            	data.form.css('width','400px');
            	data.form.css('margin-top','10px');
            	data.form.find('input[name=projectName]').css('width','300px');
            	data.form.find('input[name=peat]').css('width','120px');
            	//register validation
                data.form.validationEngine({'binded':false});
            },
            formSubmitting: function (event, data) {
            	data.form.validationEngine('hide');
                data.form.validationEngine('detach');
                data.form.validationEngine({'binded':false});
                return data.form.validationEngine('validate');
            },
  
            recordsLoaded: function (event, data) {	
            	
            	for (var i in data.records) {
            		var $row = $('#ProjectTableContainer').find(".jtable tbody tr td:first-child:eq(" + i + ") "); 
	            	if(data.records[i].statusName == 'Processing'){
	            		$row.css("background-color", "#FFFF99");
	            	}else if(data.records[i].statusName == 'Done'){
	            		$row.css("background-color", "#87CEFA");
	            	}
	            	//else if(data.records[i].statusName == 'Done'){
	            	//	$row.css("background-color", "green");
	            	//}
            	}
            },
            recordUpdated:function(event, data){
                $('#ProjectTableContainer').jtable('reload');
            },
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
        };
    if(isEditable==false){
    	delete tableArgs.actions.deleteAction;
    	delete tableArgs.fields.CustomAction;
    }
    $(document).ready(function () {
        $('#ProjectTableContainer').jtable(tableArgs);
        $('#ProjectTableContainer').jtable('load');
      	
        function refreshTable(){
       	   $(projectTableId).jtable('reload');
        }
    });
</script>
