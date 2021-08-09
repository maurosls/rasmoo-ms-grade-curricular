package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/materia")
public class MateriaController {

	private final MateriaService materiaService;

	public MateriaController(MateriaService materiaService) {this.materiaService = materiaService;}

	@GetMapping("/")
	public ResponseEntity<List<MateriaDto>> listaMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.listaMaterias());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MateriaDto> consultaMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.consultaMateria(id));
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> cadastraMateria(@RequestBody @Valid MateriaDto materiaDto) {
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.criaMateria(materiaDto));
	}

	@PutMapping("/")
	public ResponseEntity<Boolean> atualizaMateria(@RequestBody @Valid MateriaDto materiaDto) {
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.atualizaMateria(materiaDto));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletaMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.deletaMateria(id));
	}
}
