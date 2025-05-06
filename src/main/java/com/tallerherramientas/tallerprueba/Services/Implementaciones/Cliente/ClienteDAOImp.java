package com.tallerherramientas.tallerprueba.Servicios.Implementaciones.Cliente;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Cliente;
import com.tallerherramientas.tallerprueba.Repositorio.ClienteRepository;
import com.tallerherramientas.tallerprueba.Servicios.Contratos.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteDAOImp implements ClienteDAO {
    @Autowired
    private ClienteRepository clienteRepository;
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
