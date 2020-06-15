import { Component, OnInit, OnChanges, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit, OnChanges {
  @Input() favorite: boolean;
  @Output() favoriteChanged = new EventEmitter<boolean>();
  auxFavorite: boolean;

  constructor() { }

  ngOnInit(): void {
    this.restoreRating();
  }

  ngOnChanges() {
    this.restoreRating();
  }

  changeFavorite(favorite: boolean) {
    this.favoriteChanged.emit(!favorite);
  }

  restoreRating() {
    this.auxFavorite = this.favorite;
  }
}
