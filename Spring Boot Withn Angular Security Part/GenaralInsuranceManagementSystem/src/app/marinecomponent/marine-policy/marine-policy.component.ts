import { Component, OnInit } from '@angular/core';
import { MarineDetailsModel } from '../../model/MarineDetailsModel';
import { MarinedetailsService } from '../../service/marinedetails.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-marine-policy',
  templateUrl: './marine-policy.component.html',
  styleUrls: ['./marine-policy.component.css']
})
export class MarinePolicyComponent implements OnInit {

  marinedetails!: MarineDetailsModel[];
  searchmarinedetails!: MarineDetailsModel[];
  searchTerm: string = '';
  sortBy: 'policyholder' | 'id' | 'bankName' = 'policyholder'; 

  constructor(
    private marinedetailsService: MarinedetailsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadMarineDetails();
  }

  // Load all marine details from the service
  loadMarineDetails() {
    this.marinedetailsService.getMarinedetails().subscribe({
      next: (data: MarineDetailsModel[]) => {
        this.marinedetails = data;
        this.searchmarinedetails = [...data]; // Initialize search list with all marine details
      },
      error: error => {
        console.error('Error loading marine details', error);
      }
    });
  }

  // Delete marine insurance details and update the UI
  deleteMarineDetails(id: number) {
    this.marinedetailsService.deleteMarineList(id).subscribe({
      next: res => {
        console.log('Marine detail deleted successfully', res);
        this.marinedetails = this.marinedetails.filter(detail => detail.id !== id);
        this.searchMarinePolicy(); // Update search results after deletion
      },
      error: error => {
        console.error('Error deleting marine detail', error);
      }
    });
  }

  // Edit marine insurance policy
  editMarinePolicy(id: number) {
    this.router.navigate(['updatemarinelist', id]);
  }

  // View marine insurance details
  detailsMarineInsurance(id: number) {
    this.router.navigate(['marinedetails', id]);
  }

  // Navigate to the create marine policy page
  navigateToAddMarinePolicy() {
    this.router.navigateByUrl('/createmarinelist');
  }

  // Navigate to the create marine bill page
  navigateToAddMarineBill() {
    this.router.navigateByUrl('/createmarinebill');
  }

  // Search for marine policies based on search term
  searchMarinePolicy() {
    const lowerCaseSearchTerm = this.searchTerm.trim().toLowerCase();
    this.searchmarinedetails = this.marinedetails.filter(item =>
      item.policyholder?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.bankName?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.id?.toString().includes(lowerCaseSearchTerm)
    );
  }

  // Sort marine policies by selected field
  sortMarinePolicy() {
    const sortField = this.sortBy;
    this.searchmarinedetails.sort((a, b) => {
      const valA = a[sortField]?.toString().toLowerCase() || '';
      const valB = b[sortField]?.toString().toLowerCase() || '';
      return valA < valB ? -1 : valA > valB ? 1 : 0;
    });
  }
}
