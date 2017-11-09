import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SearchListService } from '../services/search_list.service';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
  })
  export class SearchComponent implements OnInit {
    public combinedList = [];
    // public fieldFromService;

    public searchValue= '';
    public match = false;  // not using this yet
    public yourSelection= '[Your Selection]';
    public selectionId = 0;
    public selectionEntityType = ''; // this will be a 'Team' or an 'Employee' so we know which type of selectionID we have


    constructor(private sls: SearchListService) {
      // nothing to do here I think, but a SearchComponent
      // will have a SearchListService available to it
    }

    makeSelection(event) {
      console.log(event.currentTarget.cells[1].childNodes[0].innerText);
      console.log(event.currentTarget.cells[0].childNodes[0].innerText);
      this.yourSelection = event.currentTarget.cells[2].childNodes[0].innerText;
      this.selectionId = event.currentTarget.cells[1].childNodes[0].innerText;
      this.selectionEntityType = event.currentTarget.cells[0].childNodes[0].innerText;
      this.searchValue = '';

    }

    ngOnInit() {
      this.fetch();
      // this.fieldFromService = this.fcs.reusableField;
    }

    fetch(): void {
      this.sls.getSearchListObservable().subscribe((resp) => {
        this.combinedList = resp.json();
      });
    }

    // apply filter JQ
// $(document).ready( function() { $('#search').keyup(function(){
//        search_table($(this).val());
//   });
//   function search_table(value){
//        $('#reimbursementHistory tr').each(function(){
//             var found = 'false';
//             $(this).each(function(){
//                  if($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
//                  {
//                     found = 'true';
//                  }
//             });
//             if(found == 'true')
//             {
//                  $(this).show();
//             }
//             else
//             {
//                  $(this).hide();
//             }
//        });
//   }
// });

  }

