import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recipe } from '../interfaces/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private readonly recipesURL = '/recipes';

  constructor(private http: HttpClient) { }

  getAllRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipesURL);
  }

  findAllFavorites(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipesURL + '/favorites');
  }

  getRecipesByType(type: string): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipesURL + '/resume/' + type);
  }

  getRecipesById(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(this.recipesURL + '/detail/' + id);
  }

  createRecipe(recipe: Recipe): Observable<Recipe> {
    return this.http.post<Recipe>(this.recipesURL, recipe);
  }

  deleteRecipe(id: number): Observable<void> {
    return this.http.delete<void>(this.recipesURL + '/' + id);
  }

  updateFav(id: number, favorite: boolean): Observable<Recipe> {
    return this.http.put<Recipe>(this.recipesURL + '/changefav/' + id, +favorite);
  }

  updateRating(id: number, rating: number): Observable<Recipe> {
    return this.http.put<Recipe>(this.recipesURL + '/changerating/' + id, rating);
  }

}
