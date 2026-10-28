/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

/**
 *
 * @author CodeLex, @ESPE
 */
public class LegalAssistant extends User {

    private String area;
    private Lawyer supervisor;

    public LegalAssistant(int userId, String name, String username, String password, String area) {
        super(userId, name, username, password, Role.LEGAL_ASSISTANT);
        this.area = area;
    }

    public boolean updateCaseData(CaseFile caseFile) {
        if (caseFile == null || !hasPermission("UPDATE_CASE")) {
            return false;
        }
        return caseFile.update();
    }

    @Override
    public String toString() {
        return "LegalAssistant{" + "name=" + getName() + ", area=" + area + '}';
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the supervisor
     */
    public Lawyer getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(Lawyer supervisor) {
        this.supervisor = supervisor;
    }
}
