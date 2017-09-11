package edu.ifpb.dac.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/09/2017, 07:31:26
 */
public class EmpregadoFaculdade implements Serializable {

    private String nome;
    private Faculdade faculdade;

    public EmpregadoFaculdade() {
    }

    public EmpregadoFaculdade(String nome, Faculdade faculdade) {
        this.nome = nome;
        this.faculdade = faculdade;
    }

    public String getNome() {
        return nome;
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.faculdade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpregadoFaculdade other = (EmpregadoFaculdade) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.faculdade, other.faculdade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmpregadoFaculdade{" + "nome=" + nome + ", faculdade=" + faculdade + '}';
    }

}
