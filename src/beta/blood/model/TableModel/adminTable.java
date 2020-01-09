package beta.blood.model.TableModel;

/**
 *
 * @author Admin
 */
public class adminTable {
    String empid, name, surname, branch;

    public adminTable(String empid, String name, String surname, String branch) {
        this.empid = empid;
        this.name = name;
        this.surname = surname;
        this.branch = branch;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    
}
