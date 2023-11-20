package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.AdicionadorLinkDocumento;
import com.autobots.automanager.modelos.DocumentoAtualizador;
import com.autobots.automanager.modelos.DocumentoSelecionador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documentos")
public class DocumentoControle {
    @Autowired
    private DocumentoRepositorio repositorio;
    @Autowired
    private DocumentoSelecionador selecionador;
    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumento(@PathVariable long id) {
        List<Documento> documentos = repositorio.findAll();
        Documento documento = selecionador.selecionar(documentos, id);
        if (documento == null) {
            ResponseEntity<Documento> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(documento);
            ResponseEntity<Documento> resposta = new ResponseEntity<>(documento, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Documento>> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        if (documentos.isEmpty()) {
            ResponseEntity<List<Documento>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(documentos);
            ResponseEntity<List<Documento>> resposta = new ResponseEntity<>(documentos, HttpStatus.FOUND);
            return resposta;
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarDocumento(@RequestBody Documento documento) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (documento.getId() == null) {
            repositorio.save(documento);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarDocumento(@RequestBody Documento atualizacao) {
        HttpStatus status = HttpStatus.CONFLICT;
        Documento documento = repositorio.getById(atualizacao.getId());
        if (documento != null) {
            DocumentoAtualizador atualizador = new DocumentoAtualizador();
            atualizador.atualizar(documento, atualizacao);
            repositorio.save(documento);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirDocumento(@RequestBody Documento exclusao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Documento documento = repositorio.getById(exclusao.getId());
        if (documento != null) {
            repositorio.delete(documento);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
