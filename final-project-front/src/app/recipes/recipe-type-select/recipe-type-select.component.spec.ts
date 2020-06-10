import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeTypeSelectComponent } from './recipe-type-select.component';

describe('RecipeTypeSelectComponent', () => {
  let component: RecipeTypeSelectComponent;
  let fixture: ComponentFixture<RecipeTypeSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipeTypeSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeTypeSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
