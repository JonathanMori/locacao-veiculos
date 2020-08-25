import { Cliente } from '../clientes/cliente'
import { Veiculo } from '../veiculos/veiculo'

export class AluguelBusca {
    idAluguel: number;
    cliente: Cliente;
    veiculo: Veiculo;
    dataLocacao: string;
    dataDevolucao: string;
    valorAluguel: string;
}