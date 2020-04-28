
package com.pavlov.albumproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlbumWSServlet", urlPatterns = {"/AlbumWSServlet"})
public class AlbumWSServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            List<Album> album_list = loadAlbumsFromDataBase();
            ObjectMapper mapper = new ObjectMapper();
            String json_string = mapper.writeValueAsString(album_list);
            out.print(json_string);
        }
    }
    
    private List<Album> loadAlbumsFromDataBase() {
        List<Album> album_list = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"
                    + "C:\\temp\\albumdatabase\\albumdatabase", "SA", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select AlbumID, Artist, Title, Release from Album");
            while (result.next()) {
                long id = result.getInt("AlbumID");
                String artist = result.getString("Artist");
                String title = result.getString("Title");
                long release = result.getInt("Release");
                Album new_album = new Album(id, artist, title, new Date(release));
                album_list.add(new_album);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.commit();
                    conn.close();
                } catch(Exception exception) {
                    // exception caught
                }
            }
        }
        return album_list;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
