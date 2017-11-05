import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Component({
    selector: 'app-redeem',
    templateUrl: './redemption.component.html',
    styleUrls: ['./redemption.component.css']
  })
export class RedemptionComponent implements OnInit {
  public creditList;
  public awardsList;
  constructor(private http: Http) {}

  public ngOnInit() {

    this.http.get('http://localhost:8080/RecognitionTool/creditType').subscribe((resp) => {
      // Read the result field from the JSON response.
      this.creditList = resp.json();
    });

    this.http.get('http://localhost:8080/RecognitionTool/awardsList').subscribe((resp) => {
      // Read the result field from the JSON response.
      this.awardsList = resp.json();
    });
  }
}
