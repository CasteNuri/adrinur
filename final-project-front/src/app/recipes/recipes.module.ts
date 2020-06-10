import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RecipesRoutingModule } from './recipes-routing.module';
import { RecipeTypeSelectComponent } from './recipe-type-select/recipe-type-select.component';
import { RecipesShowComponent } from './recipes-show/recipes-show.component';
import { RecipeItemComponent } from './recipe-item/recipe-item.component';
import { RecipeFilterPipe } from './pipes/recipe-filter.pipe';
import { SharedModule } from '../shared/shared.module';
import { RecipeAddComponent } from './recipe-add/recipe-add.component';


@NgModule({
  declarations: [
    RecipeTypeSelectComponent,
    RecipesShowComponent,
    RecipeItemComponent,
    RecipeFilterPipe,
    RecipeAddComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RecipesRoutingModule,
    SharedModule
  ]
})
export class RecipesModule { }
