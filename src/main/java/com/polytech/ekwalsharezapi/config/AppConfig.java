package com.polytech.ekwalsharezapi.config;

import com.polytech.ekwalsharezapi.dto.LedgerUserDTO;
import com.polytech.ekwalsharezapi.dto.LedgerUserResponseDTO;
import com.polytech.ekwalsharezapi.model.LedgerUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<LedgerUserDTO, LedgerUser> ledgerMap = new PropertyMap<LedgerUserDTO, LedgerUser>() {
            protected void configure() {
                map().setNom(source.getName());
            }
        };

        PropertyMap<LedgerUser, LedgerUserResponseDTO> ledgerUserToDto = new PropertyMap<LedgerUser, LedgerUserResponseDTO>() {
            protected void configure() {
                map().setName(source.getNom());
                map().setId(source.getId());
            }
        };

        modelMapper.addMappings(ledgerMap);
        modelMapper.addMappings(ledgerUserToDto);
        return modelMapper;
    }
}
