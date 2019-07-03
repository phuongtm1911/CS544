package edu.mum.cs544.bank.dao;

import edu.mum.cs544.bank.EntityManagerHelper;
import edu.mum.cs544.bank.domain.Account;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class JPAAccountDAO implements IAccountDAO {

    public void saveAccount(Account account) {
        // System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
        EntityManager em = EntityManagerHelper.getCurrent();
        em.persist(account); // add the new
    }

    public void updateAccount(Account account) {
        // System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
        EntityManager em = EntityManagerHelper.getCurrent();
        Account accountexist = loadAccount(account.getAccountnumber());
        if (accountexist != null) {
            em.remove(accountexist); // remove the old
            em.persist(account); // add the new
        }
    }

    public Account loadAccount(long accountnumber) {
        // System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
        EntityManager em = EntityManagerHelper.getCurrent();
        return em.find(Account.class, accountnumber);
    }

    public Collection<Account> getAccounts() {
        EntityManager em = EntityManagerHelper.getCurrent();
        EntityGraph<Account> graph = em.createEntityGraph(Account.class);
        graph.addAttributeNodes("entryList");
        graph.addAttributeNodes("customer");

        TypedQuery<Account> query = em.createQuery("from Account a join fetch a.entryList join fetch a.customer", Account.class);
        query.setHint("javax.persistence.fetchgraph", graph);
        Collection<Account> accountlist = query.getResultList();
        return accountlist;
    }

}
