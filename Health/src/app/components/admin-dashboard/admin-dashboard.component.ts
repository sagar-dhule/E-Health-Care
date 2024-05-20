import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/token-storage.service';
import { AuthService } from '../../services/auth/auth.service';


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  firstname!: string;
  constructor(private tokenStorage: TokenStorageService,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    const user=this.tokenStorage.getUser();
    this.firstname= user.firstname;
  }

  logout(): void {
    this.tokenStorage.signOut();
    // this.authService.logout();
    console.log("logout successfully");
    this.router.navigate(["/home"]);
  }
}
