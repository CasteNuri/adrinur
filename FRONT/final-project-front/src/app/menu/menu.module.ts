import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopMenuComponent } from './top-menu/top-menu.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [TopMenuComponent],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [TopMenuComponent]
})
export class MenuModule { }
