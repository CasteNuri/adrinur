import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { User } from '../interfaces/user';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  userLogin: User = {
    email: '',
    password: ''
  };
  error = '';

  constructor(
    private title: Title,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Iniciar sesión | El Recetario');
  }

  login() {
    this.authService.login(this.userLogin).subscribe(
      () => this.router.navigate(['/recipes']),
      error => this.error = 'Email y/o contraseña no válidos'
    );
  }

}
