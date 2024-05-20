import { Component } from '@angular/core';
import { TokenStorageService } from '../../services/token-storage.service';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  userId:any;
  isLoggedIn = false;
  firstname!: string;
  type!: string;
  users!: User[];
  constructor(public modalService:NgbModal,
    private router:Router,
    private tokenStorageService: TokenStorageService,) { }

  ngOnInit(): void {
    // this.isLoggedIn = !this.tokenStorage.getToken();
    // if (this.isLoggedIn){
    //   const user = this.tokenStorage.getUser();
    //   this.type=user.type
    //   this.firstname = user.firstname;
    //   console.log(this.firstname);
      
    // }
  }
 

//   openLoginModal(type: any){
//     const modalRef=this.modalService.open(LoginComponent,{backdrop: 'static'});
//     modalRef.componentInstance.userId=this.userId;
//     modalRef.result.then((result: any)=>{
//       if(result){

//       }
//     })
// }
openLoginModal(type: string) {
 
  
    const modalRef = this.modalService.open(LoginComponent, { backdrop: 'static' });
    modalRef.componentInstance.userId = this.userId;
    modalRef.componentInstance.loginType = type;
    modalRef.result.then((result: any) => {
      if (result) {
       
      }
    });
  
}

openRegisterModal(){
  const modalRef=this.modalService.open(RegisterComponent,{backdrop: 'static'});
  modalRef.componentInstance.userId=this.userId;
  modalRef.result.then((result: any)=>{
    if(result){

    }
  })

  //this.router.navigate(['/register']);
}

private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return `with: ${reason}`;
  }
}

logout(): void {
  this.tokenStorageService.signOut();
  window.location.reload();
}
}
