window.onload = function() {
    console.log("loaded")
    retreiveAllTickets();
}



	
function retreiveAllTickets() {
 
    let ajax = new XMLHttpRequest();

    ajax.onreadystatechange = function() {
       // console.log(`state changed ${ajax.readyState}`);
        
        if(ajax.readyState === 4 && ajax.status === 200) {
       //     console.log(ajax.responseText);
            
              let reimbs = JSON.parse(ajax.responseText);
              reimbs.forEach((reimb) => {
                document.getElementById('reimb-container').innerHTML += `
                    <div class="user" >
              
                <table>
                <tr><td width =10 ><input value ="${reimb.reimbId}" type="radio" name="Radio1"></td><td width =10 height 10>${reimb.reimbId}</td>
                <td width =15 height 10>${reimb.reimbAmount}</td>
                <td width =15 height 10>${reimb.reimbSubmitted}</td>
<td width =15 height 10>${reimb.reimbDescription}</td>
<td width =15 height 10>${reimb.reimbAuthor}</td>
<td width =15 height 10>${reimb.reimbResolver}</td>
<td width =15 height 10>${reimb.reimbStatusId}</td>
</tr>
                    </table>
                    </div>
                `;
            });
        }
    }
    ajax.open('GET', '../manager/viewPending');
    ajax.send();

}
