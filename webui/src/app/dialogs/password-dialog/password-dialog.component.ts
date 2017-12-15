import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-password-dialog',
  templateUrl: './password-dialog.component.html',
  styleUrls: ['./password-dialog.component.css']
})
export class PasswordDialogComponent implements OnInit {
  isLinear = true;
  firstFormGroup: FormGroup;
  error:boolean=false;
  generatingHash:boolean=true;
  success:boolean=false;
  constructor(public dialogRef: MatDialogRef<PasswordDialogComponent>,
              private _formBuilder: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: any){ }

  ngOnInit() {
      this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
  }

  modifyError()
  {
      if(this.error){
          this.error=false;
      }
  }

  checkCode(form:NgForm){
      console.log(form.form.value);
  }
}
