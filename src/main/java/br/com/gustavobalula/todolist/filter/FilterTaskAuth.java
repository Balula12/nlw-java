package br.com.gustavobalula.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gustavobalula.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    public IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain  filterChain)
            throws ServletException, IOException {

                // Pegar a autenticação (login, senha)
               var authorization = request.getHeader("Authorization");

               var authEncoded = authorization.substring("Basic".length()).trim();

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                var authString = new String(authDecode);

            

               String[] credenitals = authString.split(":");
               String username = credenitals[0];
               String password = credenitals[1];
               
               System.out.println("Authorization");
               System.out.println(username);
               System.out.println(password);


               // Validar usuário
               var user = this.userRepository.findByUsername(username);
                if (user == null){
                    response.sendError(401);
                }

                //validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if(passwordVerify.verified){
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
               
    }

   

}