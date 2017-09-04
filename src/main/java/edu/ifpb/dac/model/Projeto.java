package edu.ifpb.dac.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Projeto implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String descricao;

    @ManyToMany(mappedBy = "projetos")
    private Collection<Empregado> empregados;

    public Projeto(String descricao) {
        this();
        this.descricao = descricao;
    }

    public Projeto() {
        this.empregados = new LinkedList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Collection<Empregado> empregados) {
        this.empregados = empregados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addEmpregado(Empregado empregado) {
        this.empregados.add(empregado);
    }

    public void removeEmpregado(Empregado empregado) {
        this.empregados.remove(empregado);
    }
}
