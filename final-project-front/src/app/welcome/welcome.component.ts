import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(
    private title: Title,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.title.setTitle('El Recetario');
  }

  goForward() {
    this.router.navigate(['recipes']);
  }

}
