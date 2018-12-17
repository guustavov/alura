export function logarTempoDeExecucao(unidadeEmSegundos: boolean = false){
    return function(target: any, propertyKey: string, descriptor: PropertyDescriptor){
        const metodoOriginal = descriptor.value;
        
        descriptor.value = function(...args: any[]){
            
            let unidade = "ms";
            let divisor = 1;

            if(unidadeEmSegundos){
                unidade = "s";
                divisor = 1000;
            }
            console.log('-------------------------------------');
            console.log(`O método ${propertyKey} possui os seguintes parâmetros: ${JSON.stringify(args)}`);
            let t1 = performance.now();
            const retorno = metodoOriginal.apply(this, args);
            let t2 = performance.now();
            console.log(`Este método demorou ${(t2-t1)/divisor}${unidade}`);
            return retorno;
        }

        return descriptor;
    }
}