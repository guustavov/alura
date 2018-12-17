import { logarTempoDeExecucao } from '../helpers/decorators/index';

export abstract class View<T>{
    protected _elemento: JQuery;
    private _escapar: boolean;

                                // escapar?: boolean também pode ser utilizado para indicar parâmetro opcional, recebendo como valor default "undefined"
    constructor(seletor: string, escapar: boolean = true){
        this._elemento = $(seletor);
        this._escapar = escapar;
    }

    @logarTempoDeExecucao()
    update(model: T): void{
        let template = this.template(model);
        if(this._escapar){
            template = template.replace(/<script>[\s\S]*?<\/script>/g, '');
        }
        this._elemento.html(template);
    }

    abstract template(mode: T): string;
}