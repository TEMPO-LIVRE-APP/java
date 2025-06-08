package com.tempolivre.api.service;

import com.tempolivre.api.entity.Abrigo;
import com.tempolivre.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    // Listar Todos
    public Page<Abrigo> listAbrigos(Pageable pageable){
        return abrigoRepository.findAll(pageable);
    }

    // Buscar por Id
    public Abrigo searchById(String id){
        return abrigoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Abrigo nao encontrado"));

    }

    // Buscar por Nome
    public Page<Abrigo> searchByNome(String name, Pageable pageable){
        List<Abrigo> abrigos = abrigoRepository.findByName(name);
        return new PageImpl<>(abrigos, pageable, abrigos.size());
    }

    // Buscar por Endereco
    public Page<Abrigo> searchByEndereco(String endereco, Pageable pageable){
        List<Abrigo> abrigos = abrigoRepository.findByEndereco(endereco);
        return new PageImpl<>(abrigos, pageable, abrigos.size());
    }

    // Registar
    public Abrigo registerAbrigo(Abrigo abrigo){
        return abrigoRepository.save(abrigo);
    }

    // Atualizar
    public Abrigo updateAbrigo(String id, Abrigo newAbrigo){
        Abrigo abrigo = searchById(id);

        abrigo.setName(newAbrigo.getName());
        abrigo.setContato(newAbrigo.getContato());
        abrigo.setEndereco(newAbrigo.getEndereco());
        abrigo.setLatitude(newAbrigo.getLatitude());
        abrigo.setLongitude(newAbrigo.getLongitude());

        return abrigoRepository.save(abrigo);
    }

    // Deletar
    public void deleteAbrigo(String id){
        abrigoRepository.deleteById(id);
    }
}
