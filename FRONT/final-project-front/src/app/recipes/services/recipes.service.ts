import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recipe } from '../interfaces/recipe';
import { RecipesResponse, RecipeResponse } from 'src/app/recipes/interfaces/responses';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private readonly recipesURL = '/recipes';

  constructor(private http: HttpClient) { }

  getAllRecipes(): Observable<Recipe[]> {
    return this.http.get<RecipesResponse>(this.recipesURL).pipe(
      map(resp => resp.recipes)
    );
  }

  getRecipesByType(type: string): Observable<Recipe[]> {
    return this.http.get<RecipesResponse>(this.recipesURL + '/resume/' + type).pipe(
      map(resp => resp.recipes)
    );
  }

  getRecipesById(id: number): Observable<Recipe> {
    return this.http.get<RecipeResponse>(this.recipesURL + '/detail/' + id).pipe(
      map(resp => resp.recipe)
    );
  }

  createRecipe(recipe: Recipe): Observable<Recipe> {
    return this.http.post<RecipeResponse>(this.recipesURL, recipe).pipe(
      map(resp => resp.recipe)
    );
  }

}
