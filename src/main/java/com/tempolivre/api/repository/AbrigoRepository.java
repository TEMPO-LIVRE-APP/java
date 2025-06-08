package com.tempolivre.api.repository;

import com.tempolivre.api.entity.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbrigoRepository extends JpaRepository<Abrigo, String> {

    List<Abrigo> findByName(String name);

    List<Abrigo> findByEndereco(String endereco);

}
