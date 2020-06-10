import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaticStarRatingComponent } from './static-star-rating.component';

describe('StaticStarRatingComponent', () => {
  let component: StaticStarRatingComponent;
  let fixture: ComponentFixture<StaticStarRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaticStarRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaticStarRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
