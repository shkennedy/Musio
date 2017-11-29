import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../services/user.service';

import { PaymentInfo } from '../../models/paymentInfo.model';

@Component({
    selector: 'app-go-premium',
    templateUrl: './go-premium.component.html',
    styleUrls: ['./go-premium.component.css']
})
export class GoPremiumComponent implements OnInit {

    private paymentInfo: PaymentInfo;
    private success: boolean;

    constructor(
        private router: Router,
        private userService: UserService
    ) { }

    ngOnInit() { }

    goPremium() {
        this.userService.goPremium(this.paymentInfo)
        .subscribe(
            (success: boolean) => {
                this.success = success;
            },
            (error: any) => {
                this.success = false;
                console.log('error going premium');
            }
        );
    }
}
