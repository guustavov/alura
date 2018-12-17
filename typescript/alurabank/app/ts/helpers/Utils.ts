import { Imprimivel } from '../models/Imprimivel';

export function imprime(...objetos: Imprimivel[]): void{
    objetos.forEach(objeto => objeto.paraTexto());
}