import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-picture-dialog',
  templateUrl: './picture-dialog.component.html',
  styleUrls: ['./picture-dialog.component.css']
})
export class PictureDialogComponent implements OnInit {
    fileName:string='No File Selected';
    fileObject:any;
    constructor(public dialogRef: MatDialogRef<PictureDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any){ }

  ngOnInit() {}

  fileSelected(fileInput:any){
      const reader= new FileReader();
      if(fileInput.target.files && fileInput.target.files[0]){
          reader.readAsDataURL(fileInput.target.files[0]);
          reader.onload=((e)=>{
             let data=e.target['result'].split(',');
             this.fileObject=data[1];
             this.fileName=fileInput.target.files[0].name;
             // console.log(this.fileObject);
             // console.log(this.fileName);
         });
     }
     else return;
  }

  changeProfilePicture()
  {
      // console.log(this.fileObject);
      //do stuff
      console.log("changed");
  }

  }
