/*
package moyeomoyeo.filter;

import org.hibernate.hql.internal.ast.HqlASTFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter_first implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //토큰 :cos 만들어줘야함, id, pw 정상적으로 들어와서 로그인이 완료된다면 토큰을 만들어주고 그걸 응답을 해준다.
        // 요청 할때마다 header에 Authorization에 value 값으로 토큰을 가져오게 된다면
        // 그떄 토큰이 넘어오면 이 토큰이 내가 만든 토큰인지만 검증하면된다. (RSA,HS256)
        if (req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);


            if(headerAuth.equals("cos")){
                System.out.println("인증됨");
                chain.doFilter(req, res); //계속해서 진행
            }else{
                System.out.println("인증안됨");

            }
        }
        System.out.println("필터1");

    }
}
*/
