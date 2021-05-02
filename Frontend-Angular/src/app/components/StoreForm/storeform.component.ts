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
  public archivos1:any = []
  public archivos2:any = []

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
      this.subirArchivo1(store);
      this,this.subirArchivo2(store)}),
      error => alert('Error al crear nuevo local: ' + error);

  }



  capturarFile1(event): any {
    const archivoCapturado = event.target.files[0];
    this.archivos1.push(archivoCapturado)
    console.log(this.archivos1)

  }

  capturarFile2(event): any {
    const archivoCapturado = event.target.files[0];
    this.archivos2.push(archivoCapturado)
    console.log(this.archivos2)

  }


  subirArchivo1(store:Local):any {
    try{
      const formularioDeDatos = new FormData();
      this.archivos1.forEach(archivo =>{
        console.log(archivo);
        formularioDeDatos.append('imageField1',archivo)
      })
      this.storeService.setStoreImage1(store,formularioDeDatos).subscribe(res => {
        console.log('Respuesta del servidor',res);
        })
      } catch (e) {
        console.log('ERROR',e);
      }
  }

  subirArchivo2(store:Local):any {
    try{
      const formularioDeDatos = new FormData();
      this.archivos2.forEach(archivo =>{
        console.log(archivo);
        formularioDeDatos.append('imageField2',archivo)
      })
      this.storeService.setStoreImage2(store,formularioDeDatos).subscribe(res => {
        console.log('Respuesta del servidor',res);
        })
      } catch (e) {
        console.log('ERROR',e);
      }
  }

}
