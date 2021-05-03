import { StoreService } from './../../service/store.service';
import { BehaviorSubject} from 'rxjs';
import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../../../assets/js/canvasjs.min.js';
import { Subscription } from 'rxjs';
import { GraphicService } from './../../service/graphic.service';
import {Users} from '../../model/user.model';


@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit  {
  userscount: number;
  eventscount: number;
  commentscount: number;
  storescount: number;
  title = 'Frontend-Angular';
  constructor(public graphicService: GraphicService, public storeService:StoreService) {

  }


  ngOnInit() {

    this.graphicService.getUsersNumber().subscribe(
      usernumber =>{
        this.userscount = usernumber;
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
    this.graphicService.getCommentsNumber().subscribe(
      commentnumber =>{
        this.commentscount = commentnumber;
        this.graphicService.getEventsNumber().subscribe(
          eventnumber =>{
            this.eventscount = eventnumber;
            this.graphicService.getStoresNumber().subscribe(
              storenumber =>{
                this.storescount = storenumber;
                let chart = new CanvasJS.Chart("chartContainer", {
                  animationEnabled: true,
                  title: {
                    text: "Interacciones"
                  },
                  data: [{
                    type: "column",
                    dataPoints: [
                      { y: this.eventscount, label: "Eventos" },
                      { y: this.storescount, label: "Locales" },
                      { y: this.commentscount, label: "Comentarios" }
                    ]
                  }]
                });
                chart.render();
              },
              error => console.log("error")
            )
          },
          error => console.log("error")
        )
      },
      error => console.log("error")
    )



    }




  ngAfterViewInit(): void {
    (<any>window).twttr.widgets.load();

}

}
