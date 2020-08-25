import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

import { Veiculo } from '../veiculo';
import { VeiculosService } from 'src/app/veiculos.service';

@Component({
  selector: 'app-veiculos-lista',
  templateUrl: './veiculos-lista.component.html',
  styleUrls: ['./veiculos-lista.component.css']
})
export class VeiculosListaComponent implements OnInit {

  veiculos: Veiculo[] = [];
  veiculoSelecionado: Veiculo;
  mensagemSucesso: string;
  mensagemErro: string;

  constructor(
    private service: VeiculosService,
    private router: Router) { }

  ngOnInit(): void {
    this.service.getVeiculos()
      .subscribe(resposta => {
        this.veiculos = resposta;
        this.trocaPontoVirgula();
      });
  }

  novoCadastro() {
    this.router.navigate(['/veiculos-form']);
  }

  preparaDelecao(veiculo: Veiculo) {
    this.veiculoSelecionado = veiculo;
  }

  deletarVeiculo() {
    this.service.deletar(this.veiculoSelecionado)
      .subscribe(
        response => {
          this.mensagemSucesso = 'Veículo deletado com sucesso!'
          this.mensagemErro = null;
          this.ngOnInit();
        },
        erro => this.mensagemErro = 'Não é possível deletar veículo que esteja vinculado a um aluguel.')
  }

  trocaPontoVirgula() {
    this.veiculos.forEach(function (v) {
      v.valorDiaria = v.valorDiaria.toString().replace('.', ',');
    })
  }
}
