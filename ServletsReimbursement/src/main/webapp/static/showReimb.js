

function retreiveBankAccounts() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log(`state changed ${xhttp.readyState}`);
		if(xhttp.readyState === 4 && xhttp.status === 200) {
			console.log(xhttp.responseText);
			
			let accounts = JSON.parse(xhttp.responseText);
			console.log(accounts)
			accounts.forEach((account) => {
				document.getElementById('accounts-container').innerHTML += `
					<div class="account" id="${account.id}">
						<h3> Owner: ${account.owner}</h3>
						<p> Balance: ${account.balance} </p>
					</div>
				`;
			
			})
		} else {
			
		}
	}

	xhttp.open('GET', '../accounts');
	xhttp.send();
}

retreiveBankAccounts();


console.log("this is going while the request is being processed")