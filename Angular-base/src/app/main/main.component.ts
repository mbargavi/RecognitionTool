import { Component, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {LoginService} from '../services/login.service';
import {CreditsService} from '../services/credits.service';
import {HistMetricsService} from '../services/histmetrics.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public userDetails;
  public metrics;
  public Fname = localStorage.getItem('Fname');
  public Lname = localStorage.getItem('Lname');
  public Title = localStorage.getItem('Title');
  public creditsToGive = localStorage.getItem('creditsToGive');
  public creditsEarned = localStorage.getItem('creditsEarned');
  public histGiven;
  public histEarned;

   constructor(private loginService: LoginService, private creditsService: CreditsService, private hms: HistMetricsService) { }

  public ngOnInit() {
    this.getValues();

  }


  getValues(): void {
    this.creditsService.getCreditsTogive().subscribe((resp) => {
        this.creditsToGive = resp.json();
        localStorage.setItem('creditsToGive', this.creditsToGive);
    });
    this.creditsService.getCreditsEarned().subscribe((resp) => {
      this.creditsEarned = resp.json();
      localStorage.setItem('creditsEarned', this.creditsEarned);
    });
    this.hms.getHistMetricsObservable().subscribe((resp) => {
      this.metrics = resp.json();
      console.log(this.metrics);
      console.log(this.metrics[0]);
      console.log(this.metrics[1]);
      localStorage.setItem('totalGiven', this.metrics[0]);
      localStorage.setItem('totalEarned', this.metrics[1]);
      this.histGiven = this.metrics[0];
      this.histEarned = this.metrics[1];
    });

  }


}
