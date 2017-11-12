package com.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

@Component
@Transactional
public class AppStart 
{

	@Autowired
	INoteDao noteDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private String path = "C:\\Pratik\\Notes";
	
	@PostConstruct
	@Order(1)
	private void readFiles()
	{
		 path =  System.getProperty("user.dir");
		try 
		{	
			readFromPath(path);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void readFromPath(String path) throws IOException {
		File directory = new File(path);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList){
		    if (file.isFile())
		    {
		    	
		    	if(file.getName().endsWith(".txt"))
		    	{
		    		System.out.println(file.getAbsolutePath());
		    		readAndInsert(file);
		    	}
		    } else if (file.isDirectory())
		    {
		    	readFromPath(file.getAbsolutePath());
		    }
		}
	}
	
	
	private void readAndInsert(File file) throws IOException
	{
		String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		noteDao.saveAndFlush(new Note(file.getAbsolutePath(), text, file.getName()));
	}
	
	@PostConstruct
	@Order(2)
	public void createIndex()
	{
		try {
		      FullTextEntityManager fullTextEntityManager =
		        Search.getFullTextEntityManager(entityManager);
		      fullTextEntityManager.createIndexer().startAndWait();
		    }
		    catch (InterruptedException e) {
		      System.out.println(
		        "An error occurred trying to build the serach index: " +
		         e.toString());
		    }
	}

	
}
