package com.tallerherramientas.tallerprueba.Services.Implementaciones.Cliente;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Cliente;
import com.tallerherramientas.tallerprueba.Repositories.ClienteRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.ClienteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteDAOImp implements ClienteDAO {
    @Autowired
    private final ClienteRepository clienteRepository;
    
    @Override
    public Cliente guardar(Cliente entidad) {
        return clienteRepository.save(entidad);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long aLong) {
        Optional<Cliente> cliente = clienteRepository.findById(aLong);
        return Optional.ofNullable(cliente.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
    clienteRepository.deleteById(aLong);
    }
}
