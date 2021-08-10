package com.rasmoo.cliente.escola.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
public class CursoEntity implements Serializable {

    private static final long serialVersionUID = -922489302181456530L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "name")
    private String nome;

    @Column(name = "cod")
    private String codigo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private List<MateriaEntity> materias;

}
