import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../interfaces/user';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private logged = false;
  loginChange$ = new EventEmitter<boolean>();
  nickName$ = new EventEmitter<string>();

  constructor(private http: HttpClient) { }

  private setLogged(logged: boolean) {
    this.logged = logged;
    this.loginChange$.emit(logged);
  }

  login(user: User): Observable<void> {
    return this.http.post<void>('/users/login', user).pipe(
      map(ok => {
        this.setLogged(true);
        this.nickName$.emit(user.userName);
      })
    );
  }

  register(user: User): Observable<void> {
    return this.http.post<void>('/users', user);
  }

  logout(): void {
    this.setLogged(false);
  }

 }
