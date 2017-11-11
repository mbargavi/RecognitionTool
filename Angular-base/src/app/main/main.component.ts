import { Component, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {LoginService} from '../services/login.service';
import {CreditsService} from '../services/credits.service';
import {HistMetricsService} from '../services/histmetrics.service';
import {ImageService} from '../services/image.service';
import {DomSanitizer} from '@angular/platform-browser';

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
  public picShowInput = false;
  public baseURL= 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
  public profileURL;
  // public profileURL = 'http://localhost:8080/RecognitionTool/retrieveImage/1';
  public message= '';
  public fileSelected = false;

   constructor(private loginService: LoginService,
               private creditsService: CreditsService,
               private hms: HistMetricsService,
               private is: ImageService,
               private sanitizer: DomSanitizer) {
                  this.profileURL = this.sanitizer.bypassSecurityTrustUrl(this.baseURL);
               }

  public ngOnInit() {
    this.getValues();

  }

  inputPic() {
    // review modal logic
    this.picShowInput = true;

  }

  uploadImage() {
    if (this.fileSelected) {
      this.picShowInput = false;
      // this.elem.querySelector('#spinner').setAttribute('style', 'visibility:visible;'); // need to add spinner element first
      const files = (<HTMLInputElement>document.getElementById('selectFile')).files;
      const formData = new FormData();
      const file = files[0];
      console.log(files[0]);
      formData.append('file', file, file.name);
      formData.append('empId', this.loginService.userDetails.employeeId);
      this.is.uploadImageObservable(formData).subscribe(resp => {
                                              this.dataLoaded(resp);
                                              console.log(resp.json);
                                              const sanitizedUrl = this.sanitizer.bypassSecurityTrustUrl(resp);
                                              this.profileURL = sanitizedUrl;
                                            });
    }

  }

  private dataLoaded(data: any): void {
    // this.elem.querySelector('#spinner').setAttribute('style', 'visibility:hidden;'); // need to add spinner element first
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
    // BELOW STILL NOT WORKING...COMMENTING OUT SO I CAN PUSH WHAT I HAVE SO FAR
    // this.is.retrieveObservable().subscribe((resp) => {
    //   this.profileURL = this.sanitizer.bypassSecurityTrustUrl(resp);
    // },
    // (error) => {
    //   if (error.status === 400) {
    //      this.message = 'File retrieval failed! Probably no profile pic saved'; // not putting this message anywhere yet
    //      this.profileURL = this.sanitizer.bypassSecurityTrustUrl(this.baseURL);
    //   }
    // });

  }


}
