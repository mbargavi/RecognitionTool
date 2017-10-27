window.onload = function() {
    console.log("loaded")
    retreiveAllTickets();
}

function retreiveAllTickets() {
 
    let ajax = new XMLHttpRequest();

    ajax.onreadystatechange = function() {
        console.log(`state changed ${ajax.readyState}`);
        
        if(ajax.readyState === 4 && ajax.status === 200) {
            console.log(ajax.responseText);let reimbs = JSON.parse(ajax.responseText);
              reimbs.forEach((reimb) => {
            	  document.getElementById('reimb-container').innerHTML += `
            	  <div class="user"><table width =100% border="1">
            	  <tr><td width =20 height 10>${reimb.reimbId}</td>
            	  <td width =15 height 10>${reimb.reimbAmount}</td>
            	  <td width =15>  ${new Date(reimb.reimbSubmitted).toString().substring(4, 15)}</td>
            	  <td width =15 height 10>${reimb.reimbDescription}</td>
            	  <td width =15 height 10>${reimb.reimbAuthor}</td>
            	  <td width =15 height 10>${reimb.reimbResolver}</td>
            	  <td width =15 height 10>${reimb.reimbStatusId}</td>
            	  </tr>
            	  </table>
            	  </div>
                  `;});
        }
    }
    ajax.open('GET', '../employee/viewEmpApproved');
    ajax.send();

}
