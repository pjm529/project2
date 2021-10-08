package encoding;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
      private String encoding;
      @Override
      public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
         req.setCharacterEncoding(encoding);
            chain.doFilter(req, res);
      }
       @Override
       public void init(FilterConfig fConfig) throws  ServletException {
             encoding = fConfig.getInitParameter("encoding");
             
             //인코딩을 따로 지정하지 않은 경우 기본값 세팅
             if(encoding == null) {
                  encoding = "utf-8";
             }
       }
}