import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  images = ['appetizers', 'rice', 'meat', 'pasta', 'fish', 'vegetarian', 'dessert', 'all'].map((n) => `../../../assets/${n}.jpg`);
  imgRoute = ['entrantes', 'arroz', 'carne', 'pasta', 'pescado', 'vegetariano', 'postres', 'all'];
  imgTitle = ['Entrantes', 'Arroz', 'Carne', 'Pasta', 'Pescado', 'Vegetariano', 'Postres', 'Todas las recetas'];

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
