import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AluguelBusca } from '../aluguelBusca';
import { AlugueisService } from 'src/app/alugueis.service';
import { Cliente } from 'src/app/clientes/cliente';
import { ClientesService } from '../../clientes.service'

@Component({
  selector: 'app-alugueis-lista',
  templateUrl: './alugueis-lista.component.html',
  styleUrls: ['./alugueis-lista.component.css']
})
export class AlugueisListaComponent implements OnInit {

  filtro: string;
  alugueis: AluguelBusca[];
  filtroAlugados: boolean = false;
  clientes: Cliente[] = [];
  idCliente: string;

  constructor(
    private alugueisService: AlugueisService,
    private clientesService: ClientesService,
    private router: Router) { 
      this.filtro = "";
      this.idCliente = "";
    }

  ngOnInit(): void {
    this.alugueisService.buscar("", "")
    .subscribe(response => {
      this.alugueis = response;
      this.trocaPontoVirgula();
    });

    //Pega clientes para listagem
    this.clientesService.getClientes()
      .subscribe(response => this.clientes = response);
  }

  novoCadastro() {
    this.router.navigate(['/alugueis-form']);
  }

  devolucaoAluguel() {
    this.router.navigate(['/alugueis-devolucao']);
  }

  filtrar() {
    if (this.filtro == 'alugados') {
      this.filtroAlugados = true;
    } else {
      this.filtroAlugados = false;
    }

    this.alugueisService.buscar(this.filtro, this.idCliente)
      .subscribe(response => {
        this.alugueis = response;
        this.trocaPontoVirgula();
      });
  }

  trocaPontoVirgula() {
    if (this.filtro != 'alugados') {
      this.alugueis.forEach(function (a) {
        if (a.valorAluguel != null) {
          a.valorAluguel = 'R$' + a.valorAluguel.toString().replace('.', ',');
        }
      })
    }
  }

  limparFiltros() {
    this.filtro = "";
    this.idCliente = "";

    this.alugueisService.buscar("", "")
    .subscribe(response => {
      this.alugueis = response;
      this.trocaPontoVirgula();
    });
  }
}
