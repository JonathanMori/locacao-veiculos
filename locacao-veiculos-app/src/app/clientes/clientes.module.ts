import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxMaskModule, IConfig } from 'ngx-mask';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesFormComponent } from './clientes-form/clientes-form.component';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';

export let options: Partial<IConfig> | (() => Partial<IConfig>);

@NgModule({
  declarations: [
    ClientesFormComponent,
    ClientesListaComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    FormsModule,
    NgxMaskModule.forRoot()
  ],
  exports: [
    ClientesFormComponent,
    ClientesListaComponent
  ]
})
export class ClientesModule { }
