package com.servicio.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.usuario.model.Usuario;
import com.servicio.usuario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public List <Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional <Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id){
      usuarioRepository.deleteById(id);
    
    }

    public Optional<Usuario> encontrarPorRun(String run){
      return usuarioRepository.findByRun(run);
    }

}
