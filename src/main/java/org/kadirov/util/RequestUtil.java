package org.kadirov.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dto.ErrorView;

import java.io.IOException;

public final class RequestUtil {

    private RequestUtil() {
    }

    public static void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response, ErrorView errorView) throws ServletException, IOException {
        request.setAttribute("error", errorView);
        request.getRequestDispatcher("pages/error.jsp").forward(request, response);
    }
}
