import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatemarineinsurancelistComponent } from './updatemarineinsurancelist.component';

describe('UpdatemarineinsurancelistComponent', () => {
  let component: UpdatemarineinsurancelistComponent;
  let fixture: ComponentFixture<UpdatemarineinsurancelistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdatemarineinsurancelistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatemarineinsurancelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
