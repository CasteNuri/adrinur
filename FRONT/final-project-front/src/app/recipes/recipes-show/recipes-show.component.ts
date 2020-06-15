import { Component, OnInit } from '@angular/core';
import { Recipe } from '../interfaces/recipe';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'recipes-show',
  templateUrl: './recipes-show.component.html',
  styleUrls: ['./recipes-show.component.css']
})
export class RecipesShowComponent implements OnInit {
  recipes: Recipe[] = [];
  search = '';
  headers = {
    image: 'Imagen',
    title: 'Nombre',
    time: 'Tiempo',
    difficulty: 'Dificultad',
    rating: 'Valoración'
  };

  constructor(
    private title: Title,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Recetas | El Recetario');

    this.route.data.subscribe(
      data => this.recipes = data.recipes,
      error => this.recipes = null
    );
  }

  orderByTime(event: Event) {
    event.preventDefault();
    this.recipes.sort((e1, e2) => e1.time - e2.time);
    this.recipes = [...this.recipes];
  }

  orderByRating(event: Event) {
    event.preventDefault();
    this.recipes.sort((e1, e2) => e2.rating - e1.rating);
    this.recipes = [...this.recipes];
  }

  goCreate() {
    this.router.navigate(['recipes/add']);
  }

}
