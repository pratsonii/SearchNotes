package com.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class NoteQuery 
{
	
	 @PersistenceContext
	 private EntityManager entityManager;
	 
	 public List<Note> search(String text) {
		    
		    // get the full text entity manager
		    FullTextEntityManager fullTextEntityManager =
		        org.hibernate.search.jpa.Search.
		        getFullTextEntityManager(entityManager);
		    
		    // create the query using Hibernate Search query DSL
		    QueryBuilder queryBuilder = 
		        fullTextEntityManager.getSearchFactory()
		        .buildQueryBuilder().forEntity(Note.class).get();
		    
		    // a very basic query by keywords
		    org.apache.lucene.search.Query query =
		        queryBuilder
		          .keyword().wildcard()
		          .onFields("text")
		          .matching("*"+text+"*")
		          .createQuery();

		    // wrap Lucene query in an Hibernate Query object
		    org.hibernate.search.jpa.FullTextQuery jpaQuery =
		        fullTextEntityManager.createFullTextQuery(query, Note.class);
		  
		    // execute search and return results (sorted by relevance as default)
		    @SuppressWarnings("unchecked")
		    List<Note> results = jpaQuery.getResultList();
		    
		    return results;
		  } 
}
