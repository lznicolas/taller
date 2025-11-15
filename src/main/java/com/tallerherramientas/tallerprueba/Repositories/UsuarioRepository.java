package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Seguridad.Usuario;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Seguridad.UsuarioDeSeguridad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioDeSeguridad,Long> {

    Usuario findByUsuario(String username);

}
