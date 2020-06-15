import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { StaticStarRatingComponent } from './static-star-rating/static-star-rating.component';
import { PascalCaseFilterPipe } from './pipes/pascal-case-filter.pipe';
import { FavoriteComponent } from './favorite/favorite.component';
import { SameValueDirective } from './validators/same-value.directive';


@NgModule({
  declarations: [
    StarRatingComponent,
    StaticStarRatingComponent,
    PascalCaseFilterPipe,
    FavoriteComponent,
    SameValueDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    StarRatingComponent,
    StaticStarRatingComponent,
    PascalCaseFilterPipe,
    FavoriteComponent,
    SameValueDirective
  ]
})
export class SharedModule { }
