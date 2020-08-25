import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AluguelBusca } from '../aluguelBusca';
import { AlugueisService } from 'src/app/alugueis.service';
import { Aluguel } from '../aluguel';
import { NumberFormatStyle } from '@angular/common';

@Component({
  selector: 'app-alugueis-devolucao',
  templateUrl: './alugueis-devolucao.component.html',
  styleUrls: ['./alugueis-devolucao.component.css']
})
export class AlugueisDevolucaoComponent implements OnInit {

  aluguelBusca: AluguelBusca;
  aluguel: Aluguel;
  busca: boolean = false;
  success: boolean = false;
  mensagemSuccess: string;
  errors: String[];
  dataLocacao: Date;
  dataDevolucao: Date;
  resultado: number;

  constructor(
    private alugueisService: AlugueisService,
    private router: Router
  ) { 
    this.aluguelBusca = new AluguelBusca();
    this.aluguel = new Aluguel();
  }

  ngOnInit(): void {
  }

  voltarParaListagem() {
    this.router.navigate(['/alugueis-lista']);
  }

  pegarAluguel() {
    this.alugueisService.pegaAluguelPorId(this.aluguelBusca.idAluguel)
          .subscribe(
            response => {
              this.aluguelBusca = response;
              if (this.aluguelBusca.dataDevolucao != null) {
                this.aluguelBusca = new AluguelBusca();
                this.errors = ['Aluguel de código especificado já foi feito a devolução.'];
                this.success = false;
                this.busca = false;
              } else {
                this.errors = [];
                this.busca = true;
              }
            },
            errorResponse => {
              this.errors = ['Aluguel de código especificado não encontrado.'];
              this.success = false;
              this.busca = false;
            });
  }
 
  calcularAluguel() {
    var dia = this.aluguelBusca.dataLocacao.toString().split("/")[0];
    var mes = this.aluguelBusca.dataLocacao.toString().split("/")[1];
    var ano = this.aluguelBusca.dataLocacao.toString().split("/")[2];
    this.dataLocacao = new Date(+ano, +mes-1, +dia);

    var dia = this.aluguel.dataDevolucao.toString().split("/")[0];
    var mes = this.aluguel.dataDevolucao.toString().split("/")[1];
    var ano = this.aluguel.dataDevolucao.toString().split("/")[2];
    this.dataDevolucao = new Date(+ano, +mes-1, +dia);

    var dias = Math.round((this.dataDevolucao.valueOf() - this.dataLocacao.valueOf()) / (1000*60*60*24));

    this.resultado = +(+this.aluguelBusca.veiculo.valorDiaria * dias).toFixed(2);

    if (this.resultado >= 0) {
      this.errors = null;
    } else {
      this.resultado = null;
      this.errors = ['A Data de Devolução deve ser maior que a Data de Locação.'];
    }
  }

  onSubmit() {
    this.aluguel.idAluguel = this.aluguelBusca.idAluguel;
    this.aluguel.idCliente = this.aluguelBusca.cliente.idCliente;
    this.aluguel.idVeiculo = this.aluguelBusca.veiculo.idVeiculo;
    this.aluguel.dataLocacao = this.aluguelBusca.dataLocacao;
    this.aluguel.valorAluguel = this.resultado;

    this.alugueisService.devolver(this.aluguel)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
          this.mensagemSuccess = 'Devolução de aluguel efetuada com sucesso!';
          this.aluguelBusca = new AluguelBusca();
          this.aluguel = new Aluguel();
          this.busca = false;
          this.resultado = null;
        }, errorResponse => {
          this.success = false;
          this.errors = ['Erro ao realizar a devolução.'];
        })
  }
}
