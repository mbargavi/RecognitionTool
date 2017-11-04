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


    constructor(private sls: SearchListService) {
      // nothing to do here I think, but a SearchComponent
      // will have a SearchListService available to it
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

