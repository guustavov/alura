import { NegociacoesView, MensagemView } from '../views/index';
import { Negociacoes, Negociacao, NegociacaoParcial } from '../models/index';
import { domInject, throttle } from '../helpers/decorators/index';
import { NegociacaoService } from '../services/index';
import { imprime } from '../helpers/index';

export class NegociacaoController{
    
    @domInject('#data')
    private _inputData: JQuery;

    @domInject('#quantidade')
    private _inputQuantidade: JQuery;

    @domInject('#valor')
    private _inputValor: JQuery;

    private _negociacoes = new Negociacoes();
    private _negociacoesView = new NegociacoesView('#negociacoesView');
    private _mensagemView = new MensagemView('#mensagemView');

    private _service = new NegociacaoService;

    constructor(){

        // seletores alterados de "document.querySelector()" para "$()" após adição do type do Jquery
        // atribuições removidas do construtor para evitar acessos desnecessários ao DOM (usuário não interagir com tais elementos, por exemplo)
        // essas atribuições passarão a ser feitas por meio de um decorator de propriedade (@domInject)
        /* this._inputData = $('#data');
        this._inputQuantidade = $('#quantidade');
        this._inputValor = $('#valor'); */
        this._negociacoesView.update(this._negociacoes);
    }

    @throttle()
    adiciona(){
        let data = new Date(this._inputData.val().replace(/-/g, '/'));

        if(!this._ehDiaUtil(data)){
            this._mensagemView.update("Negociações só podem ser adicionadas em dias úteis!");
            return
        }

        
        const negociacao = new Negociacao(
            data,
            parseInt(this._inputQuantidade.val()),
            parseFloat(this._inputValor.val())
            );
            
            this._negociacoes.adiciona(negociacao);

            imprime(negociacao, this._negociacoes);
            
            this._negociacoesView.update(this._negociacoes);
            this._mensagemView.update("Negociação adicionada com sucesso!");
    }

    private _ehDiaUtil(data: Date){
        return data.getDay() != DiaDaSemana.Sabado 
            && data.getDay() != DiaDaSemana.Domingo;
    }

    @throttle()
    importaDados(){
        this._service
            .obterNegociacoes(res => {
                if (res.ok) return res;
                throw new Error(res.statusText);
            })
            .then(negociacoesParaImportar => {
                const negociacoesExistentes = this._negociacoes.paraArray();

                negociacoesParaImportar
                    .filter(negociacaoParaImportar => 
                        !negociacoesExistentes.some(existente => 
                            existente.ehIgual(negociacaoParaImportar)))
                    .forEach(negociacaoParaImportar => {
                    this._negociacoes.adiciona(negociacaoParaImportar);
                    this._negociacoesView.update(this._negociacoes);
                });
            })
            .catch(err => this._mensagemView.update(err.message));

    }
}

enum DiaDaSemana{
    Domingo,
    Segunda,
    Terca,
    Quarta,
    Quinta,
    Sexta,
    Sabado
}