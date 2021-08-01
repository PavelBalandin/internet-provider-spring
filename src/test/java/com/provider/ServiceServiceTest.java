package com.provider;

import com.provider.dto.ServiceDto;
import com.provider.entity.Service;
import com.provider.mapper.ServiceMapper;
import com.provider.repository.ServiceRepository;
import com.provider.service.ServiceService;
import com.provider.service.impl.ServiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceTest {

    @Mock
    ServiceRepository serviceRepository;

    ServiceService subject;

    @BeforeEach
    void setUp() {
        subject = new ServiceServiceImpl(serviceRepository, new ServiceMapper());
    }

    @Test
    void findAll() {
        List<ServiceDto> servicesExpected = new ArrayList<>();

        List<Service> serviceList = new ArrayList<>();
        when(serviceRepository.findAll()).thenReturn(serviceList);

        List<ServiceDto> servicesActual = subject.getAll();

        assertEquals(servicesExpected, servicesActual);
    }
}
