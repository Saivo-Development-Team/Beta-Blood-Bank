/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model.TableModel;

/**
 *
 * @author Daniel
 */
public class donorTable {
    String name, surname, ID;
    
    public donorTable(String name, String surname, String ID)
    {this.name = name;
    this.surname = surname;
    this.ID = ID;
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

   
}
