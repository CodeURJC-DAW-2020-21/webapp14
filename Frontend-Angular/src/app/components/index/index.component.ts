import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../../../assets/js/canvasjs.min.js';

@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit  {
  title = 'Frontend-Angular';
  ngOnInit() {
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
	for ( var i = 0; i < 10; i++ ) {		  
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
