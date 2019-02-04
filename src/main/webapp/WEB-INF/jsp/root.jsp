<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>file tree</title>
<link rel="stylesheet" href="css/folder.css">
<link rel="stylesheet" href="css/folderList.css">
<link rel="stylesheet" href="css/dropOutMenu.css">
<link rel="stylesheet" href="css/form.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
     function loadChildren(id) {
          hideDropOut();
          $("#children" + id).load("load-children", {
               parentId : id
          });
     }
</script>
<script>
	function allowDrop(ev) {
		ev.preventDefault();
	}

	function drag(ev) {
		ev.dataTransfer.setData("movedNodeId", ev.target.id);
	}

	function drop(ev, targetId) {
		var draggedId = ev.dataTransfer.getData("movedNodeId");
		if (draggedId == targetId) {
			alert("Operation not supported: can't move folder inside itself.")
		} else {
			$("#children" + targetId).load("replace", {
				draggedId : draggedId,
				targetId : targetId
			}, removeDragged(draggedId));
		}
	}

	function removeDragged(draggedId) {
		var element = document.getElementById(draggedId);
		element.parentNode.removeChild(element);
	}
</script>

<script>
	function showDropOut(id) {
		hideDropOut();
		document.getElementById(id).classList.toggle("show");
	}

	window.onclick = function(event) {
		if (!event.target.matches('.folder')) {
			hideDropOut();
		}
	}

	function hideDropOut() {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
	
	function showHideElement(id){
		  document.getElementById(id).classList.toggle("visible");
	}
</script>

<script>
function createNode(id){
	var fileName = document.getElementById("folderName" + id).value;
	$("#children" + id).load("create", {
		parentId : id,
		nodeName : fileName
	});
	showHideElement("addForm" + id);
	}
	
function updateNode(id, parentId){
    var fileName = document.getElementById("newFolderName" + id).value;
    $("#children" + parentId).load("update", {
         id : id,
         name : fileName
    });
    showHideElement(updateForm + id);
    document.getElementById("name" + id).text(fileName);
    }
    
function deleteNode(deleteId, parentId){
	if (deleteId == parentId){
	} else {
	$("#children" + parentId).load("delete", {
		deleteId : deleteId,
		parentId : parentId
	});
    }
	showHideElement("deleteForm" + deleteId);
}
</script>

</head>

<body>
     <ul>
          <li ondragover="allowDrop(event)" ondrop="drop(event, ${node.getId()})">

               <table>
                    <tr>
                         <div class="dropdown">

                              <td id="folderImageCell${node.getId()}" tabindex="0" class="folder"
                                   onclick="showDropOut('dropList${node.getId()}')"
                                   ondblclick="loadChildren('${node.getId()}')"></td>
                         <td id="name${node.getName()}">${node.getName()}</td>

                         <div id="dropList${node.getId()}" class="dropdown-content">
                              <button onclick="showHideElement('addForm${node.getId()}')">Add
                                   new folder</button>
                              <button onclick="showHideElement('updateForm${node.getId()}')">Rename</button>
                              <button onclick="showHideElement('deleteForm${node.getId()}')">Delete</button>
                         </div>

                         <div id="addForm${node.getId()}" class="hidden">
                              <form>
                                   Folder name: <input id="folderName${node.getId()}" type="text"
                                        value="New folder"> <input type="submit"
                                        value="Accept" onclick="createNode(${node.getId()})">
                              </form>
                         </div>

                         <div id="updateForm${node.getId()}" class="hidden">
                              <form>
                                   New folder name: <input id="newFolderName${node.getId()}"
                                        type="text" value="${node.getName()}"> <input
                                        type="submit" value="Accept"
                                        onclick="updateNode(${node.getId()}, ${node.getParent().getId()})">
                              </form>
                         </div>

                         <div id="deleteForm${node.getId()}" class="hidden">
                              <form>
                                   Confirm delete? <input type="submit" value="YES"
                                        onclick="deleteNode(${node.getId()}, ${node.getParent().getId()})">
                                   <input type="submit" value="NO"
                                        onclick="showHideElement('deleteForm${node.getId()}')">
                              </form>
                         </div>

                         </div>
                    </tr>
               </table>

               <ul id="children${node.getId()}">
               </ul>
          </li>
     </ul>
</body>
</html>
