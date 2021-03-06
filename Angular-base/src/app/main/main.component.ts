import { Component, Inject, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {LoginService} from '../services/login.service';
import {CreditsService} from '../services/credits.service';
import {HistMetricsService} from '../services/histmetrics.service';
import {ImageService} from '../services/image.service';
import {EmailService} from '../services/email.service';
import {SearchListService} from '../services/search_list.service';
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
  public fullList;
  public recipientInfo;
  public metrics;
  public Fname = localStorage.getItem('Fname');
  public Lname = localStorage.getItem('Lname');
  public Title = localStorage.getItem('Title');
  public creditsToGive = localStorage.getItem('creditsToGive');
  public creditsEarned = localStorage.getItem('creditsEarned');
  public histGiven;
  public histEarned;
  public picShowInput = false;
  public currTooltip = '';
  public creditsByType;
  public credits;
  public capOneCredits;
  public responseStatus;
  public messageOn = false;

  public addrecog = false;
  public baseURL= 'http://heartlandpreciousmetals.com/wp-content/uploads/2014/06/person-placeholder.jpg';
  public profileURL;
  public recipientCount;

  public message= '';
  public fileSelected = false;
  public errorMessage;
  public addRecognitionStatus = false;
  public creditText;
  private recButtonClasses;

   constructor(private loginService: LoginService,
               private creditsService: CreditsService,
               private hms: HistMetricsService,
               private is: ImageService,
               private es: EmailService,
               private sls: SearchListService,
               @Inject(Http) private http: Http,
               private router: Router,
               private sanitizer: DomSanitizer) {

               }

  public ngOnInit() {
    this.getValues();
    this.recButtonClasses = document.getElementById('recButton').classList;

  }

  public change() {
    this.checkRecognitionReadiness();
    if (localStorage.getItem('recReadiness') === 'true') {
      if (this.recButtonClasses.contains('disabled')) {
        this.recButtonClasses.remove('disabled');
      }
    } else {
      if (!(this.recButtonClasses.contains('disabled'))) {
        this.recButtonClasses.add('disabled');
      }
    }
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
    this.change();
  }

  addRecognition() {
    this.checkRecognitionReadiness();
    if (localStorage.getItem('recReadiness') === 'true') {
      this.getAddRecognitionResponse();
    }else {
      // They haven't input all the values needed to make a recognition
      // Could give message to that effect or do nothing.
    }

  }

  addRecognitionObservable(): Observable<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const body = {  'empNominatorId': this.loginService.userDetails.employeeId,
                    'nomineeId': localStorage.getItem('nomineeId'),
                    'creditTypeId': localStorage.getItem('creditsTypeId'),
                    'nominee': localStorage.getItem('nominee')};
    return this.http.post(localStorage.getItem('serverURL') + 'addRecognition', body, {headers: headers} );
  }

  getAddRecognitionResponse(): void {
    this.addRecognitionObservable().subscribe((resp) => {
      if ((resp.status === 200)) {
        console.log('here in success');
        this.message = 'Successfull submission!';
        this.messageOn = true;
        this.ngOnInit(); // calling this refreshes page numbers but more work needed to clear selected values;

        console.log('here in success');
        this.sls.getSearchListObservable().subscribe((response) => {
          this.fullList = response.json;
          console.log(this.fullList);
        });
        const emailForm = new FormData();
        if ((<HTMLInputElement>document.getElementById('copyManager')).checked === true) {
          this.recipientCount = '4';
        } else {
          this.recipientCount = '3';
        }
        // if 1 radio button is checked, set creditText to "1 Credit", else set creditText to "5 Capital One Bucks"
        if ((<HTMLInputElement>document.getElementById('creditRadio').childNodes[1].childNodes[1].childNodes[0]).checked === true) {
          this.creditText = (<HTMLElement> document.getElementById('creditRadio').childNodes[1].childNodes[1]).innerText;
        } else {
          this.creditText = (<HTMLElement> document.getElementById('creditRadio').childNodes[3].childNodes[1]).innerText;
        }
        console.log('creditText = ' + this.creditText);
        emailForm.append('recipientCount', this.recipientCount);
        emailForm.append('subject', 'Recognition Feedback!');
        emailForm.append('message', (<HTMLInputElement>document.getElementById('primaryFeedback')).value);
        emailForm.append('manMessage', (<HTMLInputElement>document.getElementById('addFeedback')).value);
        emailForm.append('competencies', localStorage.getItem('competencyString'));
        emailForm.append('nomineeId', localStorage.getItem('nomineeId'));
        emailForm.append('nominatorId', localStorage.getItem('empId'));
        emailForm.append('credits', this.creditText);
        emailForm.append('entityType', localStorage.getItem('nominee'));
        this.clearValues();
        if (!(this.recButtonClasses.contains('disabled'))) {
          this.recButtonClasses.add('disabled');
        }
        console.log(emailForm);
        this.es.sendEmailObservable(emailForm).subscribe((response) => {
          console.log(response.json);
        });
        // this.router.navigate(['success']);
       }},
      (error)  => {
        if (error.status === 417) {
          this.addRecognitionStatus = true;
          this.errorMessage = 'No credits left, add recognition failed';
        }else {
          this.addRecognitionStatus = true;
          this.errorMessage = 'Server error, add recognition failed';
        }
    });
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
    this.creditsService.getCreditsToGiveByType().subscribe((resp) => {
      this.creditsByType = resp.json();
      console.log('first' + this.creditsByType[0]);
      console.log('first' + this.creditsByType[1]);
      localStorage.setItem('currentCredits', this.creditsByType[0]);
      localStorage.setItem('currentCap1Credits', this.creditsByType[1]);
      this.credits = this.creditsByType[0];
      this.capOneCredits = this.creditsByType[1];
      this.currTooltip = 'Credits: ' +  this.credits + ', ' + 'CapOne Bucks: ' + this.capOneCredits;
    });
    this.getImage();

  }

  clearValues(): void {
    // clear the values here
    (<HTMLInputElement>document.getElementById('commBox')).checked = false;
    (<HTMLInputElement>document.getElementById('custBox')).checked = false;
    (<HTMLInputElement>document.getElementById('judgeBox')).checked = false;
    (<HTMLInputElement>document.getElementById('jobBox')).checked = false;
    (<HTMLInputElement>document.getElementById('valueBox')).checked = false;
    (<HTMLInputElement>document.getElementById('teamBox')).checked = false;
    // localStorage.setItem('clearSelection', 'true');
    // Still need some logic to clear the search component selection
    localStorage.setItem('competencySelected', 'false');
    localStorage.setItem('nomineeSelected', 'false');
    localStorage.setItem('creditsTypeId', null);
    // clearing the two radio buttons
    (<HTMLInputElement>document.getElementById('creditRadio').childNodes[1].childNodes[1].childNodes[0]).checked = false;
    (<HTMLInputElement>document.getElementById('creditRadio').childNodes[3].childNodes[1].childNodes[0]).checked = false;
    (<HTMLInputElement>document.getElementById('primaryFeedback')).value = '';
    localStorage.setItem('competencySelected', 'false');
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
  setHistoricalGiven() {
    localStorage.setItem('Histview', 'HistoricalGiven');
    this.router.navigate (['history']);
  }
  setHistoricalEarned() {
   localStorage.setItem('Histview', 'HistoricalEarned');
   this.router.navigate (['history']);
}

  sendEmail(email): void {
    this.es.sendEmailObservable(email).subscribe((resp) => {
      console.log(resp.url);
    },
    (error) => {
      console.log(error);
    }
  );
  }

  buildCompetencyString(): void {
    let competencyString = '<br><ul>';
    if ((<HTMLInputElement>document.getElementById('commBox')).checked === true) {
      competencyString += '<li><strong>Communication</strong></li>';
    }
    if ((<HTMLInputElement>document.getElementById('custBox')).checked === true ) {
      competencyString += '<li><strong>Customer Focus</strong></li>';
    }
    if ((<HTMLInputElement>document.getElementById('judgeBox')).checked === true ) {
      competencyString += '<li><strong>Judgement</strong></li>';
    }
    if ((<HTMLInputElement>document.getElementById('jobBox')).checked === true ) {
      competencyString += '<li><strong>Job Specific Skills</strong></li>';
    }
    if ((<HTMLInputElement>document.getElementById('valueBox')).checked === true ) {
      competencyString += '<li><strong>Live the Values</strong></li>';
    }
    if ((<HTMLInputElement>document.getElementById('teamBox')).checked === true ) {
      competencyString += '<li><strong>Teamwork</strong></li>';
    }
    competencyString += '</ul><br>';
    localStorage.setItem('competencyString', competencyString);
  }

  checkRecognitionReadiness() {
        if ((<HTMLInputElement>document.getElementById('commBox')).checked === true ||
        ((<HTMLInputElement>document.getElementById('custBox')).checked === true ) ||
        ((<HTMLInputElement>document.getElementById('judgeBox')).checked === true ) ||
        ((<HTMLInputElement>document.getElementById('jobBox')).checked === true ) ||
        ((<HTMLInputElement>document.getElementById('valueBox')).checked === true ) ||
        ((<HTMLInputElement>document.getElementById('teamBox')).checked === true )) {
          localStorage.setItem('competencySelected', 'true');
          this.buildCompetencyString();
        } else {
        localStorage.setItem('competencySelected', 'false');
        }
        if ((<HTMLInputElement>document.getElementById('creditRadio').childNodes[1].childNodes[1].childNodes[0]).checked === true) {
          if (this.credits > 0) {
            localStorage.setItem('hasRightCredits', 'true');
          } else {
            localStorage.setItem('hasRightCredits', 'false');
          }
        } else if ((<HTMLInputElement>document.getElementById('creditRadio').childNodes[3].childNodes[1].childNodes[0]).checked === true) {
          if (this.capOneCredits > 4) {
            localStorage.setItem('hasRightCredits', 'true');
          } else {
            localStorage.setItem('hasRightCredits', 'false');
          }
        }
        if ((localStorage.getItem('nomineeSelected') === 'true') &&
          (!(localStorage.getItem('creditsTypeId') === null)) &&
          (!((<HTMLInputElement>document.getElementById('primaryFeedback')).value === '')) &&
          (localStorage.getItem('hasRightCredits') === 'true') &&
          (localStorage.getItem('competencySelected') === 'true')) {
            localStorage.setItem('recReadiness', 'true');
        } else {
          localStorage.setItem('recReadiness', 'false');
        }
      }

}

