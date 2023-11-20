package com.autobots.automanager.servicos;

import java.util.List;

public abstract class AdicionadorLinkLista<T> implements AdicionadorLink<T> {

    @Override
    public void adicionarLink(List<T> lista) {
        lista.forEach(this::adicionarLink);
    }

    protected abstract void adicionarLinkObjeto(T objeto);
}
