package com.process.demo.controller;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends  CrudRepository<MessageReceive, Long>{

}
