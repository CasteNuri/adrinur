import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from '../interfaces/recipe';
import { RecipesService } from '../services/recipes.service';

@Component({
  selector: 'recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {
  recipe: Recipe;
  ingredientsList: string[] = [];

  constructor(
    private title: Title,
    private route: ActivatedRoute,
    private recipesService: RecipesService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Recetas | El Recetario');

    this.route.data.subscribe(
      data => this.recipe = data.recipe
    );

    this.title.setTitle(this.recipe.title + ' | El Recetario');

    this.ingredientsList = this.recipe.quantities.split('; ');
  }

  eliminateRecipe() {
    this.recipesService.deleteRecipe(this.recipe.codRec).subscribe(
      ok => this.router.navigate(['recipes']),
      error => console.error(error)
    );
  }

}
