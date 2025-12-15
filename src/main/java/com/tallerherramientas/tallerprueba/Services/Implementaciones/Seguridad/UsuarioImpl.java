package com.tallerherramientas.tallerprueba.Services.Implementaciones.Seguridad;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Seguridad.Usuario;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Seguridad.UsuarioDeSeguridad;
import com.tallerherramientas.tallerprueba.Repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario= usuarioRepository.findByUsername(username);
        if(usuario== null){
            throw new UsernameNotFoundException("Usuario no econtrado");
        }
        return new UsuarioDeSeguridad(usuario);
    }
}
