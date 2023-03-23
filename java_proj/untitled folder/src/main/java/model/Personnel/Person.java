/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

/**
 *
 * @author kal bugrara
 */
public class Person {
    String name;
    String id;
    String address; //in order to let system assign a specific market

    public Person(String id, String name, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getPersonId() {
        return id;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getPersonId();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
