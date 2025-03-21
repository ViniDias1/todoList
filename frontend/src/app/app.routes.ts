import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MainScreenComponent } from './main-screen/main-screen.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'main', component: MainScreenComponent }
];
