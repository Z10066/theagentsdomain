import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit} from '@angular/core';
import { LeftMenuComponent } from 'app/layouts/left-menu/left-menu.component';

@Component({
  selector: 'jhi-payments',
  standalone: true,
  imports: [LeftMenuComponent],
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class PaymentsComponent implements OnInit {
  pricingTableId!: string;
  publishableKey!: string;

  ngOnInit(): void {
    this.pricingTableId = 'prctbl_1PIkmEDYZSbLE5Ng39jAv6iF';
    this.publishableKey = 'pk_test_51Oki2xDYZSbLE5NgEThsvl3bd7FJPGPpU4h96YbxTDvzfHHdCF7bgm4d3fv01LLgndLeOIrW4zQjxeNipZhIxL1F00BRv2j1i3';
  }
}