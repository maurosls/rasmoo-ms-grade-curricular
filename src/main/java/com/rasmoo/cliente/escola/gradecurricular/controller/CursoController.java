package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.CursoModel;
import com.rasmoo.cliente.escola.gradecurricular.service.CursoService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {this.cursoService = cursoService;}

    /*
     * Cadastro curso, passando a os códigos das matérias a serem cadastradas
     * */
    @PostMapping("/")
    public ResponseEntity<Boolean> cadastrarCurso(@Valid @RequestBody CursoModel curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.cadastraCurso(curso));
    }
    /*
     * Listar cursos
     * */
    @GetMapping("/")
    public ResponseEntity<List<CursoDto>> listarCurso() {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.listarCursos());
    }
    /*
     * Consultar curso por código do curso
     * */
    @GetMapping("/{codCurso}")
    public ResponseEntity<Void> consultarCursoPorMateria() {
        throw new NotYetImplementedException();
    }
    /*
     * Atualizar curso
     * */
    @PutMapping
    public ResponseEntity<Void> atualizarCurso() {
        throw new NotYetImplementedException();
    }
}

