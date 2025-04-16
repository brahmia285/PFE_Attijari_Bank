package com.satisfaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private boolean required;

    @Column(length = 1000)
    private String options; // Pour les questions à choix multiples, séparées par des virgules

    private Integer orderIndex; // Pour trier les questions dans un formulaire

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    // Constructeurs
    public Question() {}

    public Question(Long id, String text, QuestionType type, boolean required, String options, Integer orderIndex, Form form) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.required = required;
        this.options = options;
        this.orderIndex = orderIndex;
        this.form = form;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
