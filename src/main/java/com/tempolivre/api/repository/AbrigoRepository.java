package com.tempolivre.api.repository;

import com.tempolivre.api.entity.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, String> {

    List<Abrigo> findByNome(String nome);

    List<Abrigo> findByEndereco(String endereco);

}
