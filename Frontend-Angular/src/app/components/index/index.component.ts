import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../../../assets/js/canvasjs.min.js';
import { Subscription } from 'rxjs';
import { UserService } from './../../service/user.service';


@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit  {
  userscount: number;
  title = 'Frontend-Angular';
  constructor(public userService: UserService) {

   }
   async ejecucionServicio() {
    let respuesta;
    await this.userService.getUsers().toPromise().then((response) => {
      respuesta = response;
    }).catch(e => console.error(e));
    return respuesta;
  }
  ngOnInit() {
    let users = this.ejecucionServicio();
    let number = users.then.length;
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

  let dataPoints = [];
	let y = 0;
	for ( var i = 0; i < users.then.length; i++ ) {
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
    }




  ngAfterViewInit(): void {
    (<any>window).twttr.widgets.load();

}

}
