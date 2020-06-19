import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../interfaces/user';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Responses } from '../interfaces/responses';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private logged = false;
  loginChange$ = new EventEmitter<boolean>();
  email$ = new EventEmitter<string>();

  constructor(private http: HttpClient) { }

  private setLogged(logged: boolean) {
    this.logged = logged;
    this.loginChange$.emit(logged);
  }

  login(user: User): Observable<void> {
    return this.http.post<Responses>('/auth/login', user).pipe(
      map(resp => {
        localStorage.setItem('token', resp.accessToken);
        this.setLogged(true);
        this.email$.emit(user.email);
      })
    );
  }

  register(user: User): Observable<void> {
    return this.http.post<void>('/auth/register', user);
  }

  logout(): void {
    this.setLogged(false);
  }

  isLogged(): boolean {
    if (this.logged) {
      return true;
    }
    return false;
  }

}
