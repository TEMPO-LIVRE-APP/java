package com.tempolivre.api.service;

import com.tempolivre.api.entity.Sensor;
import com.tempolivre.api.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    // Listar todos
    public Page<Sensor> listDevices(Pageable pageable){
        return sensorRepository.findAll(pageable);
    }

    // Listar por Id
    public Sensor searchById(String id){
        return sensorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dispositivo não encontrado"));
    }

    // Registrar
    public Sensor registerDevice(Sensor sensor){
        return sensorRepository.save(sensor);
    }

    // Atualizar
    public Sensor updateDevice(String id, Sensor sensorUpdated){
        Sensor sensor = searchById(id);
        sensor.setName(sensorUpdated.getName());
        sensor.setLatitude(sensorUpdated.getLatitude());
        sensor.setLongitude(sensorUpdated.getLongitude());
        return sensorRepository.save(sensor);
    }

    // Deletar por Id
    public void deleteDevice(String id){
        sensorRepository.deleteById(id);
    }
}
