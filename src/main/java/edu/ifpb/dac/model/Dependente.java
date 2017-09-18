package edu.ifpb.dac.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Ricardo Job
 */
@NamedQueries({
    @NamedQuery(name = "Dependente.todos", query = "SELECT d FROM Dependente d"),
    @NamedQuery(name = "Dependente.nome", query = "SELECT d FROM Dependente d WHERE d.nome LIKE :nome"),
    @NamedQuery(name = "Dependente.id", query = "SELECT d FROM Dependente d WHERE d.id=:id")
   })
@Entity
public class Dependente implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;

    public Dependente() {
    }

    public Dependente(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Dependente{" + "id=" + id + ", nome=" + nome + '}';
    }

}
