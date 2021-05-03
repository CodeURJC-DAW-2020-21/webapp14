import { Component } from '@angular/core';
import { EventService} from '../../service/event.service';
import { Event } from '../../model/event.model';
import { ActivatedRoute, Router} from '@angular/router';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'formevent',
  templateUrl: './formevent.component.html',
  styleUrls: ['./formevent.component.css']
})
export class FormeventComponent {
  event: Event;
  new: boolean;
  public archivos:any = []

  constructor(private router: Router,public loginService: LoginService, private activatedRoute: ActivatedRoute, private eventService: EventService) {
    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      eventService.getEvent(id).subscribe(
        event => this.event = event,
        error => console.error(error)
      );
      this.new = false;
    } else {
      this.event = {activities: ' ', name: ' ', description: ' ', place: ' ', reward: ' ', people: ' ', price: ' ', tag1: ' ', imageFile: null, date: null };
    }
  }

  newEvent(){
    this.eventService.addEvent(this.event).subscribe(
      (event: Event) => {this.router.navigate(['/events/']);
      this.subirArchivo(event)}),
      error => alert('Error al crear el nuevo evento: ' + error);
  }


  capturarFile(event): any {
    const archivoCapturado = event.target.files[0];
    this.archivos.push(archivoCapturado)
    console.log(this.archivos)

  }


  subirArchivo(event:Event):any {
    try{
      const formularioDeDatos = new FormData();
      this.archivos.forEach(archivo =>{
        console.log(archivo);
        formularioDeDatos.append('imageFile',archivo)
      })
      this.eventService.setEventImage(event,formularioDeDatos).subscribe(res => {
        console.log('Respuesta del servidor',res);
        })
      } catch (e) {
        console.log('ERROR',e);
      }
  }


}
