package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (urlPatterns = "/delete")

public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json;charset=utf-8");

        StringBuffer jb = new StringBuffer();

        String line;

        try {

            BufferedReader reader = request.getReader();

            while ((line=reader.readLine())!=null){
                jb.append(line);
            }

        } catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb),JsonObject.class);

        Integer id = jobj.get("id").getAsInt();

        PrintWriter pw = response.getWriter();

        if (id ==0) {

            model.getFromList().clear();
            pw.println("Все пользователи из списка удалены");
            pw.print(gson.toJson(model.getFromList()));
        }
        else if (id > 0){
            if (model.getFromList().get(id)==null){
                pw.print(gson.toJson("Пользователя с таким ID не существует"));
            } else {
                model.getFromList().remove(id);
                pw.println("Пользователь с ID=" + id + " удален! Текущий список:");
                pw.print(gson.toJson(model.getFromList()));
            }
        } else {
            pw.print(gson.toJson("ID должен быть больше нуля"));
        }


    }
}
