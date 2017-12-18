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
                this.capital(instrumentList);
                this.instruments = this.alphabeticalSort(instrumentList);
                this.loading = false;
            });
    }

    capital(instruments: Instrument[]) {
        for (let i = 0; i < instruments.length; i++) {
            instruments[i].name = instruments[i].name.replace(/(?:^|\s)\S/g, function(a) { return a.toUpperCase(); });
        }
    }

    alphabeticalSort(instruments: Instrument[]) {
        let min;
        let index;
        console.log(instruments);
        for (let i = 0 ; i < instruments.length - 1; i++) {
            min = instruments[i].name;
            index = i;
            for (let j = i + 1 ; j < instruments.length; j++) {
                if (instruments[j].name < min) {
                    min = instruments[j].name;
                    index = j;
                }
            }
            const tmp = instruments[i];
            instruments[i] = instruments[index];
            instruments[index] = tmp;
        }
        console.log(instruments);
        return instruments;
    }

}
