import { Imprimivel } from './Imprimivel';
import { Igualavel } from './Igualavel';
import { Negociacao } from './Negociacao';

export class Negociacoes implements Imprimivel, Igualavel<Negociacoes>{
                    // ou Array<Negociacao>
    private _negociacoes: Negociacao[] = [];

    adiciona(negociacao: Negociacao){
        this._negociacoes.push(negociacao);
    }
 
               // indica o retorno do método (garante autocomplete e que o typescript infira tipos em outras classes)
    paraArray(): Negociacao[]{
        // programação defensiva -> retornar uma cópia do array para evitar mudanças no array original
        // return [].concat(this._negociacoes); -> ocasionou problemas de compilação após habilitado o recurso de strictNullChecks
        // return ([] as Negociacoes[]).concat(this._negociacoes); -> como solução para o problema que surgiu com strictNullChecks
        return this._negociacoes.slice(0);
    }

    paraTexto(): void{
        console.log("Impressão");
        console.log(JSON.stringify(this._negociacoes));
    }

    ehIgual(negociacoes: Negociacoes): boolean{
        return JSON.stringify(this._negociacoes) == JSON.stringify(negociacoes);
    }
}