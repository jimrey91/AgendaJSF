
package com.reyes.controller;

import com.reyes.ejb.CategoriaFacadeLocal;
import com.reyes.ejb.NotaFacadeLocal;
import com.reyes.model.Categoria;
import com.reyes.model.Nota;
import com.reyes.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class NotaController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    @Inject
    private Nota nota;
    @Inject
    private Categoria categoria;
   
    private List<Categoria> categorias;

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
   
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    @PostConstruct
    public void init(){
        categorias = categoriaEJB.findAll();
    }
    
    public void registrar(){
        
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            nota.setCategoria(categoria);
            nota.setPersona(us.getCodigo());
            notaEJB.create(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Se registro"));
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        
        }   
        
    }
    
}
