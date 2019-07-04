package com.alizada.optimizer_service.optimizer;

/* workforce optimizer for partners' optimize classes */
public interface Optimizer<T,C> {
    T optimize(C request);
}
