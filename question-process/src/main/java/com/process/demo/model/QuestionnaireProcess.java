package com.process.demo.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaireprocess")
public class QuestionnaireProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
//    @Column(name="QpNumber")
    private int number;

//    @Column(name="QType")
	private String type;
	
//	@Column(name="QName")
	private String name;
	
    @Column(updatable = false)
    private Timestamp createdAt;

    @Column(insertable = false)
    private Timestamp modifiedAt;

//    @Version
//    private long version;
	
	
	public QuestionnaireProcess() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    public QuestionnaireProcess(Long id) {
        this.id = id;
//        this.version = version;
    }

    public QuestionnaireProcess(String type, String name) {
        super();
        this.number = (int) Math.random() * 100;
        this.type = type;
        this.name = name;
    }

    public QuestionnaireProcess(Long id, int number, String type, String name) {
        super();
        this.id = id;
        this.number = number;
        this.type = type;
        this.name = name;
    }

    public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getNumber() {
	   return number;
	}

	public void setNumber(int number) {
	    this.number = number;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    public Timestamp getCreatedAt() {
        if (createdAt != null) {
            return new Timestamp(createdAt.getTime());
        }
        return null;
    }

    protected void setCreatedAt(Timestamp timestamp) {
        this.createdAt = timestamp;
    }

    public Timestamp getModifiedAt() {
        if (modifiedAt != null) {
            return new Timestamp(modifiedAt.getTime());
        }
        return null;
    }

    @PrePersist // called during INSERT
    protected void onPersist() {
        setCreatedAt(now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = now();
    }

    protected static Timestamp now() {
        return new Timestamp(new Date().getTime());
    }

    protected void setUpdatedAt(Timestamp timestamp) {
        modifiedAt = timestamp;
    }

//    public void setVersion(long version) {
//        this.version = version;
//    }

    
    @Override
    public String toString() {
        return "Questionaireprocess [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
	
}
