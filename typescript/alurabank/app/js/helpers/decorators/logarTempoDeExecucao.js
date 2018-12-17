System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    function logarTempoDeExecucao(unidadeEmSegundos = false) {
        return function (target, propertyKey, descriptor) {
            const metodoOriginal = descriptor.value;
            descriptor.value = function (...args) {
                let unidade = "ms";
                let divisor = 1;
                if (unidadeEmSegundos) {
                    unidade = "s";
                    divisor = 1000;
                }
                console.log('-------------------------------------');
                console.log(`O método ${propertyKey} possui os seguintes parâmetros: ${JSON.stringify(args)}`);
                let t1 = performance.now();
                const retorno = metodoOriginal.apply(this, args);
                let t2 = performance.now();
                console.log(`Este método demorou ${(t2 - t1) / divisor}${unidade}`);
                return retorno;
            };
            return descriptor;
        };
    }
    exports_1("logarTempoDeExecucao", logarTempoDeExecucao);
    return {
        setters: [],
        execute: function () {
        }
    };
});
