package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repository.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public boolean atualizaMateria (Long id, MateriaDto materiaDto){
        try{
            this.consultaMateria(id);
            MateriaEntity materiaEntity = this.modelMapper.map(materiaDto, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return true;
        } catch (MateriaException m){
            throw m;
        } catch (Exception e){
            throw new MateriaException(ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    public List<MateriaDto> listaMaterias (){
        return materiaRepository.findAll()
                .stream()
                .map(materia -> this.modelMapper.map(materia, MateriaDto.class))
                .collect(Collectors.toList());
    }

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
}

