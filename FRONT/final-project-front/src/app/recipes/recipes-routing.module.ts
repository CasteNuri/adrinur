import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecipeTypeSelectComponent } from './recipe-type-select/recipe-type-select.component';
import { RecipesShowComponent } from './recipes-show/recipes-show.component';
import { RecipeAddComponent } from './recipe-add/recipe-add.component';
import { SaveChangesGuard } from '../shared/guards/save-changes.guard';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { RecipesShowResolve } from './resolvers/recipes-show.resolve';
import { RecipeDetailResolve } from './resolvers/recipe-detail.resolve';


const routes: Routes = [
  {
    path: '',
    component: RecipeTypeSelectComponent
  },
  {
    path: 'add',
    component: RecipeAddComponent,
    canDeactivate: [SaveChangesGuard]
  },
  {
    path: ':type/:id',
    component: RecipeDetailComponent,
    resolve: {
      recipe: RecipeDetailResolve
    }
  },
  {
    path: ':type',
    component: RecipesShowComponent,
    resolve: {
      recipes: RecipesShowResolve
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RecipesRoutingModule { }
