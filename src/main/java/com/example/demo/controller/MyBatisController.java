package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ActorJpa;
import com.example.demo.domain.Actor;
import com.example.demo.jpa.repository.ActorRepository;
import com.example.demo.mybatis.repository.ActorMyBatisService;

@RestController
public class MyBatisController {

	@Autowired
	ActorRepository repo;
	
	@Autowired
	ActorMyBatisService service;
	
	@RequestMapping(method=RequestMethod.GET,path="/test/{name}")
	public String testHello(@PathVariable String name) {
		return "Hello "+name;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/actors")
	public List<ActorJpa> findAllActors(){
		return repo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/mybatis/actors")
	public List<Actor> findAllActorsMyBatis(){
		return service.getActors();
	}
}
