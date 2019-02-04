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
