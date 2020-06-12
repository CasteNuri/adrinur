import { Injectable } from '@angular/core';
import { RecipesService } from '../services/recipes.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { Recipe } from '../interfaces/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeDetailResolve implements Resolve<Recipe> {

  constructor(private recipesService: RecipesService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Recipe> {
        const id = +route.params.id;
        return this.recipesService.getRecipesById(id);
  }
}
