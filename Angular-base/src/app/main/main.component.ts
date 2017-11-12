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
  // public baseURL= 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
  public baseURL = 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
  public profileURL = this.sanitizer.bypassSecurityTrustUrl(this.baseURL);
  public message= '';
  public fileSelected = false;

   constructor(private loginService: LoginService,
               private creditsService: CreditsService,
               private hms: HistMetricsService,
               private is: ImageService,
               private sanitizer: DomSanitizer) {

               }

  public ngOnInit() {
    this.getValues();

  }

  inputPic() {
    this.picShowInput = true;

  }

  uploadImage() {
    // event.preventDefault();
    if (this.fileSelected) {
      this.picShowInput = false;
      const files = (<HTMLInputElement>document.getElementById('selectFile')).files;
      const formData = new FormData();
      const file = files[0];
      console.log(files[0]);
      formData.append('file', file, file.name);
      formData.append('empId', this.loginService.userDetails.employeeId);
      this.is.uploadImageObservable(formData).subscribe(resp => {
                                              this.getImage();
                                            },
                                            (error) => {
                                                 this.message = 'Upload failed! Revert to last pic';
                                                 console.log(this.message);
                                                 this.getImage();
                                            });
    }

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

    this.getImage();

  }

  getImage(): void {
    this.is.retrieveObservable().subscribe((resp) => {
      this.profileURL = this.sanitizer.bypassSecurityTrustUrl(resp.url);
      console.log(resp.url);
    },
    (error) => {
         this.message = 'File retrieval failed! Probably no profile pic saved';
         console.log(this.message);
         this.profileURL = this.sanitizer.bypassSecurityTrustUrl(this.baseURL);
         // this.profileURL = this.baseURL;
    });
    console.log('Retrieval done...this is what we got: ' + this.profileURL);
    // if (this.profileURL === undefined) {
    //   console.log('Setting profileURL since it was undefined');
    //   this.profileURL = this.sanitizer.bypassSecurityTrustUrl(this.baseURL);
    //   // this.profileURL = 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
    //   console.log(this.profileURL);
    // }
  }

}
