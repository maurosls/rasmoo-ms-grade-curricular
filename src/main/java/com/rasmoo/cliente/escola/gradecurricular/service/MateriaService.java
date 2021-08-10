package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.controller.MateriaController;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repository.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService {

    private final static String ERROR_INTERNAL = "Erro interno, contate o suporte";
    private final static String ERROR_MATERIA_NAO_ENCONTRADA = "Materia não encontrada";


    private final MateriaRepository materiaRepository;

    private final ModelMapper modelMapper;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
        this.modelMapper = new ModelMapper();
    }

    //    @Caching(evict =
    //            @CacheEvict{value = "materia", key="#materia.id"}
    //            )
    //    @CacheEvict(value = "materia", key="#materia.id")
    @CacheEvict(key="#materia.id")
    public boolean atualizaMateria (MateriaDto materia){
        try{
            this.consultaMateria(materia.getId());
            MateriaEntity materiaEntity = this.modelMapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return true;
        } catch (MateriaException m){
            throw m;
        } catch (Exception e){
            throw new MateriaException(ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(allEntries = true)
    public boolean criaMateria (MateriaDto materiaDto){
        try{
            MateriaEntity materiaEntity = this.modelMapper.map(materiaDto, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return true;
        }
        catch (Exception e){
            throw new MateriaException(ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(allEntries = true)
    public boolean deletaMateria (Long id){
        try {
            this.consultaMateria(id);
            this.materiaRepository.deleteById(id);
        } catch (MateriaException m){
            throw m;
        } catch (Exception e){
            throw new MateriaException(ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return true;
    }

    @Cacheable(unless = "#result.size()<3")
    public List<MateriaDto> listaMaterias (){
        List<MateriaDto> materiaDtos = materiaRepository.findAll()
                .stream()
                .map(materia -> this.modelMapper.map(materia, MateriaDto.class))
                .collect(Collectors.toList());
        materiaDtos.forEach(materia -> {
            materia.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateria(materia.getId()))
                                 .withSelfRel());
        });
        return materiaDtos;
    }

    @Cacheable(key="#id")
    public MateriaDto consultaMateria (Long id){
        try{
            Optional<MateriaEntity> materiaEntity = materiaRepository.findById(id);
            if (materiaEntity.isPresent()){
                MateriaDto materiaDto = this.modelMapper.map(materiaEntity.get(), MateriaDto.class);
                return materiaDto;
            }
            throw new MateriaException(ERROR_MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
        } catch (MateriaException m){
            throw m;
        } catch (Exception e){
            throw new MateriaException(ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<MateriaDto> consultaPorHorarioMinimo(int horaMinima) {
        List<MateriaDto> materiaDtos = materiaRepository.findByHoraMinima(horaMinima)
                .stream()
                .map(materia -> this.modelMapper.map(materia, MateriaDto.class))
                .collect(Collectors.toList());
        materiaDtos.forEach(materia -> {
            materia.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateria(materia.getId()))
                                .withSelfRel());
        });
        return materiaDtos;
    }

}

