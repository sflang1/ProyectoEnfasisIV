/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import Entidad.Titular;
import javax.servlet.http.Part;

/**
 *
 * @author Asus Pc
 */
public class TitularAuxiliar 
{
    private Titular t;
    private Part file;
    private boolean checked;

    public void setFile(Part file) {
        this.file = file;
    }

    public Part getFile() {
        return file;
    }

    public Titular getT() {
        return t;
    }

    public void setT(Titular t) {
        this.t = t;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
