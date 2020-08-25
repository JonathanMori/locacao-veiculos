import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlugueisFormComponent } from './alugueis-form/alugueis-form.component';
import { AlugueisListaComponent } from './alugueis-lista/alugueis-lista.component';
import { AlugueisDevolucaoComponent } from './alugueis-devolucao/alugueis-devolucao.component'


const routes: Routes = [
  { path: 'alugueis-form', component: AlugueisFormComponent},
  { path: 'alugueis-form/:idAluguel', component: AlugueisFormComponent},
  { path: 'alugueis-lista', component: AlugueisListaComponent},
  { path: 'alugueis-devolucao', component: AlugueisDevolucaoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlugueisRoutingModule { }
