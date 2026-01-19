package com.tallerherramientas.tallerprueba.Config;

import com.tallerherramientas.tallerprueba.Modelo.Entities.EmpresaConfig;
import com.tallerherramientas.tallerprueba.Repositories.EmpresaConfigRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmpresaConfigSeed {
    @Bean
    public CommandLineRunner seedEmpresaConfig(EmpresaConfigRepository empresaConfigRepository) {
        return args -> {
            if (empresaConfigRepository.count() == 0) {
                EmpresaConfig config = new EmpresaConfig();
                config.setNombreFantasia("Empresa");
                empresaConfigRepository.save(config);
            }
        };
    }
}
