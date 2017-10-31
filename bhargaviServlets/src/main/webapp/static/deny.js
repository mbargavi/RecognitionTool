function getReimbIDForDeny() {
	
	console.log("LOADING >>>>>> " + this.responseText) ;
	let xhr = new XMLHttpRequest();

	let reimbID;
	
	reimbID = document.querySelector('input[name="Radio1"]:checked').value
	
	xhr.open('GET', '../manager/deny/'+ reimbID);
	xhr.send();
	location.reload(true);
}

