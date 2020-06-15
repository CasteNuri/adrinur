import { Injectable } from '@angular/core';
import { RecipesService } from '../services/recipes.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Recipe } from '../interfaces/recipe';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeDetailResolve implements Resolve<Recipe> {

  constructor(private recipesService: RecipesService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Recipe> {
        const id = +route.params.id;
        return this.recipesService.getRecipesById(id).pipe(
          catchError(e => of(null))
        );
  }
}
