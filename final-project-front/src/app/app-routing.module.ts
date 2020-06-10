import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { BaseUrlInterceptor } from './interceptors/base-url.interceptor';

const routes: Routes = [
  {
    path: 'welcome',
    component: WelcomeComponent
  },
  {
    path: 'info',
    component: AboutUsComponent
  },
  {
    path: 'recipes',
    loadChildren: () => import('./recipes/recipes.module').then(m => m.RecipesModule)
  },
  {
    path: '',
    redirectTo: '/welcome',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: '/welcome',
    pathMatch: 'full',
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BaseUrlInterceptor,
      multi: true,
    }
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
