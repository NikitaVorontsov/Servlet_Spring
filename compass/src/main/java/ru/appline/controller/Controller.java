package ru.appline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.logic.CompassModel;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    private static CompassModel compassModel = CompassModel.getInstance();

    @PostMapping(value = "/addSides", consumes = "application/json", produces = "application/json")
    public Map<String, String> addSides(@RequestBody Map<String, String> sides) {
        compassModel.addSides(sides);
        return compassModel.getAllSides();
    }

    @GetMapping (value = "/getSide", produces = "application/json", consumes = "application/json")
    public Map<String, String> getSide (@RequestBody Map <String,Integer> degree) {
        Map<String, String> result = new HashMap<String, String>();
        String str = "";
        int requestDegree = degree.get("Degree");
        int leftBound = 0;
        int rightBound = 0;
        for (Map.Entry<String, String> side: compassModel.getAllSides().entrySet()) {
            leftBound = Integer.parseInt(side.getValue().split("-")[0]);
            rightBound = Integer.parseInt(side.getValue().split("-")[1]);
            if (leftBound > rightBound) {
                if ((requestDegree >= leftBound && requestDegree <= 360) || (requestDegree >= 0 && requestDegree <= rightBound)) {
                    str = side.getKey();
                }
            } else if (leftBound < rightBound) {
                if (requestDegree >= leftBound && requestDegree <= rightBound) {
                    str = side.getKey();
                }
            }
            if (requestDegree<0 || requestDegree > 360)
            {
                str = "Неверное значение. Введите значение градусов от 0 до 360";
            }
        }

        result.put("Side", str);

        return result;

    }

}
