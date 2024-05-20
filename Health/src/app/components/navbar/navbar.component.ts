import { Component, Directive, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/token-storage.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ReviewComponent } from '../review/review.component';
import { PaymentComponent } from '../payment/payment.component';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isCollapsed: boolean = true;
  firstname!: string;
  user: any;
  constructor(private tokenStorage: TokenStorageService,
    public modalService:NgbModal,
    
    private router: Router) { }

  ngOnInit(): void {
    this.user=this.tokenStorage.getUser();
    this.firstname= this.user.firstname;
  }

  home(){
    
    if (this.user.type === '[ROLE_patient]') {
      this.router.navigate(['/patient-dashboard']);
      
    }
    else{
      this.router.navigate(['/doctor-dashboard']);
      
    }
  }
  
  logout(): void {
    this.tokenStorage.signOut();
    console.log("logout successfully");
    this.router.navigate(["/home"]);
  }

  isPatient(): boolean {
    const user = localStorage.getItem('auth-user');
    if (user) {
      return JSON.parse(user).type === '[ROLE_patient]';
    }
    return false; 
  }


  isDoctor(): boolean {
     this.user = localStorage.getItem('auth-user');
    if (this.user) {
      return JSON.parse(this.user).type === '[ROLE_doctor]';
    }
    return false; 
  }

  openReview(){
    const modalRef=this.modalService.open(ReviewComponent,{backdrop: 'static'});
    modalRef.componentInstance.userId=this.user.id;
    modalRef.result.then((result: any)=>{
      if(result){
  
      }
    })
  
  }

  openPayment(){
    const modalRef=this.modalService.open(PaymentComponent,{backdrop: 'static'});
    modalRef.componentInstance.userId=this.user.id;
    modalRef.result.then((result: any)=>{
      if(result){
  
      }
    })
  
  }
}