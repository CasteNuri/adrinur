import { Injectable } from '@angular/core';
import { RecipesService } from '../services/recipes.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Recipe } from '../interfaces/recipe';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipesShowResolve implements Resolve<Recipe[]> {

  constructor(private recipesService: RecipesService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Recipe[]> {
        const type = '' + route.params.type;
        if (type === 'all') {
          return this.recipesService.getAllRecipes();
        } else if (type === 'favorite') {
          return this.recipesService.findAllFavorites();
        } else {
          return this.recipesService.getRecipesByType(type).pipe(
            catchError(e => of(null))
          );
        }
  }

}
