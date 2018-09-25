
package com.reyes.controller;

import com.reyes.ejb.NotaFacadeLocal;
import com.reyes.model.Nota;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ValorarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    @Inject
    private ComentarController comentarController;
    private Nota nota;

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    @PostConstruct
    public void init(){
        this.nota = comentarController.getNota();
    }
    
    public void registrar(){
        try {
            notaEJB.edit(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Se comento"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        
    }
}
