import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import {EventComponent} from './components/Events/events.component';

const routes: Routes = [{path:'',component:IndexComponent},
{path:'events',component:EventComponent},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
