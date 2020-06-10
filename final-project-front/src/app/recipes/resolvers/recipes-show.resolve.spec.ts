import { TestBed } from '@angular/core/testing';

import { RecipesShowResolve } from './recipes-show.resolve';

describe('RecipesShowService', () => {
  let service: RecipesShowResolve;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecipesShowResolve);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
