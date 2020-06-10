import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { StaticStarRatingComponent } from './static-star-rating/static-star-rating.component';



@NgModule({
  declarations: [StarRatingComponent, StaticStarRatingComponent],
  imports: [
    CommonModule
  ],
  exports: [
    StarRatingComponent,
    StaticStarRatingComponent
  ]
})
export class SharedModule { }
