package org.kadirov.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dto.ErrorView;
import org.kadirov.servlet.exception.BadRequestException;
import org.kadirov.servlet.exception.ConflictException;
import org.kadirov.servlet.exception.InternalServerException;
import org.kadirov.servlet.exception.NotFoundException;
import org.kadirov.util.RequestUtil;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            super.service(req, res);
        } catch (InternalServerException ise){
            ise.printStackTrace();
            RequestUtil.forwardToErrorPage((HttpServletRequest) req, (HttpServletResponse) res, new ErrorView(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ise.getMessage()));
        } catch (BadRequestException bre){
            bre.printStackTrace();
            RequestUtil.forwardToErrorPage((HttpServletRequest) req, (HttpServletResponse) res, new ErrorView(HttpServletResponse.SC_BAD_REQUEST, bre.getMessage()));
        } catch (ConflictException ce){
            ce.printStackTrace();
            RequestUtil.forwardToErrorPage((HttpServletRequest) req, (HttpServletResponse) res, new ErrorView(HttpServletResponse.SC_CONFLICT, ce.getMessage()));
        } catch (NotFoundException nfe){
            nfe.printStackTrace();
            RequestUtil.forwardToErrorPage((HttpServletRequest) req, (HttpServletResponse) res, new ErrorView(HttpServletResponse.SC_NOT_FOUND, nfe.getMessage()));
        } catch (Exception e){
            e.printStackTrace();
            RequestUtil.forwardToErrorPage((HttpServletRequest) req, (HttpServletResponse) res, new ErrorView(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error occurred"));
        }
    }
}