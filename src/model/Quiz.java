/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public class Quiz {

    private String name;
    private String url;
    private String refId;
    private boolean done;

    public Quiz() {
    }

    public Quiz(String name, String url, String refId, boolean done) {
        this.name = name;
        this.url = url;
        this.refId = refId;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Quiz{" + "name=" + name + ", url=" + url + ", refId=" + refId + ", done=" + done + '}';
    }



}
