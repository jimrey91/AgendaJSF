
package com.reyes.controller;

import com.reyes.ejb.CategoriaFacadeLocal;
import com.reyes.ejb.NotaFacadeLocal;
import com.reyes.model.Categoria;
import com.reyes.model.Nota;
import com.reyes.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BuscarController implements Serializable{

    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @EJB
    private NotaFacadeLocal notaEJB;
    
    private List<Nota> listaNotas;
    private List<Categoria> listaCategorias;
    private int codigoCategoria;
    private Date fechaCategoria;

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
       
    
    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Date getFechaCategoria() {
        return fechaCategoria;
    }

    public void setFechaCategoria(Date fechaCategoria) {
        this.fechaCategoria = fechaCategoria;
    }
    
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    @PostConstruct
    public void init(){
     listaCategorias = categoriaEJB.findAll();
    }
    
    public void buscar(){
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaNotas = notaEJB.buscar(us.getCodigo().getCodigo(), codigoCategoria, fechaCategoria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
}
