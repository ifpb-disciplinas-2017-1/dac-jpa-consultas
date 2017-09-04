package edu.ifpb.dac.main;

import edu.ifpb.dac.model.Dependente;
import edu.ifpb.dac.model.Empregado;
import edu.ifpb.dac.model.Faculdade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/09/2017, 22:15:38
 */
public class ExecutorQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("consulta");
        EntityManager em = emf.createEntityManager();

//        consultarTodosOsEmpregados(em);
//        consultarFaculdadeComId(em);
//        consultarFaculdadeComIdParametros(em);
        consultarDependentesComPaginacao(em);
//        consultarNomeDoDependentes(em);
    }

    private static void consultarTodosOsEmpregados(EntityManager em) {
//        String sql = "SELECT * FROM Empregado";
        String sql = "SELECT e FROM Empregado e";
//        Query createQuery = em.createQuery(sql);
//        List resultList = createQuery.getResultList();
//        for (Object object : resultList) {
//            Empregado emp = (Empregado) object;
//            System.out.println(emp.getNome());
//        }

        TypedQuery<Empregado> query = em.createQuery(sql, Empregado.class);
        List<Empregado> lista = query.getResultList();

        lista.stream().forEach(System.out::println);
    }

    private static void consultarFaculdadeComId(EntityManager em) {
        //        String sql = "SELECT * FROM Faculdade f WHERE f.id=2";
        String sql = "SELECT f FROM Faculdade f WHERE f.id=2";
        TypedQuery<Faculdade> createQuery = em.createQuery(sql, Faculdade.class);
        createQuery.getResultList().stream().forEach(System.out::println);
    }

    private static void consultarFaculdadeComIdParametros(EntityManager em) {
//        String consulta = String.format("SELECT * FROM Faculdade WHERE id=%d", 2);
//        String sql = "SELECT f FROM Faculdade f WHERE f.id=?1";
        String sql = "SELECT f FROM Faculdade f WHERE f.id= :id";
        TypedQuery<Faculdade> createQuery = em.createQuery(sql, Faculdade.class);
//        createQuery.setParameter(1, 2);
        createQuery.setParameter("id", 2);
        createQuery.getResultList().stream().forEach(System.out::println);
    }

    private static void consultarDependentesComPaginacao(EntityManager em) {
        // todos os dependentes de 2 em 2
        String sql = "SELECT d FROM Dependente d";
        TypedQuery<Dependente> createQuery = em.createQuery(sql, Dependente.class);

        long count = em.createQuery("SELECT COUNT(d) FROM Dependente d", Long.class).getSingleResult();
        int resultadosPorPagina = 2;
        int inicio = 0;

//        for (int i = 0; inicio <= count; inicio = ++i * resultadosPorPagina) {
//            paginacao(createQuery, i, inicio, resultadosPorPagina);
//        }
        do {
            paginacao(createQuery, inicio/resultadosPorPagina, inicio, resultadosPorPagina);
            inicio += resultadosPorPagina;
        } while (inicio < count);
    }

    private static void paginacao(TypedQuery<Dependente> createQuery, int pagina, int inicio, int limite) {
        System.out.println(String.format("Pagina: %d Inicio %d Limite: %d", pagina + 1, inicio, limite));
        createQuery.setFirstResult(inicio).setMaxResults(limite);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarNomeDoDependentes(EntityManager em) {
        String sql = "SELECT d.nome FROM Dependente d";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().stream().forEach(System.out::println);
        
    }
}

//        
//        consultarNomeDoEmpregadoFaculdade(em);
//        consultarNomeDoEmpregadoFaculdadeTipada(em);
//        consultarNomeDoEmpregadoPossuiDependentes(em);
//        consultarNomeDoEmpregadoPossuiDependentesJOIN(em);
//        consultarNomeDoEmpregadoPossuiDependentesLEFTJOIN(em);
//        consultarEmpregadoPossuiDependentes(em);
//        consultarDependentesComIdEntre(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarDependentesComIdForaBETWEEN(em);
//        consultarEmpregadoSemFaculdade(em);
//        consultarEmpregadoComFaculdade(em);
//        consultarEmpregadoPossuiDependente(em);
//        consultarEmpregadoDependenteComNome(em);
//        consultarNomeDependenteComNome(em);
//        consultarPrimeiraLetraDependente(em);
//        consultarTodosOsDependentes(em);
//        consultarNomeDoEmpregadoQuantidadeDependentes(em);
//        consultarEmpregadoComIdSuperiorAMedia(em);
//        consultarEmpregadoSeIdSuperiorADez(em);
//        consultarEmpregadoSeQualquerIdSuperiorADez(em);
//        consultarEmpregadoComDependentesInicandoComM(em);
//        atualizarNomeTodosDependentes(em);
//        removerDependenteComId(em);
//        consultarTodosOsDependentesNamedQuery(em);
//        consultarOsDependentesComIdNamedQuery(em);
//        consultarTodosOsEmpregadosNativeQuery(em);
