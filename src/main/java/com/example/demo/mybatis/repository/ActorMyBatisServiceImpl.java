package com.example.demo.mybatis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Actor;
import com.example.demo.mybatis.mapper.ActorMyBatisMapper;

@Service
public class ActorMyBatisServiceImpl implements ActorMyBatisService {


	private ActorMyBatisMapper actorMyBatisMapper;

	@Autowired
	public ActorMyBatisServiceImpl(ActorMyBatisMapper actorMyBatisMapper) {
		this.actorMyBatisMapper = actorMyBatisMapper;
	}

	@Override
	public List<Actor> getActors() {
		return actorMyBatisMapper.getActors();
	}

}
