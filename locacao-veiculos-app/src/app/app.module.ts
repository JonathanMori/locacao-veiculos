import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NgxMaskModule, IConfig } from 'ngx-mask'
import { NgxCurrencyModule } from "ngx-currency";

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component'
import { ClientesModule } from './clientes/clientes.module';
import { ClientesService } from './clientes.service'
import { VeiculosModule } from './veiculos/veiculos.module';
import { VeiculosService } from './veiculos.service'
import { AlugueisModule } from './alugueis/alugueis.module';
import { AlugueisService } from './alugueis.service'

export const options: Partial<IConfig> | (() => Partial<IConfig>) = {};

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgxMaskModule.forRoot(),
    NgxCurrencyModule,
    TemplateModule,
    ClientesModule,
    VeiculosModule,
    AlugueisModule
  ],
  providers: [
    ClientesService,
    VeiculosService,
    AlugueisService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
