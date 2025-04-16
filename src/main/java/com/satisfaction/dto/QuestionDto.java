package com.satisfaction.dto;

import java.util.List;

public class QuestionDto {

    private Long id;
    private String text;
    private String type;
    private boolean required;
    private List<String> options;
    private Integer orderIndex;
    private Long formId;

    // Constructeurs
    public QuestionDto() {
    }

    public QuestionDto(Long id, String text, String type, boolean required, List<String> options, Integer orderIndex, Long formId) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.required = required;
        this.options = options;
        this.orderIndex = orderIndex;
        this.formId = formId;
    }

    // Getters & Setters

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", required=" + required +
                ", options=" + options +
                ", orderIndex=" + orderIndex +
                ", formId=" + formId +
                '}';
    }
}
