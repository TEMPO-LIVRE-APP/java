package com.tempolivre.api.service;

import com.tempolivre.api.entity.IoTDevice;
import com.tempolivre.api.repository.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IoTDeviceService {

    @Autowired
    private IoTDeviceRepository ioTDeviceRepository;

    // Listar todos
    public Page<IoTDevice> listDevices(Pageable pageable){
        return ioTDeviceRepository.findAll(pageable);
    }

    // Listar por Id
    public IoTDevice searchById(String id){
        return ioTDeviceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dispositivo não encontrado"));
    }

    // Registrar
    public IoTDevice registerDevice(IoTDevice ioTDevice){
        return ioTDeviceRepository.save(ioTDevice);
    }

    // Atualizar
    public IoTDevice updateDevice(String id, IoTDevice ioTDeviceUpdated){
        IoTDevice ioTDevice = searchById(id);
        ioTDevice.setName(ioTDeviceUpdated.getName());
        ioTDevice.setLatitude(ioTDeviceUpdated.getLatitude());
        ioTDevice.setLongitude(ioTDeviceUpdated.getLongitude());
        return ioTDeviceRepository.save(ioTDevice);
    }

    // Deletar por Id
    public void deleteDevice(String id){
        ioTDeviceRepository.deleteById(id);
    }
}
