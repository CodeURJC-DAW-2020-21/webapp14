import { StoreService } from './../../service/store.service';
import { BehaviorSubject} from 'rxjs';
import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../../../assets/js/canvasjs.min.js';
import { Subscription } from 'rxjs';
import { UserService } from './../../service/user.service';
import {Users} from '../../model/user.model';


@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit  {
  x: BehaviorSubject<Users>;
  userscount: number;
  id: number = 2;
  user:Users;
  users:Users[]=[];
  title = 'Frontend-Angular';
  constructor(public userService: UserService, public storeService:StoreService) {

   }
   async ejecucionServicioUsers() {
    let respuesta;
    await this.userService.getUsers().toPromise().then((response) => {
      respuesta = response;
    }).catch(e => console.error(e));
    return respuesta;
  }
  async

  ngOnInit() {
    this.userService.getUsersAux().subscribe(
      userarray =>{
        userarray.map(user =>this.users.push(user))
        console.log(this.users);
        console.log(this.users.length);
        console.log(this.users[0]);
        this.userscount=this.users.length;
        console.log(this.userscount);
        let users = this.ejecucionServicioUsers();
        let dataPoints = [];
        let y = 0;
        for ( var i = 0; i < this.userscount; i++ ) {
          y += 1;
          dataPoints.push({ y: y});
        }
        let chart2 = new CanvasJS.Chart("chartContainer2", {
          zoomEnabled: true,
          animationEnabled: true,
          title: {
            text: "Usuarios registrados"
          },
          data: [
          {
            type: "line",
            dataPoints: dataPoints
          }]
        });

        chart2.render();

      },
      error => console.log("error")
    )
    console.log(this.users)

		let chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
    title: {
			text: "Interacciones"
		},
		data: [{
			type: "column",
			dataPoints: [
				{ y: 2, label: "Eventos" },
				{ y: 2, label: "Locales" },
				{ y: 10, label: "Tiendas" }
			]
		}]
	});
	chart.render();
  console.log(this.userscount);
  console.log(this.users[0])
  
    }




  ngAfterViewInit(): void {
    (<any>window).twttr.widgets.load();

}

}
