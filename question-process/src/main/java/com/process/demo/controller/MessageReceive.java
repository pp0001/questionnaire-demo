package com.process.demo.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class MessageReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
	private String message;

	public MessageReceive(String message) {
		super();
		this.message = message;
	}

	public MessageReceive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
    @Override
    public String toString() {
        return "MessageReceived [id =" + id + ", message=" + message  + "]";
    }
	
}
