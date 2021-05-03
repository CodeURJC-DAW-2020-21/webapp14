import { Component } from '@angular/core';
import { StoreService } from '../../service/store.service';
import { Local } from '../../model/local.model';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'storeform',
  templateUrl: './storeform.component.html',
  styleUrls: ['./storeform.component.css']
})
export class StoreFormComponent {
  store: Local;
  new: boolean;
  public imagefile1:any = []
  public imagefile2:any = []

  constructor(private router: Router,public loginService: LoginService, private activatedRoute: ActivatedRoute, private storeService: StoreService) {
    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      storeService.getStore(id).subscribe(
        store => this.store = store,
        error => console.log(error)
      );
      this.new = false;
    } else {
      this.store = {activities: ' ', name: ' ', description: ' ', frontdescription: ' ', services: ' ', openDay: ' ', closeDay: ' ', openHour: ' ', closeHour: ' ', street: ' ', latitude: ' ', length: ' ', imageField1: null, imageField2: null};
    }
  }

  newStore() {
    this.storeService.addStore(this.store).subscribe(
      (store: Local) => {this.router.navigate(['/stores/']);
      this.UploadFile(store);
      //this,this.subirArchivo2(store)
    }),
      error => alert('Error al crear nuevo local: ' + error);

  }



  captureFile1(event): any {
    const archivoCapturado = event.target.files[0];
    this.imagefile1.push(archivoCapturado)
    console.log(this.imagefile1)

  }

  captureFile2(event): any {
    const archivoCapturado = event.target.files[0];
    this.imagefile2.push(archivoCapturado)
    console.log(this.imagefile2)

  }


  UploadFile(store:Local):any {
    try{
      const newformdata1 = new FormData();
      const newformdata2 = new FormData();
      this.imagefile1.forEach(imgfile1 =>{
        console.log(imgfile1);
        newformdata1.append('imageField1',imgfile1)
      })
      this.imagefile2.forEach(imgfile2 =>{
        console.log(imgfile2);
        newformdata2.append('imageField2',imgfile2)
      })
      this.storeService.setStoreImage1(store,newformdata1).subscribe(res => {
        console.log('Respuesta del servidor',res);
        this.storeService.setStoreImage2(store,newformdata2).subscribe(res => {
          console.log('Respuesta del servidor',res);
          })

        })
      } catch (e) {
        console.log('ERROR',e);
      }

  }



}
