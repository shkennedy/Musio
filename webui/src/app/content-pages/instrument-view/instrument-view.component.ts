import { Component, OnInit } from '@angular/core';
import { Instrument } from '../../models/instrument.model';
import { InstrumentService } from '../../services/instrument.service';

@Component({
    selector: 'app-instrument-view',
    templateUrl: './instrument-view.component.html',
    styleUrls: ['./instrument-view.component.css']
})
export class InstrumentViewComponent implements OnInit {

    private loading = true;
    private instruments: Instrument[];

    constructor(
        private instrumentService: InstrumentService
    ) { }

    ngOnInit() {
        this.instrumentService.getAllInstruments()
            .subscribe((instrumentList: Instrument[]) => {
                this.instruments = instrumentList;
                this.loading = false;
            });
    }

}
