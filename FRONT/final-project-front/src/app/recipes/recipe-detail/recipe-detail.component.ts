import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from '../interfaces/recipe';
import { RecipesService } from '../services/recipes.service';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {
  recipe: Recipe;
  ingredientsList: string[] = [];
  logged = false;

  constructor(
    private title: Title,
    private route: ActivatedRoute,
    private recipesService: RecipesService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Recetas | El Recetario');

    this.logged = this.authService.isLogged();

    this.route.data.subscribe(
      data => {
        this.recipe = data.recipe;
        if (this.recipe) {
          this.title.setTitle(this.recipe.title + ' | El Recetario');
          this.ingredientsList = this.recipe.quantities.split('; ');
        }
      },
      error => this.recipe = null
    );
  }

  eliminateRecipe() {
    if (confirm('¿Seguro que quieres eliminar la receta?')) {
      this.recipesService.deleteRecipe(this.recipe.codRec).subscribe(
        ok => this.router.navigate(['recipes']),
        error => console.error(error)
      );
    }
  }

  switchFavorite(favorite: boolean) {
    const oldFav = this.recipe.favorite;
    this.recipe.favorite = favorite;
    this.recipesService.updateFav(this.recipe.codRec, favorite).subscribe({
      error: error => this.recipe.favorite = oldFav
    });
  }

  changeRating(rating) {
    const oldRating = this.recipe.rating;
    this.recipe.rating = rating;
    this.recipesService.updateRating(this.recipe.codRec, this.recipe.rating).subscribe({
      error: error => this.recipe.rating = oldRating
    });
  }

}
