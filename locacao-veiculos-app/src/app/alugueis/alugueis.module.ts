import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { RouterModule } from '@angular/router';
import { NgxMaskModule, IConfig } from 'ngx-mask';

import { AlugueisRoutingModule } from './alugueis-routing.module';
import { AlugueisFormComponent } from './alugueis-form/alugueis-form.component';
import { AlugueisListaComponent } from './alugueis-lista/alugueis-lista.component';
import { AlugueisDevolucaoComponent } from './alugueis-devolucao/alugueis-devolucao.component';

export let options: Partial<IConfig> | (() => Partial<IConfig>);

@NgModule({
  declarations: [
     AlugueisFormComponent,
     AlugueisListaComponent,
     AlugueisDevolucaoComponent
    ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    AlugueisRoutingModule,
    NgxMaskModule.forRoot()
  ],
  exports: [
    AlugueisFormComponent,
    AlugueisListaComponent,
    AlugueisDevolucaoComponent,
  ]
})
export class AlugueisModule { }
