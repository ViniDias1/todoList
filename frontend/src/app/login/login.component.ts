import { Component, inject } from '@angular/core';
import { LoginService } from '../services/login.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms'; // Importação necessária
import { R } from '@angular/cdk/keycodes';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private router: Router) {}

  name = 'Login';
  private loginService = inject(LoginService);


  loginForm: FormGroup = new FormGroup({
    name: new FormControl(""),
    email: new FormControl(""),
    // password: new FormControl(""),

  });

  login() {
    const formValues = this.loginForm.value;
    // debugger;
    this.loginService.login(formValues).subscribe({
      next: (data) => {
        console.log('Login bem-sucedido:', data);
        this.router.navigate(['/main']); // Redireciona apenas após o sucesso
      },
      error: (error) => {
        console.error('Erro no login:', error);
      }
    });
    
  }

  getAllUsers() {
    this.loginService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }

}