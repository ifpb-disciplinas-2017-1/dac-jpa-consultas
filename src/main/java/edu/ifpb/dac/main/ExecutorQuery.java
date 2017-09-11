package edu.ifpb.dac.main;

import edu.ifpb.dac.model.Dependente;
import edu.ifpb.dac.model.Empregado;
import edu.ifpb.dac.model.EmpregadoFaculdade;
import edu.ifpb.dac.model.Faculdade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
//        consultarDependentesComPaginacao(em);
//        consultarNomeDoDependentes(em);
//        consultarNomeDoEmpregadoEFaculdade(em);
//        consultarNomeDoEmpregadoEFaculdadeComTipo(em);
//        consultarNomeDoEmpregadoQuePossuiDependentes(em);
//        consultarNomeDoEmpregadoPossuiDependentesJOIN(em);
//        consultarNomeDoEmpregadoPossuiDependentesLEFTJOIN(em);
//        consultarDependentesComIdEntre(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarDependentesComIdForaBETWEEN(em);
//        consultarEmpregadoSemFaculdade(em);
//        consultarEmpregadoComFaculdade(em);
//        consultarEmpregadoPossuiDependente(em);
//        consultarEmpregadoDependenteComNome(em);
        consultarPrimeiraLetraDependente(em);
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
            paginacao(createQuery, inicio / resultadosPorPagina, inicio, resultadosPorPagina);
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

    private static void consultarNomeDoEmpregadoEFaculdade(EntityManager em) {
        String sql = "SELECT e.nome, e.faculdade FROM Empregado e";
        // String, Faculdade
        Query createQuery = em.createQuery(sql);
        List<Object[]> resultList = createQuery.getResultList();
        resultList.forEach((tupla) -> {
            String nome = (String) tupla[0];
            Faculdade faculdade = (Faculdade) tupla[1];
            System.out.println(String.format("Nome:%s e faculdade: %s", nome, faculdade));
        });

    }

    private static void consultarNomeDoEmpregadoEFaculdadeComTipo(EntityManager em) {
        String sql = "SELECT new edu.ifpb.dac.model.EmpregadoFaculdade(e.nome, e.faculdade) FROM Empregado e";
        TypedQuery<EmpregadoFaculdade> createQuery = em.createQuery(sql, EmpregadoFaculdade.class);
        createQuery.getResultList().stream().forEach(System.out::println);
    }

    private static void consultarNomeDoEmpregadoQuePossuiDependentes(EntityManager em) {
        String sql = "SELECT e.nome FROM Empregado e, IN (e.dependentes) d";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().stream().forEach(System.out::println);
    }

    private static void consultarNomeDoEmpregadoPossuiDependentesJOIN(EntityManager em) {
        String sql = "SELECT DISTINCT(e.nome) FROM Empregado e JOIN (e.dependentes) d";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().stream().forEach(System.out::println);
    }

    // nome do empregado e quantidade de dependentes
    private static void consultarNomeDoEmpregadoPossuiDependentesLEFTJOIN(EntityManager em) {
        String sql = "SELECT e.nome, COUNT(d) FROM Empregado e LEFT JOIN (e.dependentes) d GROUP BY e.nome";
        Query createQuery = em.createQuery(sql);

        List<Object[]> resultList = createQuery.getResultList();
        resultList.forEach((tupla) -> {
            String nome = (String) tupla[0];
            Long quantidade = (Long) tupla[1];
            System.out.println(String.format("Nome:%s e quantidade: %d", nome, quantidade));
        });
    }

    private static void consultarDependentesComIdEntre(EntityManager em) {
        String sql = "SELECT d FROM Dependente d WHERE d.id>=1 AND d.id<=(55 + 1)";
        TypedQuery<Dependente> createQuery = em.createQuery(sql, Dependente.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarDependentesComIdEntreBETWEEN(EntityManager em) {
        String sql = "SELECT d FROM Dependente d WHERE d.id BETWEEN 1 AND 56";
        TypedQuery<Dependente> createQuery = em.createQuery(sql, Dependente.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarDependentesComIdForaBETWEEN(EntityManager em) {
        String sql = "SELECT d FROM Dependente d WHERE d.id NOT BETWEEN 1 AND 56";
        TypedQuery<Dependente> createQuery = em.createQuery(sql, Dependente.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarEmpregadoSemFaculdade(EntityManager em) {
//     String sql = "SELECT * FROM Empregado WHERE chave_faculdade is null";
        String sql = "SELECT e FROM Empregado e WHERE e.faculdade IS NULL";
        TypedQuery<Empregado> createQuery = em.createQuery(sql, Empregado.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarEmpregadoComFaculdade(EntityManager em) {
        String sql = "SELECT e FROM Empregado e WHERE e.faculdade IS NOT NULL";
        TypedQuery<Empregado> createQuery = em.createQuery(sql, Empregado.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarEmpregadoPossuiDependente(EntityManager em) {
        String sql = "SELECT e FROM Empregado e WHERE e.dependentes IS NOT EMPTY";
        TypedQuery<Empregado> createQuery = em.createQuery(sql, Empregado.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    // recuperar o empregado que possui algum dependente com nome iniciando em M
    private static void consultarEmpregadoDependenteComNome(EntityManager em) {
        String sql = "SELECT LOWER(e.nome) FROM Empregado e, Dependente d WHERE d MEMBER OF e.dependentes "
                + "AND UPPER(d.nome) LIKE 'M%'";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarPrimeiraLetraDependente(EntityManager em) {
        String sql = "SELECT SUBSTRING(d.nome,1,1) FROM Dependente d";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().forEach(System.out::println);
    }

}

//        consultarNumeroDeTodosOsDependentes(em);
//        consultarNomeDoEmpregadoEQuantidadeDependentes(em); //com mais de dois dep.
//        consultarEmpregadoComIdSuperiorAMedia(em);
//        consultarEmpregadoSeIdSuperiorADez(em);
//        consultarEmpregadoSeQualquerIdSuperiorADez(em);
//        consultarEmpregadoComDependentesInicandoComM(em);
//        atualizarNomeTodosDependentes(em);
//        removerDependenteComId(em);
//        consultarTodosOsDependentesNamedQuery(em);
//        consultarOsDependentesComIdNamedQuery(em);
//        consultarTodosOsEmpregadosNativeQuery(em);
//git tag -a aula-1 -m "após a primeira aula"
