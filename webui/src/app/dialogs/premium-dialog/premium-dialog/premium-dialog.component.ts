import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { UserService } from '../../../services/user.service';
import { TopBarProxyService } from '../../../services/topBarProxy.service';

import { PaymentInfo } from '../../../models/paymentInfo.model';

@Component({
    selector: 'app-premium-dialog',
    templateUrl: './premium-dialog.component.html',
    styleUrls: ['./premium-dialog.component.css']
})
export class PremiumDialogComponent implements OnInit {

    private invalid = false;
    private paymentInfo: PaymentInfo;
    private success = false;
    private errorMessage: string;
    private cardType = 'visa';
    private loading = false;

    constructor(private router: Router,
        private topBarProxyService: TopBarProxyService,
        private userService: UserService,
        public dialogRef: MatDialogRef<PremiumDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any) { }

    ngOnInit() {
    }

    goPremium(form: NgForm) {
        this.paymentInfo = new PaymentInfo();
        this.paymentInfo.billingAddress = form.form.value.address;
        this.paymentInfo.cardHolderName = form.form.value.name;
        this.paymentInfo.creditCardNumber = form.form.value.card_number;
        this.paymentInfo.cvv = form.form.value.cvv;
        this.paymentInfo.expirationDate = form.form.value.month + '/' + form.form.value.year;
        this.loading = true;
        this.userService.goPremium(this.paymentInfo)
            .subscribe(
            (success: boolean) => {
                this.loading = false;
                this.success = success;
                this.invalid = !success;
                if (this.success) {
                    this.topBarProxyService.setPremium(true);
                }
            },
            (error: any) => {
                this.loading = false;
                this.success = false;
                this.errorMessage = error;
                this.invalid = true;
                console.log('error going premium');
            });
        // setTimeout(() => { this.loading = false; } , 2000);
    }

    invalidChange() {
        if (this.invalid) {
            this.invalid = false;
        }
    }

    changeCardType(data: any) {
        this.cardType = data.value;
    }

    getCardPattern() {
        return (this.cardType === 'visa') ? /^\d{16}$/ : /^\d{15}$/;
    }

    getCvvPattern() {
        return (this.cardType === 'visa') ? /^\d{3}$/ : /^\d{4}$/;
    }

    closeDialog() {
        this.dialogRef.close();
    }

}
