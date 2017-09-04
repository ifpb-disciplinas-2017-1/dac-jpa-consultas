package edu.ifpb.dac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Faculdade implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String descricao;
    @OneToMany(mappedBy = "faculdade")
    private Collection<Empregado> empregados;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Faculdade(String descricao) {
        this();
        this.descricao = descricao;
    }

    public Faculdade(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Faculdade() {
        this.empregados = new ArrayList<>();
    }

    public Collection<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Collection<Empregado> empregados) {
        this.empregados = empregados;
    }

    public void addEmp(Empregado empregado) {
        empregados.add(empregado);
    }

    @Override
    public String toString() {
        return "Faculdade{" + "id=" + id + ", descricao=" + descricao + '}';
    }

}
