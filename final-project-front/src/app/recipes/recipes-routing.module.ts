import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecipeTypeSelectComponent } from './recipe-type-select/recipe-type-select.component';
import { RecipesShowComponent } from './recipes-show/recipes-show.component';
import { RecipeAddComponent } from './recipe-add/recipe-add.component';
import { SaveChangesGuard } from '../shared/guards/save-changes.guard';


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
  // {
  //   path: 'all/:id',
  //   component:
  // },
  {
    path: ':type',
    component: RecipesShowComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RecipesRoutingModule { }
