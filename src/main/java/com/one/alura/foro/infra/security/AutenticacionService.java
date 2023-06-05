package com.one.alura.foro.infra.security;

import com.one.alura.foro.domain.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

   private UsuarioRepository usuarioRepository;
   public  AutenticacionService(UsuarioRepository usuRepo) {
       this.usuarioRepository = usuRepo;
   }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return usuarioRepository.findByEmail(email);
    }
}
