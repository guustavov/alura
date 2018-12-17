import { Imprimivel } from './Imprimivel';
import { Igualavel } from './Igualavel';
export class Negociacao implements Imprimivel, Igualavel<Negociacao>{

    /* constructor(
        private _data: Date,
        private _quantidade: number,
        private _valor: number){
    }

    get data(){
        return this._data;
    }

    get quantidade(){
        return this._quantidade;
    }

    get valor(){
        return this._valor;
    }

    get volume(){
        return this._quantidade * this._valor;
    } */

    /* variação de implementação acima utilizando "readonly",
    para casos em que as propriedades não devem ser
    alteradas após instanciação do objeto (via construtor, por exemplo) */

    constructor (readonly data: Date, readonly quantidade: number, readonly valor: number){}

    get volume(){
        return this.quantidade * this.valor;
    }

    paraTexto(): void{
        console.log("Impressão");
        console.log(`   Data: ${this.data}\n    Quantidade: ${this.quantidade}\n    Valor: ${this.valor}`);
    }

    ehIgual(negociacao: Negociacao): boolean{
        return  this.data.getDate() == negociacao.data.getDate()
                && this.data.getMonth() == negociacao.data.getMonth()
                && this.data.getFullYear() == negociacao.data.getFullYear();
    }
}