package com.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {


	@Autowired
	INoteDao noteDao;
	
	@Autowired
	NoteQuery noteQuery;
	
	@RequestMapping("/getNotes")
	@ResponseBody
	public List<Note> getNotes()
	{
		return noteDao.findAll();
	}
	
	@RequestMapping("/searchJson")
	@ResponseBody
	public List<Note> getNotesJson(@RequestParam("q") String query)
	{
		return noteQuery.search(query);
	}
	
	@RequestMapping("/searchJson1")
	@ResponseBody
	public Note getNotesJson1(@RequestParam("q") String query)
	{
		return noteQuery.search(query).get(0);
	}
	
	
	@RequestMapping("/search")
	public String getNotes(@RequestParam("q") String query, Model model)
	{
		
		model.addAttribute("noteResults", noteQuery.search(query));
		return "search";
		
	}

	@RequestMapping("/")
	public String getHome()
	{
		return "/index.html";
		
	}
	
	
}
