package edu.ifpb.dac.main;

import edu.ifpb.dac.model.Dependente;
import edu.ifpb.dac.model.Empregado;
import edu.ifpb.dac.model.Endereco;
import edu.ifpb.dac.model.Faculdade;
import edu.ifpb.dac.model.Projeto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/09/2017, 22:17:19
 */
public class ExecutorPersist {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("consulta");
        EntityManager em = emf.createEntityManager();
        criarBaseDeDados(em);
    }

    private static void criarBaseDeDados(EntityManager em) {
        Dependente ana = new Dependente("Ana");
        Dependente maria = new Dependente("Maria");
        
        Endereco end = new Endereco("Rua Antonio Vilela", "Centro");
        
        Faculdade ifpb = new Faculdade("IFPB");
        
        Projeto dac = new Projeto("DAC 2017.1");
        
        Empregado job = new Empregado(end, "Job");
        job.addDep(ana);
        job.addDep(maria);
        
        end.setEmpregado(job);
        
        job.setFaculdade(ifpb);
        ifpb.addEmp(job);
        
        dac.addEmpregado(job);
        job.addProj(dac);
        
        em.getTransaction().begin();
        em.persist(job);
        em.getTransaction().commit();
        
    }
}
