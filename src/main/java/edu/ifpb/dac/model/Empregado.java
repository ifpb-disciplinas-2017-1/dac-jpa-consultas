package edu.ifpb.dac.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Empregado implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Dependente.class,
            cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Chave_Emp")
    private Collection<Dependente> dependentes;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER,
            mappedBy = "empregado")
    private Endereco endereco;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "chave_faculdade")
    private Faculdade faculdade;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "emp_proj")
    private Collection<Projeto> projetos;

    public Empregado() {
        this(null);
    }

    public Empregado(Endereco endereco) {
        this(endereco, "");
    }

    public Empregado(Endereco endereco, String nome) {
        this.endereco = endereco;
        this.nome = nome;
        this.dependentes = new LinkedList<>();
        this.projetos = new LinkedList<>();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(Collection<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public void addDep(Dependente dependente) {
        dependentes.add(dependente);
    }

    public void removeDep(Dependente dependente) {
        dependentes.remove(dependente);
    }

    public void addProj(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public void removeProj(Projeto projeto) {
        this.projetos.remove(projeto);
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
//        this.faculdade.addEmp(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Empregado{" + "id: " + id + ", nome: " + nome + '}';
    }

}
