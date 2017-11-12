package com.search;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

	private String path = "C:\\Pratik\\Notes";
	
	@Autowired
	AppStart appStart;
	
	
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
	 /*try {
	            WatchService watcher = FileSystems.getDefault().newWatchService();
	            Path dir = Paths.get(path);
	            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	             
	            System.out.println("Watch Service registered for dir: " + dir.getFileName());
	             
	            while (true) {
	                WatchKey key;
	                try {
	                    key = watcher.take();
	                } catch (InterruptedException ex) {
	                    return;
	                }
	                 
	                for (WatchEvent<?> event1 : key.pollEvents()) {
	                    WatchEvent.Kind<?> kind = event1.kind();
	                     
	                    @SuppressWarnings("unchecked")
	                    WatchEvent<Path> ev = (WatchEvent<Path>) event1;
	                    Path fileName = ev.context();
	                     
	                    System.out.println(kind.name() + ": " + fileName);
	                     
	                    
	                    if (kind == ENTRY_CREATE) {
	             
	                    	appStart.createIndex();
	             
	                    } else if (kind == ENTRY_DELETE) {
	             
	                    	appStart.createIndex();
	             
	                    } else if (kind == ENTRY_MODIFY) {
	             
	                    	appStart.createIndex();
	             
	                    }
	                }
	                 
	                boolean valid = key.reset();
	                if (!valid) {
	                    break;
	                }
	            }
	             
	        } catch (IOException ex) {
	            System.err.println(ex);
	        }*/
  }
}