package com.tempolivre.api.repository;

import com.tempolivre.api.entity.IoTDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IoTDeviceRepository extends JpaRepository<IoTDevice, String> {

}
