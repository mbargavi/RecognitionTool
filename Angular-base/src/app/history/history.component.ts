import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Http, RequestOptions, Headers } from '@angular/http';

@Component({
    selector: 'app-history',
    templateUrl: './history.component.html',
    styleUrls: ['./history.component.css']
  })
  export class HistoryComponent implements OnInit {
    public creditsEarned;
    public creditsGiven;
    constructor(private http: Http) {}
    redeemCredits(e) {
      // do something here
    }
    public ngOnInit() {
    this.http.get('http://localhost:8080/RecognitionTool/creditsEarned').subscribe((resp) => {
        // Read the result field from the JSON response.
        this.creditsEarned = resp.json();
      });

      this.http.get('http://localhost:8080/RecognitionTool/creditsGiven').subscribe((resp) => {
        // Read the result field from the JSON response.
        this.creditsGiven = resp.json();
      });
    }
  }

