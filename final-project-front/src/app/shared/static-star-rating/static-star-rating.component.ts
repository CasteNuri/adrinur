import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'static-star-rating',
  templateUrl: './static-star-rating.component.html',
  styleUrls: ['./static-star-rating.component.css']
})
export class StaticStarRatingComponent implements OnInit {
  @Input() rating: number;

  constructor() { }

  ngOnInit(): void {
  }

}
