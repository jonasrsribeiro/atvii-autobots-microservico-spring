package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.servicos.AdicionadorLink;
import com.autobots.automanager.servicos.AdicionadorLinkFactory;

@Component
public class AdicionadorLinkTelefone extends AdicionadorLinkFactory<Telefone> implements AdicionadorLink<Telefone> {

	@Override
    protected AdicionadorLink<Telefone> criarAdicionadorLink(Class<?> classe) {
        if (classe.equals(Telefone.class)) {
            return new AdicionadorLinkTelefone();
        }

		return null;

	}

	@Override
    public void adicionarLink(Telefone objeto) {
        adicionarLinkTelefone(objeto);
    }

	@Override
    public void adicionarLink(List<Telefone> lista) {
        for (Telefone telefone : lista) {
            adicionarLinkTelefone(telefone);
        }
    }

	private void adicionarLinkTelefone(Telefone telefone) {
        long id = telefone.getId();

        Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).obterTelefone(id))
                .withSelfRel().withType("GET").withRel("self").withTitle("Obter detalhes do telefone");

        Link linkListaTelefones = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).obterTelefones())
                .withRel("todosTelefones").withType("GET").withTitle("Obter lista de clientes");

        Link linkNovoTelefone = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).cadastrarTelefone(new Telefone()))
                .withRel("novoTelefone").withType("POST").withTitle("Criar um novo telefone");

        Link linkAtualizar = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).atualizarTelefone(telefone))
                .withRel("atualizar").withType("PUT").withTitle("Atualizar detalhes do telefone");

        Link linkExcluir = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).excluirTelefone(telefone)).withRel("excluir")
                .withType("DELETE").withTitle("Excluir telefone");

        telefone.add(linkProprio, linkListaTelefones, linkNovoTelefone, linkAtualizar, linkExcluir);
    }

	public void adicionarLinkListaTelefones(List<Telefone> clientes) {
        Link linkListaTelefones = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).obterTelefones()).withRel("telefones")
                .withType("GET").withTitle("Obter lista de telefones");

        Link linkNovoTelefone = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).cadastrarTelefone(new Telefone()))
                .withRel("novoTelefone").withType("POST").withTitle("Criar um novo telefone");

        clientes.forEach(telefone -> telefone.add(linkListaTelefones, linkNovoTelefone));
    }
}
