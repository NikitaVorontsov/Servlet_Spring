package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (urlPatterns = "/post")

public class ServletCalculator extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json;charset=utf-8");

        StringBuffer jb = new StringBuffer();

        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line=reader.readLine())!=null) {
                jb.append(line);
            }
        }
        catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb),JsonObject.class);

        double var1 = jobj.get("var1").getAsDouble();
        double var2 = jobj.get("var2").getAsDouble();
        String math = jobj.get("math").getAsString();
        double summ = 0;

        PrintWriter pw = response.getWriter();

        if (math.equals("+")) {
            summ = var1+var2;
            pw.print(gson.toJson("result: "+summ));
        }
        else if (math.equals("-")){
            summ = var1 - var2;
            pw.print(gson.toJson("result: "+summ));
        } else if (math.equals("*")){
            summ = var1 * var2;
            pw.print(gson.toJson("result: "+summ));
        } else if (math.equals("/")){
            if (var2==0){
                pw.print(gson.toJson("Введите значение var2, не равное нулю"));
            } else {
                summ = var1/var2;
                pw.print(gson.toJson("result: "+summ));
            }
        }
    }
}
