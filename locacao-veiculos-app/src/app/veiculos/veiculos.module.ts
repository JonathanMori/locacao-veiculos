import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { NgxMaskModule, IConfig } from 'ngx-mask';
import { NgxCurrencyModule } from "ngx-currency";

import { VeiculosRoutingModule } from './veiculos-routing.module';
import { VeiculosFormComponent } from './veiculos-form/veiculos-form.component';
import { VeiculosListaComponent } from './veiculos-lista/veiculos-lista.component';

export let options: Partial<IConfig> | (() => Partial<IConfig>);

@NgModule({
  declarations: [
    VeiculosFormComponent,
    VeiculosListaComponent
  ],
  imports: [
    CommonModule,
    VeiculosRoutingModule,
    FormsModule,
    NgxMaskModule.forRoot(),
    NgxCurrencyModule
  ],
  exports: [
    VeiculosFormComponent,
    VeiculosListaComponent
  ]
})
export class VeiculosModule { }
