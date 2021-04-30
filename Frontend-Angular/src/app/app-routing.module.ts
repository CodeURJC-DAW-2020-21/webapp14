import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';

import {StoreComponent} from './components/Store/store.component';
import {LoginComponent} from './components/Login/login.component';
import {EventsComponent} from './components/Events/events.component';
import { GovernComponent } from './components/Government/government.component';
import { FormeventComponent} from './components/FormEvents/formevent.component';
import {MainEventComponent} from './components/MainEvent/mainevent.component';
import {MainStoreComponent} from './components/MainStore/mainstore.component';
import {StoreFormComponent} from './components/StoreForm/storeform.component';
import {UsersComponent} from './components/User/user.component';


const routes: Routes = [{path:'',component:IndexComponent},
{path: 'events',component:EventsComponent},
{path: 'govern',component:GovernComponent},
{path: 'formevent',component:FormeventComponent},
{path: 'mainevent/:id', component: MainEventComponent},
{path: 'storeform', component: StoreFormComponent},
{path: 'stores',component:StoreComponent},
{path: 'login',component:LoginComponent},
{path: 'user',component:UsersComponent},
{path: 'mainstore/:id',component:MainStoreComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
