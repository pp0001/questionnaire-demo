package com.process.demo.questionnaire;

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
@Table(name = "questionnaire")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @Column
    private int number;

    @Column
	private String type;
	
	@Column
	private String name;
	@Column
	private String question;
	@Column
	private String answer;
	@Column
	private String processor;
	@Column
	private String activity;
	@Column
	private String riskLevel;
	
	
    @Column(updatable = false)
    private Timestamp createdAt;

    @Column(insertable = false)
    private Timestamp modifiedAt;

//    @Version
//    private long version;
	
	
	public Questionnaire() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    public Questionnaire(Long id) {
        this.id = id;
//        this.version = version;
    }

    public Questionnaire(String type, String name) {
        super();
        this.number = (int) Math.random() * 100;
        this.type = type;
        this.name = name;
    }

    public Questionnaire(Long id, int number, String type, String name, String question, String answer,
			String processor, String activity, String riskLevel) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
		this.name = name;
		this.question = question;
		this.answer = answer;
		this.processor = processor;
		this.activity = activity;
		this.riskLevel = riskLevel;
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
	
	
	
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
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

//    public long getVersion() {
//        return version;
//    }

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
        return "Questionnaire [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
}
