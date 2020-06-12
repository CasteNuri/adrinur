import { TestBed } from '@angular/core/testing';

import { RecipeDetailResolve } from './recipe-detail.resolve';

describe('RecipeDetailService', () => {
  let service: RecipeDetailResolve;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecipeDetailResolve);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
