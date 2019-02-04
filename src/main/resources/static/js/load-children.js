<script>
	function loadChildren(id) {
		hideDropOut();
		$("#children" + id).load("load-children", {
			parentId : id
		});
	}
</script>
