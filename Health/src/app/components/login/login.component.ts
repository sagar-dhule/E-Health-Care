import { Component, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../models/user';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorageService } from '../../services/token-storage.service';
import { Router } from '@angular/router';
import { RegisterComponent } from '../register/register.component';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  @Input() userId: any;
  @Input() loginType: any;
  closeResult!: string;
  loginForm!: FormGroup;
  formGroup: any;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = 'Invalid Email and Password';
  type!: string;
  users!: User[];
  loginModal = true;

  constructor(public activeModal: NgbActiveModal,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    public modalService:NgbModal,
    public router:Router
    ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'email': new FormControl('', [Validators.required,Validators.email]),
      'password': new FormControl('', Validators.required)
    });
  
  }

  onSubmit(data: any) {
    console.log(JSON.stringify(data));

    let obj = {
      email: data.email,
      password: data.password
    }
    console.log(obj);

    this.authService.login(obj).subscribe((res: any) => {
      console.log(res.type);
      
      
      if(res.type == "[ROLE_admin]"){
        
        this.tokenStorage.saveUser(res);
        // this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(["/admin/doctors"]);
        this.type = this.tokenStorage.getUser().type;
        localStorage.setItem('auth-token', res.token);
        console.log(localStorage.getItem('auth-token'));
        console.log(res);
        

        
      }
      else if(res.type == "[ROLE_patient]"){
       
        this.tokenStorage.saveUser(res);
        // this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(["/patient-dashboard"]);
        this.type = this.tokenStorage.getUser().type;
        localStorage.setItem('auth-token', res.token);
        console.log(localStorage.getItem('auth-token'));
        console.log(res);
        
      }else if(res.type == "[ROLE_doctor]"){
       
        this.tokenStorage.saveUser(res);
        // this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(["/doctor-dashboard"]);
        this.type = this.tokenStorage.getUser().type;
        localStorage.setItem('auth-token', res.token);
        console.log(localStorage.getItem('auth-token'));
        console.log(res);
        
      }
      else {
        
        this.isLoginFailed = true;
        console.log("error");
      }
        
    })
    
  } 
  reloadPage(): void {
    window.location.reload();}

  dismiss():void {
    this.activeModal.dismiss();
    
    }

    openRegisterModal(){
      const modalRef=this.modalService.open(RegisterComponent,{backdrop: 'static'});
      modalRef.componentInstance.userId=this.userId;
      modalRef.result.then((result)=>{
        if(result){
        }
      })
    } 
    // openForgotModal(){
    //   const modalRef=this.modalService.open(ForgotPasswordComponent,{backdrop: 'static'});
    //   modalRef.componentInstance.userId=this.userId;
    //   modalRef.result.then((result)=>{
    //     if(result){}
    //   })
    // } 
}


