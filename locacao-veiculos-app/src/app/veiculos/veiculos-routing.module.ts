import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VeiculosFormComponent } from './veiculos-form/veiculos-form.component'
import { VeiculosListaComponent } from './veiculos-lista/veiculos-lista.component';

const routes: Routes = [
  { path: 'veiculos-form', component: VeiculosFormComponent},
  { path: 'veiculos-form/:idVeiculo', component: VeiculosFormComponent},
  { path: 'veiculos-lista', component: VeiculosListaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VeiculosRoutingModule { }
