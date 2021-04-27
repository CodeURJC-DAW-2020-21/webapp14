import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import {EventComponent} from './components/Events/events.component';
import {MainEventComponent} from './components/MainEvent/mainevent.component';

const routes: Routes = [{path:'',component:IndexComponent},
{path:'events',component:EventComponent}, {path:'mainevent',component:MainEventComponent},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
