package com.ecommerce.used_good.bean;

import java.util.Objects;

public class EmailData 
{
    private String toEmail;
    private String subject;
    private String body;


    public EmailData() {
    }

    public EmailData(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    public String getToEmail() {
        return this.toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailData toEmail(String toEmail) {
        setToEmail(toEmail);
        return this;
    }

    public EmailData subject(String subject) {
        setSubject(subject);
        return this;
    }

    public EmailData body(String body) {
        setBody(body);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmailData)) {
            return false;
        }
        EmailData emailData = (EmailData) o;
        return Objects.equals(toEmail, emailData.toEmail) && Objects.equals(subject, emailData.subject) && Objects.equals(body, emailData.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toEmail, subject, body);
    }

    @Override
    public String toString() {
        return "{" +
            " toEmail='" + getToEmail() + "'" +
            ", subject='" + getSubject() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }

}
