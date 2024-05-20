import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbActiveModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule, ɵInternalFormsSharedModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthInterceptor } from './services/interceptor/auth.interceptor';
import { PatientdashboardComponent } from './components/patientdashboard/patientdashboard.component';
import { BookappointmentComponent } from './components/bookappointment/bookappointment.component';
import { PaymentComponent } from './components/payment/payment.component';
import { CommonModule } from '@angular/common';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { LabReportComponent } from './components/labreport/labreport.component';
import { DoctordashboardComponent } from './components/doctordashboard/doctordashboard.component';
import { Appointment } from './models/appointment';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { PrescriptionsComponent } from './components/prescriptions/prescriptions.component';
import { GridModule } from '@progress/kendo-angular-grid';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AddDoctorComponent } from './components/add-doctor/add-doctor.component';
import { DocotorListComponent } from './components/doctor-list/doctor-list.component';
import { CustomDatePipe } from './custom/custom-date.pipe';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
import { ReviewComponent } from './components/review/review.component';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { PatientlistComponent } from './components/patientlist/patientlist.component';
import { ViewreportsComponent } from './components/viewreports/viewreports.component';
import { BookingdetailsComponent } from './components/bookingdetails/bookingdetails.component';
import { ViewprescriptionComponent } from './components/viewprescription/viewprescription.component';
//import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    PatientdashboardComponent,
    BookappointmentComponent,
    PaymentComponent,
    UserprofileComponent,
    LabReportComponent,
    DoctordashboardComponent,
    AppointmentsComponent,
    PrescriptionsComponent,
    ViewprescriptionComponent,
    AdminDashboardComponent,
    AddDoctorComponent,
    DocotorListComponent,
    PatientlistComponent,
    ViewreportsComponent,
    BookingdetailsComponent,
    ReviewComponent,
   CustomDatePipe
  ],
  imports: [
    CommonModule,
    ɵInternalFormsSharedModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    GridModule,
    NgxExtendedPdfViewerModule,
    ToastrModule.forRoot()

  ],
  providers: [CustomDatePipe,NgbActiveModal
    //{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
 bootstrap: [AppComponent]
})
export class AppModule { }
