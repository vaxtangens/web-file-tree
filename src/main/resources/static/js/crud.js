<script>
function createNode(id){
	var fileName = document.getElementById("folderName" + id).value;
	$("#children" + id).load("create-node", {
		parentId : id,
		nodeName : fileName
	});
	showHideElement("addForm" + id);
	}
	
function updateNode(id, parentId){
    var fileName = document.getElementById("newFolderName" + id).value;
	alert("inside updateNode " + fileName);

    $("#children" + parentId).load("update", {
         id : id,
         name : fileName
    });
    showHideElement(updateForm + id);
    document.getElementById("name" + id).text(fileName);
    }
    
function deleteNode(deleteId, parentId){
	if (deleteId == parentId){
		alert("Root folder could not be deleted");
	} else {
		alert("start loading..." + " Calling delete with parameters: dID " + deleteId + "parentID" + parentId);

	$("#children" + parentId).load("delete", {
		deleteId : deleteId,
		parentId : parentId
	});
    }
	alert("deleteForm" + deleteId);
	showHideElement("deleteForm" + deleteId);
}
</script>
