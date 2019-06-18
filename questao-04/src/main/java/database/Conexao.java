package database;

import entity.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Conexao {

    private EntityManager emPostgre1;
    private EntityManager emPostgre2;


    public Conexao() {
        this.emPostgre1 = Persistence
                .createEntityManagerFactory("POSTGRESQL")
                .createEntityManager();
        this.emPostgre2 = Persistence
                .createEntityManagerFactory("POSTGRESQL2")
                .createEntityManager();
    }

    public void salvarPostgre(Usuario usuario) {
        emPostgre1.getTransaction().begin();
        emPostgre1.persist(usuario);
        emPostgre1.getTransaction().commit();
    }

    public void salvarPostgre2(Usuario usuario) {
        emPostgre2.getTransaction().begin();
        emPostgre2.persist(usuario);
        emPostgre2.getTransaction().commit();
    }

}
