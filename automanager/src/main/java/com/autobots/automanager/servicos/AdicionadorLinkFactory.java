package com.autobots.automanager.servicos;

public abstract class AdicionadorLinkFactory<T> {
    protected abstract AdicionadorLink<T> criarAdicionadorLink(Class<?> classe);
}
