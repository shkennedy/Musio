import { Component, OnInit } from '@angular/core';
import { Instrument } from '../../models/instrument.model';

@Component({
  selector: 'app-instrument-view',
  templateUrl: './instrument-view.component.html',
  styleUrls: ['./instrument-view.component.css']
})
export class InstrumentViewComponent implements OnInit {

  private loading = true;
  private instruments: Instrument[];
  constructor(
  ) { }

  ngOnInit() {
  }

}
