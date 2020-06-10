import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'recipe-type-select',
  templateUrl: './recipe-type-select.component.html',
  styleUrls: ['./recipe-type-select.component.css']
})
export class RecipeTypeSelectComponent implements OnInit {

  constructor(private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle('Recetas | El Recetario');
  }

}
