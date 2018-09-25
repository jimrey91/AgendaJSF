
package com.reyes.controller;

import com.reyes.ejb.NotaFacadeLocal;
import com.reyes.model.Nota;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ComentarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    private List<Nota> notas;
    private Nota nota;

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    
    @PostConstruct
    public void init(){
        notas = notaEJB.findAll();
    }
    
    public void asignar(Nota nota){
        this.nota = nota;
    }
}
