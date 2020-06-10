import { Pipe, PipeTransform } from '@angular/core';
import { Recipe } from '../interfaces/recipe';

@Pipe({
  name: 'recipeFilter'
})
export class RecipeFilterPipe implements PipeTransform {

  transform(recipes: Recipe[], search: string): Recipe[] {
    return recipes.filter(e =>
      e.title.toLowerCase().includes(search.toLowerCase()));
  }

}
