package pl.agh.kis.soa.catering.client.authentification;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import eu.bitwalker.useragentutils.UserAgent;


public class BrowserFilter implements Filter{
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestBlockingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.contains("no-browser-support.html")) {
            chain.doFilter(request, response); // Just continue chain.
        } else {
            // Do your business stuff here for all paths other than /specialpath.

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));

            if (userAgent.getBrowser().getName().equals("Firefox")) {
                res.sendRedirect("no-browser-support.html");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
        //we can close resources here
    }
}
