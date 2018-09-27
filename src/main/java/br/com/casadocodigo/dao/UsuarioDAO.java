package br.com.casadocodigo.dao;

import br.com.casadocodigo.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsuarioDAO implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList();

        if(usuarios.isEmpty()){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return usuarios.get(0);
    }
}
