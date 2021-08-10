package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.service.MateriaService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/materia")
public class MateriaController {

	private final static String DELETE = "DELETE";
	private final static String UPDATE = "UPDATE";

	private final MateriaService materiaService;

	public MateriaController(MateriaService materiaService) {this.materiaService = materiaService;}

	@GetMapping("/")
	public ResponseEntity<Response<List<MateriaDto>>> listaMaterias() {
		Response<List<MateriaDto>> response = new Response<>();
		response.setData(materiaService.listaMaterias());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(MateriaController.class).listaMaterias())
							 .withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<MateriaDto>> consultaMateria(@PathVariable Long id) {
		Response<MateriaDto> response = new Response<>();
		response.setData(materiaService.consultaMateria(id));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateria(id))
							 .withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(MateriaController.class).deletaMateria(id))
							 .withRel(DELETE));
		return ResponseEntity.status(HttpStatus.OK).body(response);
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

	@GetMapping("/hora-minima/{horaMinima}")
	public ResponseEntity<Response<List<MateriaDto>>> consultaPorHorarioMinimo(@PathVariable int horaMinima) {
		Response<List<MateriaDto>> response = new Response<>();
		response.setData(materiaService.consultaPorHorarioMinimo(horaMinima));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(MateriaController.class).listaMaterias())
							 .withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
