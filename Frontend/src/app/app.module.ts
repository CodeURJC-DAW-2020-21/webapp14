import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { IndexComponent } from './components/index/index.component';
import { HeaderComponent } from './components/Header/header.component';
import { FooterComponent } from './components/Footer/footer.component';

import { StoreComponent } from './components/Store/store.component';
import { LoginComponent } from './components/Login/login.component';


import { GovernComponent } from './components/Government/government.component';
import { FormeventComponent} from './components/FormEvents/formevent.component';
import { CommentComponent } from './components/Comments/comment.component';
import { MainEventComponent } from './components/MainEvent/mainevent.component';

import { MainStoreComponent } from './components/Mainstore/mainstore.component';

import { StoreFormComponent } from './components/StoreForm/storeform.component';
import { EventsComponent } from './components/Events/events.component';

import { UsersComponent } from './components/User/user.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import { MapComponent } from '@core/components/map/map.component';


@NgModule({
  declarations: [
    MapComponent,
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    EventsComponent,
    UsersComponent,
    StoreComponent,
    LoginComponent,
    GovernComponent,
    FormeventComponent,
    MainEventComponent,
    MainStoreComponent,
    StoreFormComponent,
    CommentComponent,
    EventsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
