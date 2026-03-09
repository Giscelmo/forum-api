//package br.com.giscelmo.forum_api.infra.security;
//
//import br.com.giscelmo.forum_api.repository.UsuarioRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class SecurityFilter extends OncePerRequestFilter {
//
//    private TokenService tokenService;
//
//    UsuarioRepository usuarioRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        var tokenJWT = recuperarToken(request);
//
//        if (tokenJWT != null) {
//            var subjesct = tokenService.getSubject(tokenJWT);
//            var usuario = usuarioRepository.findByLogin(subjesct);
//            var authentication = new UsernamePasswordAuthenticationToken(
//                    usuario,
//                    null,
//                    usuario.getAuthorities()
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println(subjesct);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String recuperarToken(HttpServletRequest request) {
//        var authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null) {
//            return authorizationHeader.replace("Bearer", "").trim();
//        }
//        return null;
//    }
//}
