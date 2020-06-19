import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {
  logged = false;
  email: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.loginChange$.subscribe(
      (logged: boolean) => {
        this.logged = logged;
        if (logged) {
          this.authService.email$.subscribe(
            (email: string) => this.email = email
          );
        }
      }
    );
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/auth/login']);
  }

}
