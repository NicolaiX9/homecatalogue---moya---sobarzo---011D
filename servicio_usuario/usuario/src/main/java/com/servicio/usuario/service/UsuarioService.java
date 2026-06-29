package com.servicio.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.usuario.model.TipoUsuario;
import com.servicio.usuario.model.Usuario;
import com.servicio.usuario.repository.TipoUsuarioRepository;
import com.servicio.usuario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

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

        TipoUsuario tipo = tipoUsuarioRepository.findById(
            //Busca el id y, si no existe, lanza un mensaje informándolo 
            usuario.getTipoUsuario().getId())
        .orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));

        usuario.setTipoUsuario(tipo);

    return usuarioRepository.save(usuario);
}

    public void eliminar(Long id){
      usuarioRepository.deleteById(id);
    
    }

    public Optional<Usuario> encontrarPorRun(String run){
      return usuarioRepository.findByRun(run);
    }

}
