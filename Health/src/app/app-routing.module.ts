import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PatientdashboardComponent } from './components/patientdashboard/patientdashboard.component';
import { BookappointmentComponent } from './components/bookappointment/bookappointment.component';
import { PaymentComponent } from './components/payment/payment.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { LabReportComponent } from './components/labreport/labreport.component';
import { RegisterComponent } from './components/register/register.component';
import { DoctordashboardComponent } from './components/doctordashboard/doctordashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { DocotorListComponent } from './components/doctor-list/doctor-list.component';
import { AddDoctorComponent } from './components/add-doctor/add-doctor.component';
//import { ShopComponent } from './components/shop/shop.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { ReviewComponent } from './components/review/review.component';
import { PatientlistComponent } from './components/patientlist/patientlist.component';
import { BookingdetailsComponent } from './components/bookingdetails/bookingdetails.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, // Redirect to home
  { path: 'home', component: HomeComponent }, // Route for the home pag
  { path: 'navbar', component: NavbarComponent },
  { path: 'patient-dashboard', component: PatientdashboardComponent },
  { path: 'bookappointment', component: BookappointmentComponent },
  { path: 'payment', component: PaymentComponent },
  { path: 'userprofile', component: UserprofileComponent },
  { path: 'labreport', component: LabReportComponent },
  { path: 'review', component: ReviewComponent },
  { path: 'appointments', component: AppointmentsComponent },
  { path: 'doctor-dashboard', component: DoctordashboardComponent },
  {
    path: 'admin', component: AdminDashboardComponent, children: [
      {path: 'doctors', component: DocotorListComponent},
      { path: 'patients', component: PatientlistComponent },
      { path: 'bookingdetails', component: BookingdetailsComponent }
    ]
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
