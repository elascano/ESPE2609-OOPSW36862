package com.computerstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Customer")
public class Customer {

    @Id
    private String _id;          

    @Field("id")
    private Integer id;          

    private String name;
    private Integer age;
    private Double moneySpent;

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getMoneySpent() { return moneySpent; }
    public void setMoneySpent(Double moneySpent) { this.moneySpent = moneySpent; }
}
