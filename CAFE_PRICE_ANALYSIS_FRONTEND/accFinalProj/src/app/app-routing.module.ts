import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { PriceanalysisComponent } from './priceanalysis/priceanalysis.component';
import { ReportsComponent } from './reports/reports.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'dashboard/:empId', component: HomepageComponent },
  { path: 'analysis', component: PriceanalysisComponent },
  { path: 'reports', component: ReportsComponent },
  { path: 'feedback', component: FeedbackComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
