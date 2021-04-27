import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import {EventsComponent} from './components/Events/events.component';
import {UsersComponent} from './components/User/user.component';

const routes: Routes = [{path:'',component:IndexComponent},
{path:'events',component:EventsComponent},
{path:'user',component:UsersComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
