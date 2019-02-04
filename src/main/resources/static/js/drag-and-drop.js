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
