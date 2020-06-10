import { Component, OnInit } from '@angular/core';
import { Recipe } from '../interfaces/recipe';
import { RecipesService } from '../services/recipes.service';
import { Title } from '@angular/platform-browser';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';
import { ComponentDeactivate } from 'src/app/shared/guards/save-changes.guard';

@Component({
  selector: 'recipe-add',
  templateUrl: './recipe-add.component.html',
  styleUrls: ['./recipe-add.component.css']
})
export class RecipeAddComponent implements OnInit, ComponentDeactivate {
  newRecipe: Recipe;
  fileImg: string;
  saved: boolean;

  constructor(
    private recipesService: RecipesService,
    private title: Title,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.title.setTitle('Añadir Receta | El Recetario');

    this.resetForm();
  }

  addRecipe() {
    this.recipesService.createRecipe(this.newRecipe).subscribe(
      recipe => {
        this.saved = true;
        this.router.navigate(['recipes']);
      },
      error => console.error(error)
    );
  }

  private resetForm() {
    this.newRecipe = {
      title: '',
      image: '',
      quantities: '',
      time: 0,
      difficulty: '',
      description: '',
      type: '',
      rating: 0,
      favourite: false
    };
    this.fileImg = '';
  }

  changeImage(fileInput: HTMLInputElement) {
    if (!fileInput.files || fileInput.files.length === 0) { return; }
    const reader: FileReader = new FileReader();
    reader.readAsDataURL(fileInput.files[0]);
    reader.addEventListener('loadend', e => {
      this.newRecipe.image = reader.result as string;
    });
  }

  canDeactivate(): boolean {
    if (this.saved) {
      return true;
    }
    return confirm('¿Quieres abandonar la página? Los cambios no se guardarán.');
  }

  validClasses(ngModel: NgModel, validClass: string, invalidClass: string) {
    return {
      [validClass]: ngModel.touched && ngModel.valid,
      [invalidClass]: ngModel.touched && ngModel.invalid
    };
  }

  validClassesTime(ngModel: NgModel, validClass: string, invalidClass: string) {
    return {
      [validClass]: ngModel.touched && ngModel.valid && this.newRecipe.time > 0,
      [invalidClass]: ngModel.touched && (ngModel.invalid || this.newRecipe.time === 0)
    };
  }

}
