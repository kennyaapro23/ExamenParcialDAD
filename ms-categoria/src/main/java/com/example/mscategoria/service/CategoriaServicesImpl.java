package com.example.mscategoria.service;

import com.example.mscategoria.entity.Categoria;
import com.example.mscategoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicesImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
    @Override
    public Categoria guardar (Categoria categoria){
        return categoriaRepository.save(categoria);

    }
    @Override
    public Categoria actualizar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    @Override
    public Optional<Categoria> listarPorId(Integer id){
        return categoriaRepository.findById(id);
    }
    @Override
    public void eliminarPorId (Integer id){
        categoriaRepository.deleteById(id);
    }

}
