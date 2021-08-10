package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.controller.MateriaController;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.CursoModel;
import com.rasmoo.cliente.escola.gradecurricular.repository.CursoRepository;
import com.rasmoo.cliente.escola.gradecurricular.repository.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final MateriaRepository materiaRepository;
    private final ModelMapper modelMapper;

    public CursoService(CursoRepository cursoRepository,
                        MateriaRepository materiaRepository) {
        this.cursoRepository = cursoRepository;
        this.materiaRepository = materiaRepository;
        this.modelMapper = new ModelMapper();
    }


    public Boolean cadastraCurso(CursoModel curso) {

        List<MateriaEntity> listMateriaEntity = new ArrayList<>();
        if(!curso.getMaterias().isEmpty()) {
            curso.getMaterias().forEach(materia->{
                if(this.materiaRepository.findById(materia).isPresent())
                    listMateriaEntity.add(this.materiaRepository.findById(materia).get());
            });
        }
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setCodigo(curso.getCodCurso());
        cursoEntity.setNome(curso.getNome());
        cursoEntity.setMaterias(listMateriaEntity);
        this.cursoRepository.save(cursoEntity);
        return true;
    }

    public List<CursoDto> listarCursos() {

        List<CursoDto> cursoDtos = cursoRepository.findAll()
                .stream()
                .map(curso ->
                         CursoDto.builder()
                                 .codCurso(curso.getCodigo())
                                 .nome(curso.getNome())
                                 .materias(curso.getMaterias().stream().map(
                                         materiaEntity -> materiaEntity.getId()).collect(Collectors.toList()))
                                 .build()
                     )
                .collect(Collectors.toList());
//        cursoDtos.forEach(curso -> {
//            curso.add(WebMvcLinkBuilder.linkTo(
//                    WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateria(curso.getId()))
//                                .withSelfRel());
//        });
        return cursoDtos;

    }

}
