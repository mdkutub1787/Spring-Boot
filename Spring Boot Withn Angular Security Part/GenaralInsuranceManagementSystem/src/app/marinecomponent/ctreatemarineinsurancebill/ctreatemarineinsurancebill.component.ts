import { Component, OnInit } from '@angular/core';
import { MarinebillService } from '../../service/marinebill.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MarineBillModel } from '../../model/MarineBill.Model';
import { MarineDetailsModel } from '../../model/MarineDetailsModel';
import { MarinedetailsService } from '../../service/marinedetails.service';

@Component({
  selector: 'app-ctreatemarineinsurancebill',
  templateUrl: './ctreatemarineinsurancebill.component.html',
  styleUrls: ['./ctreatemarineinsurancebill.component.css']
})
export class CtreatemarineinsurancebillComponent implements OnInit {

  marineBill: MarineBillModel = new MarineBillModel();
  policyholders: MarineDetailsModel[] = [];
  selectedPolicyholder: MarineDetailsModel | undefined;
  marineBillId: number | null = null;  // To check if it's an update or save

  constructor(
    private marineBillService: MarinebillService,
    private marineDetailsService: MarinedetailsService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Load all policyholders for dropdown
    this.marineDetailsService.getMarinedetails().subscribe((data: MarineDetailsModel[]) => {
      this.policyholders = data;
    });

    // Check if we're updating an existing bill
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.marineBillId = +id;  // Convert id to number
        this.loadMarineBill(this.marineBillId);
      }
    });
  }

  // Load the Marine Bill by ID (for update case)
  loadMarineBill(id: number): void {
    this.marineBillService.getByMarineBillId(id).subscribe((data: MarineBillModel) => {
      this.marineBill = data;
      this.selectedPolicyholder = this.marineBill.marineDetails;  // Set selected policyholder
    });
  }


  onPolicyholderChange(): void {
    if (this.selectedPolicyholder) {
      this.marineBill.marineDetails.sumInsured = this.selectedPolicyholder.sumInsured;
      this.marineBill.marineDetails.bankName = this.selectedPolicyholder.bankName;
      this.calculatePremiums();
    }
  }

  calculatePremiums(): void {
    const marineRate = this.marineBill.marineRate / 100;
    const warSrccRate = this.marineBill.warSrccRate / 100;
    const taxRate = 0.15;

    if (this.marineBill.marineDetails.sumInsured) {
      const netPremium = this.marineBill.marineDetails.sumInsured * (marineRate + warSrccRate);
      this.marineBill.netPremium = this.roundToTwoDecimalPlaces(netPremium);

      const tax = this.marineBill.netPremium * taxRate;
      const grossPremium = this.marineBill.netPremium + tax + (this.marineBill.stampDuty || 0);
      this.marineBill.grossPremium = this.roundToTwoDecimalPlaces(grossPremium);
    }
  }


  // Save or Update Marine Bill based on ID
  saveOrUpdateMarineBill(): void {
    if (!this.selectedPolicyholder) {
      console.log('Please select a policyholder.');
      return;
    }

    this.marineBill.marineDetails = this.selectedPolicyholder;  // Set the selected policyholder

    if (this.marineBillId) {
      // Update case
      this.marineBillService.updateMarineBill(this.marineBillId, this.marineBill).subscribe(
        (response) => {
          console.log('Marine Bill updated successfully', response);
          this.router.navigate(['viewmarinebill']);  // Navigate after update
        },
        (error) => {
          console.error('Error updating marine bill', error);
        }
      );
    } else {
      // Save case
      this.marineBillService.createMarineBill(this.marineBill).subscribe(
        (response) => {
          console.log('Marine Bill saved successfully', response);
          this.router.navigate(['/viewmarinebill']);  // Navigate after save
        },
        (error) => {
          console.error('Error saving marine bill', error);
        }
      );
    }
  }

  // Helper method to round to two decimal places
  private roundToTwoDecimalPlaces(value: number): number {
    return Math.round(value * 100) / 100;
  }

  get roundedSumInsured(): number {
    return Math.round(this.marineBill.marineDetails.sumInsured);
  }


  get roundedNetPremium(): number {
    return Math.round(this.marineBill.netPremium);
  }

  get roundedGrossPremium(): number {
    return Math.round(this.marineBill.grossPremium);
  }

}
