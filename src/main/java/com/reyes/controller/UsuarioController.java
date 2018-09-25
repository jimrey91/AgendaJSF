
package com.reyes.controller;

import com.reyes.ejb.UsuarioFacadeLocal;
import com.reyes.model.Persona;
import com.reyes.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;

@Named
@ViewScoped
public class UsuarioController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @Inject
    private Persona persona;
    
    @Inject
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
//        persona = new Persona();
//        usuario = new Usuario();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void registrar(){
        try {
            String encrypt = DigestUtils.sha1Hex(this.usuario.getClave());
            this.usuario.setCodigo(persona);
            this.usuario.setClave(encrypt);
            usuarioEJB.create(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Se registro"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }
    }
    
}
