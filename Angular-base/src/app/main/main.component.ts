import { Component, Inject, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {LoginService} from '../services/login.service';
import {CreditsService} from '../services/credits.service';
import {HistMetricsService} from '../services/histmetrics.service';
import {ImageService} from '../services/image.service';
import {ChangeTitleService} from '../services/changeTitle.service';
import {DomSanitizer} from '@angular/platform-browser';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {Headers} from '@angular/http';
import { Router } from '@angular/router';

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
  public currTooltip = 'Test Message';


  public addrecog = false;
  public baseURL= 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
  public profileURL;


  public message= '';
  public messageOn = false;
  public fileSelected = false;
  public errorMessage;
  public addRecognitionStatus = false;
  private newTitleId;

   constructor(private loginService: LoginService,
               private creditsService: CreditsService,
               private hms: HistMetricsService,
               private is: ImageService,
               @Inject(Http) private http: Http,
               private router: Router,
               private sanitizer: DomSanitizer,
               private cts: ChangeTitleService) {

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

  private dataLoaded(data: any): void {
    // this.elem.querySelector('#spinner').setAttribute('style', 'visibility:hidden;'); // need to add spinner element first
  }

  public onSelectionChange(event) {
    localStorage.setItem('creditsTypeId', event.srcElement.value);
    console.log(localStorage.getItem('creditsTypeId'));
  }

  addRecognition() {
    this.getAddRecognitionResponse();


  }

  addRecognitionObservable(): Observable<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const body = {	'empNominatorId': this.loginService.userDetails.employeeId,
                    'nomineeId': localStorage.getItem('nomineeId'),
                    'creditTypeId': localStorage.getItem('creditsTypeId'),
                    'nominee': localStorage.getItem('nominee')};
    return this.http.post(localStorage.getItem('serverURL') + 'addRecognition', body, {headers: headers} );
  }

  getAddRecognitionResponse(): void {
    this.addRecognitionObservable().subscribe((resp) => {
      if ((resp.status === 200)) {
          // testing
          this.messageOn = true;
          this.ngOnInit(); // calling this refreshes page numbers but more work needed to clear selected values;
          console.log('here in success');
          // this.router.navigate(['success']);
      }},
      (error) => {
         this.addRecognitionStatus = true;
   });
  }

  // updateTitle(): void {
  //   this.cts.getUpdatedTitleId().subscribe((response) => {
  //     if (response.status === 200) {
  //       this.newTitleId = response.json();
  //       console.log('newTitleId being filled with ' + response.json());
  //       if (localStorage.getItem('newTitleId') === '0') {
  //         console.log('Title does not change.  Nothing to do');
  //       } else if (localStorage.getItem('newTitleId') === '-1') {
  //         console.log('There was some error trying to update.  Leave Title as is.');
  //       } else {
  //         console.log('Title has changed. Update the localStorage, userDetails and this.Title');
  //         localStorage.setItem('Title', this.newTitleId.titleName);
  //         localStorage.setItem('titleId', this.newTitleId.titleId);
  //         this.userDetails.titleId = this.newTitleId.titleId;
  //         this.userDetails.title.titleId = this.newTitleId.titleId;
  //         this.userDetails.title.titleName = this.newTitleId.titleName;

  //         this.Title = localStorage.getItem('Title');
  //         console.log('Just set this.Title to ' + this.Title);
  //       }
  //     }

  //   });
  // }



  // if ((resp.status === 200 )) {
  //   this.userDetails = resp.json();
  //   localStorage.setItem('Fname', this.userDetails.firstName);
  //   localStorage.setItem('Lname', this.userDetails.lastName);
  //   localStorage.setItem('Title', this.userDetails.title.titleName);
  //   localStorage.setItem('user', this.userDetails);
  //   localStorage.setItem('empId', this.userDetails.employeeId);
  //   localStorage.setItem('teamId', this.userDetails.teamId);
  //   localStorage.setItem('Title', this.userDetails.title.titleName);
  //   localStorage.setItem('empId', this.userDetails.employeeId);
  //   this.router.navigate(['main']);
  //   console.log(this.userDetails); }},
  //   (error) => {
  //      if (error.status === 400) {
  //         this.message = 'Those credentials were invalid!';
  //      }
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
