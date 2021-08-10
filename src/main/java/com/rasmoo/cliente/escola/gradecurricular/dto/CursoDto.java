package com.rasmoo.cliente.escola.gradecurricular.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
//@NoArgsConstructor
@Builder
public class CursoDto extends RepresentationModel<CursoDto> {

    @NotBlank(message = "Nome deve ser preenchido")
    @Size(max = 30,min = 10)
    private String nome;
    @NotBlank(message = "Código deve ser preenchido")
    @Size(max = 10,min = 4)
    private String codCurso;
    private List<Long> materias;
}
