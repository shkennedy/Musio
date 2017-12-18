import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';

import { UserService } from '../../services/user.service';

@Component({
    selector: 'app-password-dialog',
    templateUrl: './password-dialog.component.html',
    styleUrls: ['./password-dialog.component.css']
})
export class PasswordDialogComponent implements OnInit {

    newPassword = '';

    isLinear = true;
    firstFormGroup: FormGroup;
    emailingUser = true;
    success = false;
    error = false;

    constructor(
        private userService: UserService,
        public dialogRef: MatDialogRef<PasswordDialogComponent>,
        private _formBuilder: FormBuilder,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) { }

    ngOnInit() {
        this.firstFormGroup = this._formBuilder.group({
            firstCtrl: ['', Validators.required]
        });

        this.userService.sendChangePasswordEmail()
            .subscribe((success: boolean) => {
                console.log(`Email sent to user: ${success}`);
            });
    }

    modifyError() {
        if (this.error) {
            this.error = false;
        }
    }

    storeNewPassword(passwordNgForm: NgForm): void {
        this.newPassword = passwordNgForm.form.value.password;
    }

    checkCode(ngForm: NgForm) {
        console.log(ngForm.form.value);
        this.userService.changePassword(ngForm.form.value.code, this.newPassword)
        .subscribe((success: boolean) => {
            if (success) {
                this.success = true;
            } else {
                this.error = true;
            }
        });
    }

    closeDialog() {
        this.dialogRef.close();
    }
}
