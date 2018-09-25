
package com.reyes.ejb;

import com.reyes.model.Telefono;
import java.util.List;


public interface TelefonoFacadeLocal {
    
    void create(Telefono telefono);

    void edit(Telefono telefono);

    void remove(Telefono telefono);

    Telefono find(Object id);

    List<Telefono> findAll();

    List<Telefono> findRange(int[] range);

    int count();
    
    List<Telefono> buscarTelefono(int codigoPersona) throws Exception;
}
