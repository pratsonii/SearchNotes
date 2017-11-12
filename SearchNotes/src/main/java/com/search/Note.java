package com.search;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="Notes")
@Indexed
public class Note 
{
	private Long id;
	private String path;
	private String text;
	private String fileName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="Path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name="Text", length=50000)
	@Field
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Note(String path, String text) {
		super();
		this.path = path;
		this.text = text;
	}
	
	public Note(String path, String text, String fileName) {
		super();
		this.path = path;
		this.text = text;
		this.fileName = fileName;
	}
	public Note() {
		super();
	}
	
	@Column(name="fileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
